package com.aaronat1.marvelcompose.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaronat1.marvelcompose.data.entities.Event
import com.aaronat1.marvelcompose.data.repositories.EventsRepository
import com.aaronat1.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(EventDetailViewModel.UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(event = EventsRepository.find(id))
        }
    }

    data class  UiState(
        val loading: Boolean = false,
        val event: Event? = null
    )
}