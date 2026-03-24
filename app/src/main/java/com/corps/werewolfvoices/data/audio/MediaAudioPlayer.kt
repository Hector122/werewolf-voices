package com.corps.werewolfvoices.data.audio

import android.content.Context
import android.media.MediaPlayer
import com.corps.werewolfvoices.domain.audio.AudioPlayer

class MediaAudioPlayer(private val context: Context) : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(rawResId: Int): Result<Unit> {
        try {
            mediaPlayer?.stop()

            mediaPlayer = MediaPlayer.create(context, rawResId).apply {
                setOnCompletionListener {
                    release()
                    mediaPlayer = null
                }
                start()
            }

            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun stop() {
        mediaPlayer?.apply {
            if (isPlaying) stop()
            this.release()
        }
        mediaPlayer = null
    }

    override fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
