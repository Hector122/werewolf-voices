package com.corps.werewolfvoices.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Character(
    val id: Int,
    val name: String,
    val type: CharacterType,
    val description: String,
    @DrawableRes val imageRes: Int,
    @RawRes val soundRes: Int
)
