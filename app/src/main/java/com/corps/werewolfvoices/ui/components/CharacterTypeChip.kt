package com.corps.werewolfvoices.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.corps.werewolfvoices.domain.model.CharacterType

@Composable
fun CharacterTypeChip(
    type: CharacterType,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(4.dp)
    val (backgroundColor, textColor) = when (type) {
        CharacterType.WEREWOLF -> Color.Red.copy(alpha = 0.2f) to Color.Red
        CharacterType.SPECIAL -> Color.Blue.copy(alpha = 0.2f) to Color.Blue
        CharacterType.VILLAGER -> Color.Green.copy(alpha = 0.2f) to Color.Green
    }

    Box(
        modifier = modifier
            .border(width = 1.dp, color = textColor, shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)

    ) {
        Text(
            text = type.name.lowercase().replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@PreviewLightDark
@Composable
private fun CharacterTypeChipPreview() {
    CharacterTypeChip(type = CharacterType.WEREWOLF)
}
