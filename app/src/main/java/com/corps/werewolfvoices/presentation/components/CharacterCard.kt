package com.corps.werewolfvoices.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.presentation.theme.WerewolfVoicesTheme

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 3.dp,
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .padding(8.dp)
        ) {
            Card(
                shape = RoundedCornerShape(
                    topEnd = 8.dp,
                    topStart = 8.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = character.type.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    WerewolfVoicesTheme() {
        CharacterCard(
            character = Character(
                id = 1,
                name = "Werewolf",
                type = CharacterType.WEREWOLF,
                shortDescription = "A classic werewolf character",
                imageRes = 0,
                soundRes = 0
            )
        )
    }
}
