package com.example.tinn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinn.data.modelForJSON.ProfileModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.data.modelForJSON.ResponceModel
import com.example.tinn.data.networkService.RetrofitClient
import com.example.tinn.data.networkService.UserService
import retrofit2.Call
import java.text.SimpleDateFormat
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _requestStatus = MutableLiveData<String>("")
    val requestStatus: LiveData<String>
        get() = _requestStatus

    private val _userInfo = MutableLiveData<ResponceDataUserModel?>(null)
    val userInfo: LiveData<ResponceDataUserModel?>
        get() = _userInfo

    private val db = RetrofitClient.getRetrofitService().create(UserService::class.java)

    fun putUserInfo(
        login: String,
        firstName: String,
        secondName: String,
        sex: String,
        phone: String,
        dateOfBirth: String,
    ) {
        _requestStatus.value = "LOADING"
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

            db.putUser(model).enqueue(object : Callback<ResponceDataUserModel> {
                override fun onResponse(
                    call: Call<ResponceDataUserModel>,
                    response: Response<ResponceDataUserModel>
                ) {
                    _requestStatus.value = "OK"
                }

                override fun onFailure(call: Call<ResponceDataUserModel>, t: Throwable) {
                    _requestStatus.value = ""
                    ErrorObserver.showErrorMessage(t.message.toString())
                }
            })

        } else {
            _requestStatus.value = ""
            ErrorObserver.showErrorMessage("Дата рождения не валидна")
        }

    }

    fun getUserInfo() {
        db.getUser().enqueue(object : Callback<ResponceModel<ResponceDataUserModel>> {
            override fun onResponse(
                call: Call<ResponceModel<ResponceDataUserModel>>,
                response: Response<ResponceModel<ResponceDataUserModel>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        if (it.status) {
                            _userInfo.value = body.data
                        } else {
                            /*body.errors.forEach {
                                ErrorObserver.showErrorMessage(it.toString())
                            }*/
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponceModel<ResponceDataUserModel>>, t: Throwable) {
                ErrorObserver.showErrorMessage(t.message.toString())
            }
        })
    }
}