package com.example.tinn.data.emptities.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserNotices(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    val sound: Int = 0,
    val text: Int = 0,
    val likes: Int = 0,
    val comments: Int = 0,
    @SerializedName ("achievements_notices")
    val achievementsNotices: Int = 0,
    @SerializedName("created_at")
    val createdAt: Date = Date(),
    @SerializedName("update_at")
    val updateAt: Date = Date(),
)
