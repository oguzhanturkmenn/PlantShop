package com.oguzhanturkmen.myplantapp.data.datasource

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.oguzhanturkmen.myplantapp.data.models.Answer
import com.oguzhanturkmen.myplantapp.data.models.User

class PlantDataSource(
    var firebaseAuth: FirebaseAuth,
    var collectionReference: CollectionReference
) {
    val answerLogin = MutableLiveData<Answer>()
    val answerRegister = MutableLiveData<Answer>()
    var userLiveData = MutableLiveData<User>()
    var userId: String? = firebaseAuth.currentUser?.uid
    var userEmail: String? = firebaseAuth.currentUser?.email

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
                        hasmapp["userEmail"].toString(),
                    )
                    userLiveData.value = liveUser
                }
            }
        }
        return userLiveData
    }

}