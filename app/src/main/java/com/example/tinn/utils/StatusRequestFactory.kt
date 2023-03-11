package com.example.tinn.utils


object StatusRequestFactory {
    enum class StatusType() {
        NONE,
        LOADING,
        SUCCESS
    }

    data class StatusRequestModel<T>(
        val status: StatusType = StatusType.NONE,
        val body: T? = null
    )

    fun <T> getSuccess(body: T?) = StatusRequestModel(StatusType.SUCCESS, body)

    fun getNone() = StatusRequestModel<Any>()

    fun getLoading() = StatusRequestModel<Any>(StatusType.LOADING)
}