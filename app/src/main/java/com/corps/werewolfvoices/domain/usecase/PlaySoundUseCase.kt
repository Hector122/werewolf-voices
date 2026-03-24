package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.audio.AudioPlayer
import javax.inject.Inject


class PlaySoundUseCase @Inject constructor(
    private val audioPlayer: AudioPlayer
) {
    operator fun invoke(resId: Int): Result<Unit> {
        return audioPlayer.play(resId)
    }
}
