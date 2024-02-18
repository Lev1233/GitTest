package com.example.testappsbuilding.data

import android.content.Context
import com.example.testappsbuilding.network.MarsApiService
import com.example.testappsbuilding.usecase.AgeUseCase
import com.example.testappsbuilding.usecase.AgeUseCaseImpl
import com.example.testappsbuilding.usecase.NameUseCase
import com.example.testappsbuilding.usecase.NameUseCaseImpl
import com.example.testappsbuilding.usecase.UserProfileUseCase
import com.example.testappsbuilding.usecase.UserProfileUseCaseImpl
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory



interface AppContainer {

    val dataRepository:DataRepository
    val ageUseCase:AgeUseCase
    val nameUseCase:NameUseCase
    val userProfileUseCase:UserProfileUseCase
    val marsPhotosRepository: MarsPhotosRepository

}

class DefaultAppContainer(private val context:Context):AppContainer{


    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }



    override val dataRepository: DataRepository by lazy{
        DataRepositoryImpl(context)
    }
    override val ageUseCase: AgeUseCase by lazy {
        AgeUseCaseImpl(dataRepository)
    }

    override val nameUseCase: NameUseCase by lazy {
        NameUseCaseImpl(dataRepository)
    }

    override val userProfileUseCase: UserProfileUseCase by lazy {
        UserProfileUseCaseImpl(dataRepository)
    }


}