package com.corps.werewolfvoices.ui.characterlist

import android.util.Log
import app.cash.turbine.test
import com.corps.werewolfvoices.domain.audio.AudioPlayer
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.usecase.GetCharactersUseCase
import com.corps.werewolfvoices.domain.usecase.PlaySoundUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val playSoundUseCase: PlaySoundUseCase = mockk()
    private val audioPlayer: AudioPlayer = mockk(relaxed = true)

    private lateinit var viewModel: CharacterListViewModel

    private val fakeCharacters = listOf(
        Character(1, "Werewolf", CharacterType.WEREWOLF, "Kills a villager each night.", 0, 0),
        Character(2, "Villager", CharacterType.VILLAGER, "No special power.", 0, 0)
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    private fun createViewModel() = CharacterListViewModel(getCharactersUseCase, playSoundUseCase, audioPlayer)

    @Test
    fun `uiState emits loading true as initial value`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Success(emptyList())
        viewModel = createViewModel()

        viewModel.uiState.test {
            assertTrue(awaitItem().isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits characters and clears loading on success`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Success(fakeCharacters)
        viewModel = createViewModel()

        viewModel.uiState.test {
            awaitItem() // initial loading state

            advanceUntilIdle()

            val loaded = awaitItem()
            assertEquals(false, loaded.isLoading)
            assertEquals(fakeCharacters, loaded.characters)
            assertNull(loaded.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits error message and clears loading on failure`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Error("Something went wrong", null)
        viewModel = createViewModel()

        viewModel.uiState.test {
            awaitItem() // initial loading state

            advanceUntilIdle()

            val errorState = awaitItem()
            assertEquals(false, errorState.isLoading)
            assertTrue(errorState.characters.isEmpty())
            assertNotNull(errorState.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `playCharacterSound stops player and delegates to use case on success`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Success(emptyList())
        every { playSoundUseCase(any()) } returns Result.success(Unit)
        viewModel = createViewModel()

        viewModel.onIntent(CharacterListAction.PlayCharacterSound(fakeCharacters[0]))

        verify(exactly = 1) { audioPlayer.stop() }
        verify(exactly = 1) { playSoundUseCase(fakeCharacters[0].soundResId) }
    }

    @Test
    fun `playCharacterSound emits ShowErrorMessage event when play fails`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Success(emptyList())
        every { playSoundUseCase(any()) } returns Result.failure(Exception("Playback error"))
        viewModel = createViewModel()

        viewModel.events.test {
            viewModel.onIntent(CharacterListAction.PlayCharacterSound(fakeCharacters[0]))
            advanceUntilIdle()

            val event = awaitItem()
            assertTrue(event is CharacterListEvent.ShowErrorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onCleared releases the audio player`() = runTest(testDispatcher) {
        coEvery { getCharactersUseCase() } returns DataResult.Success(emptyList())
        viewModel = createViewModel()

        viewModel.javaClass.getDeclaredMethod("onCleared").apply {
            isAccessible = true
            invoke(viewModel)
        }

        verify(exactly = 1) { audioPlayer.release() }
    }
}
