package com.corps.werewolfvoices.data.source

import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDataSourceImpTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var dataSource: LocalDataSourceImp

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        dataSource = LocalDataSourceImp()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCharacters returns DataResult Success`() = runTest(testDispatcher) {
        val result = dataSource.getCharacters()

        assertTrue(result is DataResult.Success)
    }

    @Test
    fun `getCharacters returns exactly 7 characters`() = runTest(testDispatcher) {
        val result = dataSource.getCharacters() as DataResult.Success

        assertEquals(7, result.value.size)
    }

    @Test
    fun `getCharacters returns first character with correct name and type`() = runTest(testDispatcher) {
        // Given / When
        val result = dataSource.getCharacters() as DataResult.Success
        val first = result.value.first()

        // Then
        assertEquals("Werewolf", first.name)
        assertEquals(CharacterType.WEREWOLF, first.type)
    }
}
