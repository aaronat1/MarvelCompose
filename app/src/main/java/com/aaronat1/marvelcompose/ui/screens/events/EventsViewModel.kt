package com.aaronat1.marvelcompose.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronat1.marvelcompose.data.entities.Event
import com.aaronat1.marvelcompose.data.repositories.EventsRepository
import com.aaronat1.marvelcompose.ui.screens.characters.CharactersViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    private var _state = MutableStateFlow(EventsViewModel.UiState())
    val state: StateFlow<EventsViewModel.UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = EventsRepository.get())
        }
    }

    data class UiState(val loading: Boolean = false, val items: List<Event> = emptyList())
}