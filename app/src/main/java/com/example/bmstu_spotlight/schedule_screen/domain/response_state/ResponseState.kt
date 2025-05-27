package com.example.bmstu_spotlight.schedule_screen.domain.response_state

data class ResponseState<out T> (val state: DataState, val data: T?, val message: String?) {

    enum class DataState {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): ResponseState<T> {
            return ResponseState(DataState.LOADING, null, "Загрузка данных")
        }

        fun <T> success(data: T?): ResponseState<T> {
            return ResponseState(DataState.SUCCESS, data, "Данные успешно получены")
        }

        fun <T> error(): ResponseState<T> {
            return ResponseState(DataState.ERROR, null,  "Ошибка получения данных")
        }
    }
}