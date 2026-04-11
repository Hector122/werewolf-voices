package com.corps.werewolfvoices.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.corps.werewolfvoices.domain.model.CharacterType

@Composable
fun CharacterTypeChip(
    type: CharacterType,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(4.dp)
    val (backgroundColor, textColor) = when (type) {
        CharacterType.WEREWOLF -> Color(0xFFE53935).copy(alpha = 0.2f) to Color(0xFFFF8A80)
        CharacterType.SPECIAL -> Color(0xFF1E88E5).copy(alpha = 0.2f) to Color(0xFF90CAF9)
        CharacterType.VILLAGER -> Color(0xFF43A047).copy(alpha = 0.2f) to Color(0xFFA5D6A7)
    }

    val label = type.name.lowercase().replaceFirstChar { it.uppercase() }

    Box(
        modifier = modifier
            .border(width = 1.dp, color = textColor, shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(horizontal = 10.dp, vertical = 2.dp)

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            color = textColor
        )
    }
}

@PreviewLightDark
@Composable
private fun CharacterTypeChipPreview() {
    Column() {
        CharacterTypeChip(type = CharacterType.WEREWOLF)
        CharacterTypeChip(type = CharacterType.SPECIAL)
        CharacterTypeChip(type = CharacterType.VILLAGER)
    }
}
