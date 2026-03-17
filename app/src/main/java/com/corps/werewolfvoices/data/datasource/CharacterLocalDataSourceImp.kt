package com.corps.werewolfvoices.data.datasource

import com.corps.werewolfvoices.R
import com.corps.werewolfvoices.domain.datasource.CharacterLocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult

class CharacterLocalDataSourceImp : CharacterLocalDataSource {
    override suspend fun getCharacters(): DataResult<List<Character>> {
        return try {
            // In a real app, this might be a JSON parse or a Room query.
            // For now, we return our static Werewolf character set.
            val characters = listOf(
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Eliminate the villagers by night",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.cinematic
                ),
                Character(
                    id = 1,
                    name = "Villager",
                    type = CharacterType.VILLAGER,
                    shortDescription = "Find the hidden Werewolves by Day.",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.cinematic
                ),
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Eliminate the villagers!",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.cinematic
                ),
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Eliminate the villagers!",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.cinematic
                ),
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Eliminate the villagers!",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.cinematic
                ),
            )
            DataResult.Success(characters)
        } catch (e: Exception) {
            DataResult.Error("Could not load characters", e)
        }
    }
}
