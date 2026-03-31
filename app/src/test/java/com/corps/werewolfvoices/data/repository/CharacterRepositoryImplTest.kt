package com.corps.werewolfvoices.data.repository

import com.corps.werewolfvoices.domain.datasource.LocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
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


        coVerify(exactly = 1) { dataSource.getCharacters() }
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

    @Test
    fun `getCharacter should handle empty list`() = runTest(testDispatcher) {
        coEvery { dataSource.getCharacters() } returns DataResult.Success(emptyList())
        val result = repository.getCharacter()
        assertEquals(DataResult.Success(emptyList<Character>()), result)
    }

    @Test
    fun `getCharacter should handle thrown exception`() = runTest(testDispatcher) {
        coEvery { dataSource.getCharacters() } throws RuntimeException("DB crashed")
        // assert whatever your repository is supposed to do — wrap it? rethrow?
    }
}
