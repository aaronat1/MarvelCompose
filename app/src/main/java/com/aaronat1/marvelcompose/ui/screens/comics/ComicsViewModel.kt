package com.aaronat1.marvelcompose.ui.screens.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronat1.marvelcompose.data.entities.Comic
import com.aaronat1.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComicsViewModel: ViewModel() {

    val state = Comic.Format.values().associate { it to MutableStateFlow(UiState()) }

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        print("Aqui1 ${uiState.value.comics.size}")
        if (uiState.value.comics.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(comics = ComicsRepository.get(format))
            print("Aqui2 ${uiState.value.comics.size}")
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comics: List<Comic> = emptyList()
    )
}