package com.corps.werewolfvoices.presentation.screen.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corps.werewolfvoices.domain.audio.AudioPlayer
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.usecase.GetCharactersUseCase
import com.corps.werewolfvoices.domain.usecase.PlaySoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val playSoundUseCase: PlaySoundUseCase,
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> = _uiState.onStart {
        loadCharacters()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CharacterListUiState()
    )

    // Use a Channel for events (Buffered ensures events aren't lost if the UI is busy)
    private val _events = Channel<CharacterListUiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onIntent(intent: CharacterListAction) {
        when (intent) {
            is CharacterListAction.PlayCharacterSound -> playCharacterSound(intent)
        }
    }

    private fun playCharacterSound(intent: CharacterListAction.PlayCharacterSound) {
        audioPlayer.stop()
        val result = playSoundUseCase(intent.character.soundResId)
        result.onFailure { error ->
            viewModelScope.launch {
                _events.send(CharacterListUiEvent.ShowErrorMessage(error.message ?: "Unknown error playing sound"))
            }
        }
    }

    private fun loadCharacters() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = getCharactersUseCase.invoke()

            _uiState.update {
                when (result) {
                    is DataResult.Success -> {
                        it.copy(
                            isLoading = false,
                            characters = result.value,
                            errorMessage = null
                        )
                    }

                    is DataResult.Error -> {
                        it.copy(
                            isLoading = false,
                            characters = emptyList(),
                            errorMessage = result.message
                        )
                    }

                    else -> it.copy(isLoading = false, errorMessage = null)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }
}
