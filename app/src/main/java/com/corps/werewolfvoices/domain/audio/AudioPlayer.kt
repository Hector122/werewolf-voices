package com.corps.werewolfvoices.domain.audio

interface AudioPlayer {
    fun play(rawResId: Int): Result<Unit>
    fun stop()
    fun release()
}
