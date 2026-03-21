package com.corps.werewolfvoices.di

import com.corps.werewolfvoices.domain.audio.AudioPlayer
import com.corps.werewolfvoices.data.audio.ExoAudioPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AudioModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAudioPlayer(
        exoAudioPlayer: ExoAudioPlayer
    ): AudioPlayer
}
