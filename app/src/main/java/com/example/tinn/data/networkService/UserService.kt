package com.example.tinn.data.networkService

import com.example.tinn.data.modelForJSON.ProfileModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.data.modelForJSON.ResponceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserService {
    @GET("profile/user")
    fun getUser(@Header("Authorization") token: String): Call<ResponceModel<ResponceDataUserModel>>

    @PUT("profile/user")
    fun putUser(
        @Body profileModel: ProfileModel,
        @Header("Authorization") token: String
    ): Call<ResponceDataUserModel>
}