package com.aaronat1.marvelcompose.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.aaronat1.marvelcompose.data.entities.Event
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.repositories.EventsRepository
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

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Event>> = emptyList<Event>().right()
    )
}