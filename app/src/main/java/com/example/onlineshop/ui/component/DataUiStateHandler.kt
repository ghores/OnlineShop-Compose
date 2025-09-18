package com.example.onlineshop.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.onlineshop.ui.state.DataUiState

@Composable
fun <T> DataUiStateHandler(
    state: DataUiState<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { Loading(modifier) },
    errorContent: @Composable (String) -> Unit = { ErrorBox(it, modifier = modifier) },
    successContent: @Composable (List<T>) -> Unit
) {
    when {
        state.isLoading -> loadingContent()
        state.error != null -> errorContent(state.error)
        state.data != null -> successContent(state.data)
    }
}