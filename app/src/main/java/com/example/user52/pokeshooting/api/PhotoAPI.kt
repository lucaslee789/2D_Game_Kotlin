package com.example.user52.pokeshooting.api


import com.example.user52.pokeshooting.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET


interface PhotoAPI {
    @GET("?key=10971871-bb304c2733e0d619150f2eaa3&q=pokemon&image_type=photo")
    fun getPhotos(): Call<PhotoList>
}