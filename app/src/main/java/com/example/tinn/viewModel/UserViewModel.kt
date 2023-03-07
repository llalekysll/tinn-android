package com.example.tinn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinn.data.modelForJSON.ProfileModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.data.networkService.RetrofitClient
import com.example.tinn.data.networkService.UserService
import retrofit2.Call
import java.text.SimpleDateFormat
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _requestIsSuccess = MutableLiveData<Boolean>(null)
    val requestIsSuccess: LiveData<Boolean>
        get() = _requestIsSuccess

    private val db = RetrofitClient.getRetrofitService().create(UserService::class.java)

    fun putUserInfo(
        login: String,
        firstName: String,
        secondName: String,
        sex: String,
        phone: String,
        dateOfBirth: String,
        token: String?,
    ) {
        _requestIsSuccess.value = null
        val date = SimpleDateFormat("ddMMyyyy").parse(dateOfBirth)

        if (date != null) {
            val model = ProfileModel(
                login = login,
                firstName = firstName,
                secondName = secondName,
                genderId = if (sex == "Мужской") 0 else 1,
                phone = phone,
                dataOfBirth = date
            )

            db.putUser(model, token!!).enqueue(object : Callback<ResponceDataUserModel> {
                override fun onResponse(
                    call: Call<ResponceDataUserModel>,
                    response: Response<ResponceDataUserModel>
                ) {
                    _requestIsSuccess.value = true
                }

                override fun onFailure(call: Call<ResponceDataUserModel>, t: Throwable) {
                    _requestIsSuccess.value = false
                    ErrorObserver.showErrorMessage(t.message.toString())
                }
            })

        } else {
            _requestIsSuccess.value = false
            ErrorObserver.showErrorMessage("Дата рождения не валидна")
        }

    }
}