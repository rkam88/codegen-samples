package ${PACKAGE_NAME}

internal object ${NAME} {
    sealed class UiState {
        data object Loading : UiState()
        data class Error(val exception: Exception) : UiState()
        // TODO: Update screen state
        data class Content(val data: Any = Unit) : UiState()
    }

    sealed class UiEvent {
        data object OnBackTap : UiEvent()
        data object OnRetryTap : UiEvent()
    }

    sealed class Event {
        data object NavigateBack : Event()
    }
}
