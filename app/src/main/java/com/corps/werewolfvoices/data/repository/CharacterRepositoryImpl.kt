package com.corps.werewolfvoices.data.repository

import com.corps.werewolfvoices.domain.datasource.CharacterLocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    val dataSource: CharacterLocalDataSource,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterRepository {

    override suspend fun getCharacter(): DataResult<List<Character>> {
        return withContext(dispatcher) { dataSource.getCharacters() }
    }
}
