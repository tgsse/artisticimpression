package com.ix.artisticimpression.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ix.artisticimpression.R
import com.ix.artisticimpression.data.art.ArtRepository
import com.ix.artisticimpression.data.art.local.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArtState(
    val isLoading: Boolean = true,
    val dailyArt: Art? = null
)

sealed class ArtEvent {
    object Init : ArtEvent()
}

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val artRepository: ArtRepository,
    application: Application
) : AndroidViewModel(application = application) {

    private val uiEventChannel = Channel<UiEvent>()
    val uiEvents = uiEventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(ArtState())
    val state = _state.asStateFlow()

    fun onEvent(event: ArtEvent) {
        when (event) {
            is ArtEvent.Init -> init()
        }
    }

    private fun init() {
        loadDailyArt()
    }

    private fun loadDailyArt() {
        viewModelScope.launch(Dispatchers.IO) {
            val art = artRepository.local.loadDailyArt().firstOrNull()
            if (art != null) {
                _state.update { s ->
                    s.copy(
                        dailyArt = art,
                        isLoading = false
                    )
                }
            } else {
                fetchDailyArt()
            }
        }
    }

    private fun saveDailyArt(art: Art) {
        viewModelScope.launch(Dispatchers.IO) {
            artRepository.local.saveDailyArt(art)
        }
    }

    private fun fetchDailyArt() {
        viewModelScope.launch {
            try {
                _state.update { uiState -> uiState.copy(isLoading = true) }
                val art = artRepository.remote.fetchDailyArt()
                _state.update { uiState -> uiState.copy(dailyArt = art) }
                saveDailyArt(art)
            } catch (e: Exception) {
                showError(e)
            } finally {
                _state.update { uiState -> uiState.copy(isLoading = false) }
            }
        }
    }

    private fun showError(e: Exception) {
        Log.e(this.javaClass.toString(), e.toString())
        viewModelScope.launch {
            uiEventChannel.send(
                element = UiEvent.ShowMessage(
                    message = e.message
                        ?: getApplication<Application>().resources.getString(R.string.error_default)
                )
            )
        }
    }

    sealed class UiEvent {
        data class ShowMessage(val message: String) : UiEvent()

//        object NavigateToDetail : UiEvent()
    }
}
