package com.corps.werewolfvoices.data.datasource

import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult

class CharacterLocalDataSource() {
    suspend fun getCharacters(): DataResult<List<Character>> {
        // TODO: no implementation
        //return dao.getCharacters()
        return DataResult.Success(emptyList())
    }
}
