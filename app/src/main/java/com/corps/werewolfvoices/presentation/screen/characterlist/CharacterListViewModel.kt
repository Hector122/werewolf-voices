package com.corps.werewolfvoices.presentation.screen.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corps.werewolfvoices.domain.usecase.GetCharactersUseCase
import com.corps.werewolfvoices.domain.usecase.PlaySoundUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class CharacterListViewModel(
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
            val result = getCharactersUseCase().invoke()


        }
    }

}
