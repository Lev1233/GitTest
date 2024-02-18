package com.example.testappsbuilding.network


import com.example.testappsbuilding.model.MarsPhoto
import retrofit2.http.GET



interface MarsApiService {

    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}



