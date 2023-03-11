package com.example.tinn.data.modelForJSON

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language
import java.util.Date

data class ProfileModel(
    val login: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    val phone: String,
    @SerializedName("gender_id")
    val genderId: Int,
    @SerializedName("data_of_birth")
    val dataOfBirth: Date,
)

data class Gender(
    val title: String = "",
    val id: Int = 0,
)