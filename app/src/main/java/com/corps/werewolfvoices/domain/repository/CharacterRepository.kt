package com.corps.werewolfvoices.domain.repository

import com.corps.werewolfvoices.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(): Flow<List<Character>>
}
