package ${PACKAGE_NAME}

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ${NAME}ViewModel @Inject constructor(
    private val uiConverter: ${NAME}UiConverter,
) : ViewModel() {

    private val _state = MutableStateFlow<${NAME}.UiState>(${NAME}.UiState.Loading)
    val state = _state.asStateFlow()

    private val eventChannel = Channel<${NAME}.Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        loadData(force = false)
    }

    fun onUiEvent(event: ${NAME}.UiEvent) {
        when (event) {
            is ${NAME}.UiEvent.OnBackTap -> navigateBack()
            is ${NAME}.UiEvent.OnRetryTap -> loadData(force = true)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch { eventChannel.send(${NAME}.Event.NavigateBack) }
    }

    private fun loadData(force: Boolean) {
        _state.value = ${NAME}.UiState.Loading

        viewModelScope.launch {
            // TODO: Add repository call and update state with result
        }
    }
}
