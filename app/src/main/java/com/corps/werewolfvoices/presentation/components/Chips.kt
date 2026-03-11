package com.corps.werewolfvoices.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.corps.werewolfvoices.domain.model.CharacterType

@Composable
fun Chips(
    character: CharacterType,
    modifier: Modifier = Modifier
) {
    val borderColor = when (character) {
        CharacterType.WEREWOLF -> androidx.compose.ui.graphics.Color.Red
        CharacterType.VILLAGER -> androidx.compose.ui.graphics.Color.Green
        CharacterType.SPECIAL -> androidx.compose.ui.graphics.Color.Blue
    }

}
