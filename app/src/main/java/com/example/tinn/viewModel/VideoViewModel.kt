package com.example.tinn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinn.data.modelForJSON.ResponceModel
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
        db.getVideo(token).enqueue(object : Callback<ResponceModel<Video>> {
            override fun onResponse(call: Call<ResponceModel<Video>>, response: Response<ResponceModel<Video>>) {
                _video.value = response.body()?.data
            }

            override fun onFailure(call: Call<ResponceModel<Video>>, t: Throwable) {
                ErrorObserver.showErrorMessage(t.message.toString())
            }

        })
    }
}