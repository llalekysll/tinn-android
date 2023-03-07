package com.example.tinn.data.networkService

import com.example.tinn.data.emptities.Comment
import com.example.tinn.data.modelForJSON.ResponceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Video {
    @GET("video/categories")
    fun getCategories(@Header("Authorization") token: String): Call<Any>

    @GET("video/comments")
    fun getComments(
        @Query("video_id") videoId: String,
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): Call<Comment>

    @GET("video")
    fun getVideo(@Header("Authorization") token: String): Call<ResponceModel<Video>>

    @GET("video/recommended")
    fun getRecommendedVideo(@Header("Authorization") token: String): Call<ResponceModel<Video>>

    @GET("video/popular")
    fun getPopularVideo(@Header("Authorization") token: String): Call<ResponceModel<Video>>
}