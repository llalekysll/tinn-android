package com.example.tinn.data.emptities

import com.google.gson.annotations.SerializedName

data class Comment(
    val id: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("video_id")
    val videoId: Int = 0,
    @SerializedName("parent_id")
    val parentId: Int = 0,
    val likes: Int = 0
)