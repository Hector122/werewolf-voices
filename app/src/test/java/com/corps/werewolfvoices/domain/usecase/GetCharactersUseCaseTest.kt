package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersUseCaseTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var useCase: GetCharactersUseCase
    private val repository: CharacterRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetCharactersUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return success with characters when repository succeeds`() = runTest {
        // Given
        val characters = listOf(
            Character(1, "Werewolf", CharacterType.WEREWOLF, "Desc", 0, 0)
        )
        val expectedResult = DataResult.Success(characters)
        coEvery { repository.getCharacters() } returns expectedResult

        // When
        val result = useCase()

        // Then
        assertEquals(expectedResult, result)
        coVerify(exactly = 1) { repository.getCharacters() }
        confirmVerified(repository)
    }

    @Test
    fun `invoke should return error when repository fails`() = runTest {
        // Given
        val expectedResult = DataResult.Error(message = "", throwable = Exception("Network error"))
        coEvery { repository.getCharacters() } returns expectedResult

        // When
        val result = useCase()

        // Then
        assertEquals(expectedResult, result)
        coVerify(exactly = 1) { repository.getCharacters() }
        confirmVerified(repository)
    }

    @Test
    fun `invoke should return empty success when repository returns no characters`() = runTest {
        // Given
        val expectedResult = DataResult.Success(emptyList<Character>())
        coEvery { repository.getCharacters() } returns expectedResult

        // When
        val result = useCase()

        // Then
        assertEquals(expectedResult, result)
        coVerify(exactly = 1) { repository.getCharacters() }
        confirmVerified(repository)
    }
}
