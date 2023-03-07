package com.example.tinn.data.networkService

import com.example.tinn.data.modelForJSON.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthorizationService {
    @POST("register")
    fun register(@Body registerModel: RegisterModel): Call<ResponceAuthorizatinoModel>

    @POST("login")
    fun login(@Body signInModel: SignInModel): Call<ResponceAuthorizatinoModel>

    @POST("verification/email")
    fun verificationEmail(
        @Body verificationModel: VerificationModel,
        @Header("Authorization") token: String
    ): Call<ResponceVerificationModel>
}