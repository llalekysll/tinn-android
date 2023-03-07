package com.example.tinn.data.networkService

import com.example.tinn.data.modelForJSON.ProfileModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.data.modelForJSON.ResponceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserVideoService {
    @GET("profile/user")
    fun getUser(): Call<ResponceModel<ResponceDataUserModel>>

    @PUT("profile/user")
    fun putUser(@Body profileModel: ProfileModel): Call<ResponceModel<ResponceDataUserModel>>
}