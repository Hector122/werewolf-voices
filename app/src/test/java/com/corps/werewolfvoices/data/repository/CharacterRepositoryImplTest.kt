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
    private lateinit var dataSource: LocalDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        dataSource = mockk()
        Dispatchers.setMain(testDispatcher)
        repository = CharacterRepositoryImpl(dataSource, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getCharacter returns success when data source succeeds`() = runTest(testDispatcher) {
        val characters = listOf(
            Character(
                1, "Werewolf", CharacterType.WEREWOLF,
                "Desc", 0, 0
            )
        )
        val expected = DataResult.Success(characters)
        coEvery { dataSource.getCharacters() } returns expected

        val result = repository.getCharacters()

        assertEquals(expected, result)
        coVerify(exactly = 1) { dataSource.getCharacters() }
    }

    @Test
    fun `getCharacter returns error when data source returns error`() = runTest(testDispatcher) {
        val exception = Exception("DB error")
        val expected = DataResult.Error("Error message", exception)
        coEvery { dataSource.getCharacters() } returns expected

        val result = repository.getCharacters()

        assertEquals(expected, result)
        coVerify(exactly = 1) { dataSource.getCharacters() }
    }

    @Test
    fun `getCharacter returns success with empty list`() = runTest(testDispatcher) {
        val expected = DataResult.Success(emptyList<Character>())
        coEvery { dataSource.getCharacters() } returns expected

        val result = repository.getCharacters()

        assertEquals(expected, result)
    }

    //TODO:
//    @Test
//    fun `getCharacter should handle thrown exception`() = runTest(testDispatcher) {
//        coEvery { dataSource.getCharacters() } throws RuntimeException("DB crashed")
//        // assert whatever your repository is supposed to do — wrap it? rethrow?
//    }
}
