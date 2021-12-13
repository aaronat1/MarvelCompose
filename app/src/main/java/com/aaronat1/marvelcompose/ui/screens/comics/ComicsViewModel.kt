package com.aaronat1.marvelcompose.ui.screens.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.aaronat1.marvelcompose.data.entities.Comic
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComicsViewModel: ViewModel() {

    val state = Comic.Format.values().associate { it to MutableStateFlow(UiState()) }

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        if (uiState.value.comics.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(comics = ComicsRepository.get(format))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comics: Result<List<Comic>> = emptyList<Comic>().right()
    )
}