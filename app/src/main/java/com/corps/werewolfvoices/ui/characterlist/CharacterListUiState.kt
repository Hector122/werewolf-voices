package com.corps.werewolfvoices.ui.characterlist

import com.corps.werewolfvoices.domain.model.Character
import javax.annotation.concurrent.Immutable

@Immutable
data class CharacterListUiState(
    val isLoading: Boolean = true,
    val characters: List<Character> = emptyList(),
    val errorMessage: String? = null
)
