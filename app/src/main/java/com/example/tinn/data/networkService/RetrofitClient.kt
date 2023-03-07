package com.example.tinn.data.networkService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitService: Retrofit? = null
    private var retrofitAuthService: Retrofit? = null

    fun getRetrofitService(): Retrofit {
        if (retrofitService == null) {
            retrofitService = Retrofit.Builder()
                .baseUrl("https://api.tinn.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitService!!
    }

    fun getRetrofitAuthService(): Retrofit {
        if (retrofitService == null) {
            val client = OkHttpClient.Builder().addInterceptor(ServiceInterceptor).build()

            retrofitService = Retrofit.Builder()
                .baseUrl("https://auth.tinn.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        return retrofitService!!
    }
}