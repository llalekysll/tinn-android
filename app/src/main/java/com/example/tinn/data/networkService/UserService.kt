package com.example.tinn.data.networkService

import com.example.tinn.data.modelForJSON.ProfileModel
import com.example.tinn.data.modelForJSON.ResponceDataGendersModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.data.modelForJSON.ResponceModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserService {
    @GET("profile/user")
    fun getUser(): Call<ResponceModel<ResponceDataUserModel>>

    @PUT("profile/user")
    fun putUser(@Body profileModel: ProfileModel, ): Call<ResponceDataUserModel>

    @GET("profile/genders")
    fun getGenders(): Call<ResponceModel<ResponceDataGendersModel>>

    @Multipart
    @POST("profile/avatar")
    fun loadAvatar(@Part filePart: MultipartBody.Part): Call<Any>
}