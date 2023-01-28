package com.example.bmsAssignment.base.api

data class NetworkCall<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): NetworkCall<T> {
            return NetworkCall(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): NetworkCall<T> {
            return NetworkCall(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): NetworkCall<T> {
            return NetworkCall(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}