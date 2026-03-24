package com.corps.werewolfvoices.presentation.screen.characterlist

sealed interface CharacterListUiEvent {
    data class ShowErrorMessage(val message: String) : CharacterListUiEvent
}
