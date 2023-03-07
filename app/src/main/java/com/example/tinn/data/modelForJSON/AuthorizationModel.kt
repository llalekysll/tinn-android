package com.example.tinn.data.modelForJSON

import com.google.gson.annotations.SerializedName

data class RegisterModel (
    val email: String,
    val password: String,
    @SerializedName("password_confirmation")
    val passwordConfirmation: String,
    val code: String
)

data class SignInModel (
    val email: String,
    val password: String,
)

data class ResponceAuthorizatinoModel(
    val success: Boolean = false,
    val message: String = "",
    val data: ResponceDataAuthorizationModel = ResponceDataAuthorizationModel()
)

data class ResponceDataAuthorizationModel(
    val token: String = "",
    val email: Array<String> = emptyArray(),
    val password: Array<String> = emptyArray(),
    val code: Array<String> = emptyArray(),
)