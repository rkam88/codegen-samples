package dev.roman.kamyshnikov.codegen.samples.core.ui.componenets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UiComponentError(
    exception: Exception,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Whoops, something went wrong.\n\n$exception")
        Button(onRetry) { Text("Retry") }
    }
}
