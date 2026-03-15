package com.corps.werewolfvoices.domain.repository

import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult

interface CharacterRepository {
    fun getCharacter(): DataResult<List<Character>>
}
