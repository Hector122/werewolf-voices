package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersUseCaseTest {

    private lateinit var useCase: GetCharactersUseCase
    private val repository: CharacterRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetCharactersUseCase(repository)
    }

    @Test
    fun `invoke should return result from repository`() = runTest {
        // Given
        val characters = listOf(
            Character(1, "Werewolf", CharacterType.WEREWOLF, "Desc", 0, 0)
        )
        val expectedResult = DataResult.Success(characters)
        coEvery { repository.getCharacter() } returns expectedResult

        // When
        val result = useCase()

        // Then
        assertEquals(expectedResult, result)
    }
}
