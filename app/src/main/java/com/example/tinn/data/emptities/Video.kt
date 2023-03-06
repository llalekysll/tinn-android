package com.example.tinn.data.emptities

import com.google.gson.annotations.SerializedName

data class Video(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("user_owner_id")
    val userOwnerId: Int = 0,
    val price: Int = 0,
    @SerializedName("category_id")
    val categoryId: Int = 0,
    val title: String,
    val description: String,
    val likes: Int,
    val comments: Int,
    val views: Int,
    val url: String,
    @SerializedName("hash_tags")
    val hashTags: String
)
