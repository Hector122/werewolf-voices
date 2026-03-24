package com.corps.werewolfvoices.presentation.screen.characterlist

import com.corps.werewolfvoices.presentation.common.UiText

sealed interface CharacterListEvent {
    data class ShowErrorMessage(val message: UiText) : CharacterListEvent
}
