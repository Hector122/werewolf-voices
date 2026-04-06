package com.corps.werewolfvoices.data.repository

import com.corps.werewolfvoices.domain.datasource.LocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterRepository {

    override suspend fun getCharacters(): DataResult<List<Character>> {
        return withContext(dispatcher) { dataSource.getCharacters() }
    }
}
