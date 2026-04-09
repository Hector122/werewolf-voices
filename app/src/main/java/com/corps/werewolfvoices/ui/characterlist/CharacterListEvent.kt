package com.corps.werewolfvoices.ui.characterlist

import com.corps.werewolfvoices.ui.common.UiText

sealed interface CharacterListEvent {
    data class ShowErrorMessage(val message: UiText) : CharacterListEvent
}
