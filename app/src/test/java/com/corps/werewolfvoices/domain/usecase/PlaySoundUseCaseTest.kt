package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.audio.AudioPlayer
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlaySoundUseCaseTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var useCase: PlaySoundUseCase
    private val audioPlayer: AudioPlayer = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = PlaySoundUseCase(audioPlayer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `invoke should return success when audio player succeeds`() {
        // Given
        val resId = 1
        every { audioPlayer.play(resId) } returns Result.success(Unit)

        // When
        val result = useCase(resId)

        // Then
        assertTrue(result.isSuccess)
        verify(exactly = 1) { audioPlayer.play(resId) }
    }

    @Test
    fun `invoke should return failure when audio player fails`() {
        // Given
        val resId = 2
        every { audioPlayer.play(resId) } returns Result.failure(Exception("Playback error"))

        // When
        val result = useCase(resId)

        // Then
        assertTrue(result.isFailure)
        verify(exactly = 1) { audioPlayer.play(resId) }
    }
}
