package com.example.tinn.data.emptities.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserPrivacies(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("type_id")
    val typeId: Int = 1,
    @SerializedName("subscriptions_id")
    val subscriptionsId: Int = 1,
    @SerializedName("playlists_id")
    val playlistsId: Int = 1,
    @SerializedName("achievements_privacies_id")
    val achievementsPrivaciesId: Int = 1,
    @SerializedName("created_at")
    val createdAt: Date = Date(),
    @SerializedName("update_at")
    val updateAt: Date = Date(),
)
