package com.example.tinn.data.networkService

import com.example.tinn.data.emptities.Comment
import com.example.tinn.data.emptities.Video
import com.example.tinn.data.modelForJSON.ResponceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VideoService {
    @GET("video/categories")
    fun getCategories(): Call<Any>

    @GET("video/comments")
    fun getComments(
        @Query("video_id") videoId: String,
        @Query("page") page: Int,
    ): Call<Comment>

    @GET("video")
    fun getVideo(): Call<ResponceModel<Video>>

    @GET("video/recommended")
    fun getRecommendedVideo(): Call<ResponceModel<Video>>

    @GET("video/popular")
    fun getPopularVideo(): Call<ResponceModel<Video>>
}