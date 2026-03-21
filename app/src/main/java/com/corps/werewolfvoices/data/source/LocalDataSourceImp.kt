package com.corps.werewolfvoices.data.datasource

import com.corps.werewolfvoices.R
import com.corps.werewolfvoices.domain.datasource.LocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.CharacterType
import com.corps.werewolfvoices.domain.model.DataResult
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor() : LocalDataSource {
    override suspend fun getCharacters(): DataResult<List<Character>> {
        return try {
            // In a real app, this might be a JSON parse or a Room query.
            // For now, we return our static Werewolf character set.
            val characters = listOf(
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Kills a villager each night.",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.sound_cinematic
                ),
                Character(
                    id = 2,
                    name = "Villager",
                    type = CharacterType.VILLAGER,
                    shortDescription = "No special power. Find the wolves!",
                    imageRes = R.drawable.card_villager,
                    soundRes = R.raw.sound_cinematic
                ),
                Character(
                    id = 3,
                    name = "Seer",
                    type = CharacterType.SPECIAL,
                    shortDescription = "Sees one player's role each night.",
                    imageRes = R.drawable.card_seer,
                    soundRes = R.raw.sound_cinematic
                ),
                Character(
                    id = 4,
                    name = "Hunter",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Shoots one player upon elimination.",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.sound_cinematic
                ),
                Character(
                    id = 5,
                    name = "Witch",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Has one healing and one poison potion.",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.sound_cinematic
                ),
                Character(
                    id = 5,
                    name = "Cupid",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Links two lovers on the first night.",
                    imageRes = R.drawable.card_werewolf,
                    soundRes = R.raw.sound_cinematic
                ),
            )
            DataResult.Success(characters)
        } catch (e: Exception) {
            DataResult.Error("Could not load characters", e)
        }
    }
}
