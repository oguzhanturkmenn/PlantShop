package com.oguzhanturkmen.myplantapp.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.models.Plant
import com.oguzhanturkmen.myplantapp.data.models.User
import com.oguzhanturkmen.myplantapp.retrofit.PlantAPI
import com.oguzhanturkmen.myplantapp.room.PlantDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantDataSource(
    var firebaseAuth: FirebaseAuth,
    var collectionReference: CollectionReference,
    var plantAPI: PlantAPI,
    var plantDao: PlantDao
) {
    var allPlantList = listOf<Plant>()
    var basketPlantList = listOf<Plant>()
    val answerLogin = MutableLiveData<Answer>()
    val answerRegister = MutableLiveData<Answer>()
    var userLiveData = MutableLiveData<User>()

    var userId: String? = firebaseAuth.currentUser?.uid
    var userEmail: String? = firebaseAuth.currentUser?.email
/*
    suspend fun loadPlants(): List<Plant> =
        withContext(Dispatchers.IO){
            plantAPI.getPlant().data
    }
 */

    suspend fun loadPlants(): List<Plant> {
        userEmail = firebaseAuth.currentUser?.email
        userEmail.let {
            withContext(Dispatchers.IO) {
                allPlantList = plantAPI.getPlant().data
                for (plant in allPlantList) {
                    // plant.userEmail = userEmail
                }
            }
        }
        return allPlantList
    }


    suspend fun addToBasket(plantName: String, plantPrice: String,plantCount:Int) {
        withContext(Dispatchers.IO) {
            val saveBasket = Plant(0,"","",plantPrice,plantName,plantCount)
            plantDao.saveBasket(saveBasket)
        }
    }






    suspend fun getAllBasket(): List<Plant> =
        withContext(Dispatchers.IO) {
            plantDao.getAllBasket()
        }


    /*
suspend fun getCombineData():List<Plant>{
    withContext(Dispatchers.IO){
        for (plant in loadPlants()){ // api listesi
            for (databasePlant in listDataBase){
                if(plant.PlantName == databasePlant.){
                    plant.isBasket = databasePlant.isBasket
                    plant.isFavori = true
                }
            }
        }
    }
}

 */


    fun login(email: String, password: String): MutableLiveData<Answer> {
        answerLogin.value = Answer(null, null)
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                answerLogin.value = Answer(1, "")
            }
        }.addOnFailureListener {
            answerLogin.value = Answer(0, it.message)
        }
        return answerLogin
    }

    fun register(email: String, password: String): MutableLiveData<Answer> {
        answerRegister.value = Answer(null, null)
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val uid = firebaseUser.uid
                    val user = User(uid, email)
                    collectionReference.document(uid).set(user).addOnSuccessListener {
                        answerRegister.value = Answer(1, "")
                    }
                }
            }.addOnFailureListener {
                answerRegister.value = Answer(0, it.message)
            }
        return answerRegister
    }

    fun getLiveUser(): MutableLiveData<User> {
        userId = firebaseAuth.currentUser?.uid
        userId?.let {
            collectionReference.document(it).addSnapshotListener { value, _ ->
                if (value != null) {
                    val hasmapp: HashMap<String, Any?> = value.data as HashMap<String, Any?>
                    val liveUser = User(
                        hasmapp["userId"].toString(),
                        hasmapp["userEmail"].toString()
                    )
                    Log.d("deneme", liveUser.toString())
                    userLiveData.value = liveUser
                }
            }
        }
        return userLiveData
    }

    fun updateImage(imageUrl: String) {
        val image = HashMap<String, Any>()
        image["ppUrl"] = imageUrl
        userId?.let {
            collectionReference.document(it).update(image)
        }
    }


}

