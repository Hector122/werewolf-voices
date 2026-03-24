package com.corps.werewolfvoices.presentation.screen.characterlist

import com.corps.werewolfvoices.domain.model.Character

sealed class CharacterListAction {
    data class PlayCharacterSound(val character: Character) : CharacterListAction()
}
