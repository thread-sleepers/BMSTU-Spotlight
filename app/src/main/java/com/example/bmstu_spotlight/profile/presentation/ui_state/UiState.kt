package com.example.bmstu_spotlight.profile.presentation.ui_state

data class UiState<out T>(
    val result: Result<T>,
    val isLoading: Boolean = false
) {
    val data: T? get() = result.getOrNull()
    val error: Throwable? get() = result.exceptionOrNull()

    companion object {
        fun <T> loading(): UiState<T> =
            UiState(Result.failure(IllegalStateException("Loading")), isLoading = true)

        fun<T> success(data: T): UiState<T> = UiState(Result.success(data))

        fun<T> error(exception: Throwable): UiState<T> = UiState(Result.failure(exception))
    }
}