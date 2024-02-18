package com.example.testappsbuilding.data

import com.example.testappsbuilding.model.MarsPhoto
import com.example.testappsbuilding.network.MarsApiService


interface MarsPhotosRepository {

    suspend fun getMarsPhotos(): List<MarsPhoto>
    suspend fun getRandomMarsPhoto(): MarsPhoto
}


class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService)
    : MarsPhotosRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

    override suspend fun getRandomMarsPhoto(): MarsPhoto {
        val allPhotos = getMarsPhotos()
        return allPhotos.random()
    }
}