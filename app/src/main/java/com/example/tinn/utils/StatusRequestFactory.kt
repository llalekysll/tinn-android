package com.example.tinn.utils


object StatusRequestFactory {
    enum class StatusType() {
        NONE,
        LOADING,
        SUCCESS
    }

    data class StatusRequestModel<T>(
        val status: StatusType = StatusType.NONE,
        val body: T? = null,
        val key: String = ""
    )

    fun <T> getSuccess(body: T?, key: String = "") = StatusRequestModel(StatusType.SUCCESS, body, key)

    fun getNone() = StatusRequestModel<Any>()

    fun getLoading() = StatusRequestModel<Any>(StatusType.LOADING)
}