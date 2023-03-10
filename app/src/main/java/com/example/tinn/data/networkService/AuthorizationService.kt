package com.example.tinn.data.networkService

import com.example.tinn.data.emptities.user.AuthUser
import com.example.tinn.data.modelForJSON.*
import retrofit2.Call
import retrofit2.http.*

interface AuthorizationService {
    @Headers("No-Authentication: true")
    @POST("register")
    fun register(@Body registerModel: RegisterModel): Call<ResponceAuthorizatinoModel>

    @Headers("No-Authentication: true")
    @POST("login")
    fun login(@Body signInModel: SignInModel): Call<ResponceAuthorizatinoModel>

    @POST("verification/email")
    fun verificationEmail(
        @Body verificationModel: VerificationModel,
    ): Call<ResponceVerificationModel>

    @GET("api/user")
    fun getUser(): Call<AuthUser>
}