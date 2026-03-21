package com.corps.werewolfvoices.data.audio

import android.content.Context
import com.corps.werewolfvoices.domain.audio.AudioPlayer

class MediaPlayer(private val context: Context) : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(rawResId: Int) {
        mediaPlayer?.stop()
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, rawResId)
        mediaPlayer?.start()
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }
}
