package com.oguzhanturkmen.myplantapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.oguzhanturkmen.mypharmacyonduty.service.APIUtils
import com.oguzhanturkmen.myplantapp.data.datasource.PlantDataSource
import com.oguzhanturkmen.myplantapp.data.repo.PlantRepo
import com.oguzhanturkmen.myplantapp.retrofit.PlantAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        collectionReference: CollectionReference

    ): PlantDataSource {
        return PlantDataSource(firebaseAuth, collectionReference)
    }

    @Provides
    @Singleton
    fun providePlantAPI(): PlantAPI {
        return APIUtils.plantAPIGet()
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

