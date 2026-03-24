package com.corps.werewolfvoices.data.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.corps.werewolfvoices.domain.audio.AudioPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExoAudioPlayer @Inject constructor(
    @ApplicationContext private val context: Context
) : AudioPlayer {
    private val player = ExoPlayer.Builder(context).build()

    override fun play(rawResId: Int): Result<Unit> {
        return try {
            val uri = "android.resource://${context.packageName}/$rawResId"
            player.apply {
                stop() // Ensure only one sound plays at a time
                setMediaItem(MediaItem.fromUri(uri))
                prepare()
                play()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun stop() {
        player.stop()
    }

    override fun release() {
        player.release()
    }
}
