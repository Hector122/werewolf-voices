package com.corps.werewolfvoices.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.corps.werewolfvoices.domain.model.CharacterType

data class CharacterUi(
    val id: Int,
    val name: String,
    val type: CharacterType,
    val description: String,
    @DrawableRes val imageRes: Int,
    @RawRes val soundRes: Int
)
