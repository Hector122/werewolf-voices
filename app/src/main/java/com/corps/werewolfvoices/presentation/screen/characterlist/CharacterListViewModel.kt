package com.corps.werewolfvoices.presentation.screen.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.usecase.GetCharactersUseCase
import com.corps.werewolfvoices.domain.usecase.PlaySoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val playSoundUseCase: PlaySoundUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState = _uiState.onStart {
        fetchCharacters()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = CharacterListUiState()
    )

    private fun fetchCharacters() {
        _uiState.value = _uiState.value.copy(isLoading = true)

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
}
