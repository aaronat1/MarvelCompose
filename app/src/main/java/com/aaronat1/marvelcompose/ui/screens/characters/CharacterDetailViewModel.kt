package com.aaronat1.marvelcompose.ui.screens.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.right
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.repositories.CharactersRepository
import com.aaronat1.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(character = CharactersRepository.find(id))
        }
    }

    data class  UiState(
        val loading: Boolean = false,
        val character: Result<Character?> = Either.Right(null)
    )
}