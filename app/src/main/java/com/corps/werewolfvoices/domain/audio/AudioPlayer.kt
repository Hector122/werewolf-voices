package com.corps.werewolfvoices.domain.audio

interface AudioPlayer {
    fun play(rawResId: Int)
    fun stop()
    fun release()
}
