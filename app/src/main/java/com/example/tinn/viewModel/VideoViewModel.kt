package com.example.tinn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinn.data.networkService.RetrofitClient
import com.example.tinn.data.networkService.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoViewModel : ViewModel() {
    private val _video = MutableLiveData<List<Video>>(emptyList())
    val video: LiveData<List<Video>>
        get() = _video

    private val db = RetrofitClient.getRetrofitService().create(Video::class.java)

    fun getVideo(token: String) {
        db.getVideo(token).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _video.value = response.body()
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                ErrorObserver.showErrorMessage(t.message.toString())
            }

        })
    }
}