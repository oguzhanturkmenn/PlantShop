package com.oguzhanturkmen.myplantapp.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.oguzhanturkmen.mypharmacyonduty.service.APIUtils
import com.oguzhanturkmen.myplantapp.data.datasource.PlantDataSource
import com.oguzhanturkmen.myplantapp.data.repo.PlantRepo
import com.oguzhanturkmen.myplantapp.retrofit.PlantAPI
import com.oguzhanturkmen.myplantapp.room.PlantDao
import com.oguzhanturkmen.myplantapp.room.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePlantRepo(kds: PlantDataSource): PlantRepo {
        return PlantRepo(kds)
    }

    @Provides
    @Singleton
    fun provideKisilerDataSource(
        firebaseAuth: FirebaseAuth,
        collectionReference: CollectionReference,
        plantAPI: PlantAPI,
        plantDao: PlantDao
    ): PlantDataSource {
        return PlantDataSource(
            firebaseAuth,
            collectionReference,
            plantAPI,
            plantDao
        )
    }

    @Provides
    @Singleton
    fun providePlantAPI(): PlantAPI {
        return APIUtils.plantAPIGet()
    }

    @Provides
    @Singleton
    fun providePlantDao(@ApplicationContext context: Context):PlantDao{
        val db = Room.databaseBuilder(context,PlantDatabase::class.java,"real_plant.sqlite")
            .createFromAsset("real_plant.sqlite").build()

        return db.getPlantRoomDao()
    }

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStore(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): CollectionReference =
        FirebaseFirestore.getInstance().collection("User")
}

