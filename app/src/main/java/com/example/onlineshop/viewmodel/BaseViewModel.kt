package com.example.onlineshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.ui.state.DataUiState
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected fun <T> loadData(
        stateSetter: (DataUiState<T>) -> Unit,
        loader: suspend () -> ApiResponse<T>
    ) {
        stateSetter(DataUiState(isLoading = true))
        viewModelScope.launch {
            try {
                val response = loader()
                if (response.status != "OK") {
                    throw Exception(response.message)
                }
                stateSetter(DataUiState(data = response.data))
            } catch (e: Exception) {
                stateSetter(DataUiState(error = e.message))
            }
        }
    }
}