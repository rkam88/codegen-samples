package ${PACKAGE_NAME}

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.roman.kamyshnikov.codegen.samples.core.ui.componenets.UiComponentError
import dev.roman.kamyshnikov.codegen.samples.core.ui.theme.CodegenSamplesTheme

private typealias onUiEvent = (${NAME}.UiEvent) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ${NAME}Screen(
    state: ${NAME}.UiState,
    onUiEvent: onUiEvent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") }, // TODO: update title
                navigationIcon = {
                    IconButton(onClick = { onUiEvent(${NAME}.UiEvent.OnBackTap) }) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "navigate back",
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        PullToRefreshBox(
            isRefreshing = state is ${NAME}.UiState.Loading,
            onRefresh = { onUiEvent(${NAME}.UiEvent.OnRetryTap) },
            modifier = Modifier.padding(contentPadding)
        ) {
            when (state) {
                is ${NAME}.UiState.Loading -> Placeholder()

                is ${NAME}.UiState.Error -> UiComponentError(
                    exception = state.exception,
                    onRetry = { onUiEvent(${NAME}.UiEvent.OnRetryTap) },
                )

                is ${NAME}.UiState.Content -> Content(state, onUiEvent)
            }
        }
    }
}

@Composable
private fun Content(
    state: ${NAME}.UiState.Content,
    onUiEvent: onUiEvent,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement Content state
}

@Composable
private fun Placeholder(modifier: Modifier = Modifier) {
    // TODO: Update placeholder
}

//region: preview
@Preview(showBackground = true)
@Composable
private fun ${NAME}ScreenPreview(
    @PreviewParameter(${NAME}ScreenProvider::class) state: ${NAME}.UiState
) {
    CodegenSamplesTheme {
        ${NAME}Screen(state = state, onUiEvent = {})
    }
}

private class ${NAME}ScreenProvider : PreviewParameterProvider<${NAME}.UiState> {
    override val values: Sequence<${NAME}.UiState>
        get() = TODO("Add previews")
}
//endregion
