package com.corps.werewolfvoices.domain.datasource

import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult

interface CharacterLocalDataSource {
    suspend fun getCharacters(): DataResult<List<Character>>
}
