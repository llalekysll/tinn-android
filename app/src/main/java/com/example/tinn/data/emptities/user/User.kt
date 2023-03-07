package com.example.tinn.data.emptities.user

import com.google.gson.annotations.SerializedName
import java.util.Date

data class User(
    val id: Int = 0,
    @SerializedName("auth_id")
    val authId: Int = 0,
    @SerializedName("expire_at")
    val expireAt: Date = Date(),
    @SerializedName("created_at")
    val createdAt: Date = Date(),
    @SerializedName("update_at")
    val updatedAt: Date = Date()
)

data class ProfileInfo(
    @SerializedName("videos_count")
    val videosCount: Int = 0,
    @SerializedName("subscriptions_count")
    val subscriptionsCount: Int = 0
)