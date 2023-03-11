package com.example.tinn.viewModel

import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.tinn.data.modelForJSON.*
import com.example.tinn.data.networkService.RetrofitClient
import com.example.tinn.data.networkService.UserService
import com.example.tinn.utils.StatusRequestFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

typealias status = StatusRequestFactory.StatusRequestModel<Any>

class UserViewModel : ViewModel() {

    private val _requestStatus = MutableLiveData<status>(StatusRequestFactory.getNone())
    val requestStatus: LiveData<status>
        get() = _requestStatus

    private val _userInfo = MutableLiveData<ResponceDataUserModel?>(null)
    val userInfo: LiveData<ResponceDataUserModel?>
        get() = _userInfo

    private val db = RetrofitClient.getRetrofitService().create(UserService::class.java)

    fun getGender() {
        db.getGenders().enqueue(object : Callback<ResponceModel<ResponceDataGendersModel>> {
            override fun onResponse(
                call: Call<ResponceModel<ResponceDataGendersModel>>,
                response: Response<ResponceModel<ResponceDataGendersModel>>
            ) {
                _requestStatus.value = StatusRequestFactory.getSuccess(response.body())
            }

            override fun onFailure(
                call: Call<ResponceModel<ResponceDataGendersModel>>,
                t: Throwable
            ) {
                _requestStatus.value = StatusRequestFactory.getNone()
                ErrorObserver.showErrorMessage("Дата рождения не валидна")
            }

        })
    }

    fun putUserInfo(
        login: String,
        firstName: String,
        secondName: String,
        sex: Int,
        phone: String,
        dateOfBirth: String,
    ) {
        _requestStatus.value = StatusRequestFactory.getLoading()
        val date = SimpleDateFormat("ddMMyyyy").parse(dateOfBirth)

        if (date != null) {
            val model = ProfileModel(
                login = login,
                firstName = firstName,
                secondName = secondName,
                genderId = sex,
                phone = phone,
                dataOfBirth = date
            )

            db.putUser(model).enqueue(object : Callback<ResponceDataUserModel> {
                override fun onResponse(
                    call: Call<ResponceDataUserModel>,
                    response: Response<ResponceDataUserModel>
                ) {
                    _requestStatus.value = StatusRequestFactory.getSuccess(response.body())
                }

                override fun onFailure(call: Call<ResponceDataUserModel>, t: Throwable) {
                    _requestStatus.value = StatusRequestFactory.getNone()
                    ErrorObserver.showErrorMessage(t.message.toString())
                }
            })

        } else {
            _requestStatus.value = StatusRequestFactory.getNone()
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

    fun loadAvatar(uri: Uri) {
        _requestStatus.value = StatusRequestFactory.getLoading()
        val file = uri.toFile()
        val requestBody = file.asRequestBody()
        val partFile = MultipartBody.Part.createFormData("image", file.name, requestBody)
        db.loadAvatar(partFile).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _requestStatus.value = StatusRequestFactory.getSuccess(response.body())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                ErrorObserver.showErrorMessage(t.message.toString())
            }

        })
    }
}