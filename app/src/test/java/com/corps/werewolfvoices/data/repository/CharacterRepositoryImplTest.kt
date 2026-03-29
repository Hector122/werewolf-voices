package com.corps.werewolfvoices.data.repository

import com.corps.werewolfvoices.domain.datasource.LocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {

    private lateinit var repository: CharacterRepositoryImpl
    private val dataSource: LocalDataSource = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        repository = CharacterRepositoryImpl(dataSource, testDispatcher)
    }

    @Test
    fun `getCharacter should return success when data source returns success`() = runTest(testDispatcher) {
        // Given
        val characters = listOf(
            Character(1, "Werewolf", CharacterType.WEREWOLF, "Desc", 0, 0)
        )
        val expectedResult = DataResult.Success(characters)
        coEvery { dataSource.getCharacters() } returns expectedResult

        // When
        val result = repository.getCharacter()

        // Then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getCharacter should return error when data source returns error`() = runTest(testDispatcher) {
        // Given
        val expectedResult = DataResult.Error("Error message", Exception())
        coEvery { dataSource.getCharacters() } returns expectedResult

        // When
        val result = repository.getCharacter()

        // Then
        assertEquals(expectedResult, result)
    }
}
