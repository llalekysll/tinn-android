package com.example.tinn.data.emptities.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserProfiles(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int? = null,
    val login: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("second_name")
    val secondName: String? = null,
    val phone: String? = null,
    val avatar: String? = null,
    val background: String? = null,
    @SerializedName("gender_id")
    val genderId: Int? = null,
    val language: String? = null,
    val url: String? = null,
    @SerializedName("date_of_birth")
    val dateOfBirth: Date? = null,
    @SerializedName("init_recommended")
    val initRecommended: Int = 1,
    @SerializedName("init_popular")
    val initPopular: Int? = 1,
    @SerializedName("password_date_change")
    val passwordDateChange: Date? = null,
    val devices: Int = 0,
    @SerializedName("created_at")
    val createdAt: Date = Date(),
    @SerializedName("update_at")
    val updateAt: Date = Date(),
)