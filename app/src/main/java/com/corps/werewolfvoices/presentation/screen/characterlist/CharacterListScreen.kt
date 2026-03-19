package com.corps.werewolfvoices.presentation.screen.characterlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.corps.werewolfvoices.R
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.presentation.components.CharacterCard
import com.corps.werewolfvoices.presentation.theme.WerewolfVoicesTheme

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onCharacterClick: (Character) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(stringResource(R.string.werewolf_ambient_sounds), Modifier.padding(16.dp))
        }

        items(
            items = characters,
            key = { character -> character.id }
        ) { character ->
            CharacterCard(
                character = character,
                onClick = { onCharacterClick(character) }
            )
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun CharacterListScreenPreview() {
    val sampleCharacters = List(12) { i ->
        Character(
            id = i,
            name = when (i % 3) {
                0 -> "Werewolf $i"
                1 -> "Villager $i"
                else -> "Special Role $i"
            },
            type = when (i % 3) {
                0 -> CharacterType.WEREWOLF
                1 -> CharacterType.VILLAGER
                else -> CharacterType.SPECIAL
            },
            shortDescription = "This is a short description for character number $i in the werewolf game.",
            imageRes = 0,
            soundRes = 0
        )
    }
    WerewolfVoicesTheme() {
        CharacterListScreen(
            characters = sampleCharacters,
            onCharacterClick = {}
        )
    }
}
