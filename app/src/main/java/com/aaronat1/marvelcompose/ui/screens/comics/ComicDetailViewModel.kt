package com.aaronat1.marvelcompose.ui.screens.comics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.entities.Comic
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.repositories.ComicsRepository
import com.aaronat1.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(comic = ComicsRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comic: Result<Comic?> = Either.Right(null)
    )
}