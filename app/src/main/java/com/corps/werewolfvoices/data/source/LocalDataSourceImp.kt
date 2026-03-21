package com.corps.werewolfvoices.data.source

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

            //TODO: move text to string.xml
            val characters = listOf(
                Character(
                    id = 1,
                    name = "Werewolf",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Kills a villager each night.",
                    imageResId = R.drawable.card_werewolf,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 2,
                    name = "Villager",
                    type = CharacterType.VILLAGER,
                    shortDescription = "No special power. Find the wolves!",
                    imageResId = R.drawable.card_villager,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 3,
                    name = "Seer",
                    type = CharacterType.SPECIAL,
                    shortDescription = "Sees one player's role each night.",
                    imageResId = R.drawable.card_seer,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 4,
                    name = "Hunter",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Shoots one player upon elimination.",
                    imageResId = R.drawable.card_werewolf,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 5,
                    name = "Witch",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Has one healing and one poison potion.",
                    imageResId = R.drawable.card_werewolf,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 6,
                    name = "Cupid",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Links two lovers on the first night.",
                    imageResId = R.drawable.card_werewolf,
                    soundResId = R.raw.sound_cinematic
                ),
                Character(
                    id = 7,
                    name = "Little Girl",
                    type = CharacterType.WEREWOLF,
                    shortDescription = "Can spy on werewolves at night — at great risk.",
                    imageResId = R.drawable.card_werewolf,
                    soundResId = R.raw.sound_cinematic
                ),
            )
            DataResult.Success(characters)
        } catch (e: Exception) {
            DataResult.Error("Could not load characters", e)
        }
    }
}
