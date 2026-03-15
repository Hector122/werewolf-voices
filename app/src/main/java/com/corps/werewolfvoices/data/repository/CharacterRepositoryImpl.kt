package com.corps.werewolfvoices.data.repository

import com.corps.werewolfvoices.data.datasource.CharacterLocalDataSource
import com.corps.werewolfvoices.domain.model.Character
import com.corps.werewolfvoices.domain.model.DataResult
import com.corps.werewolfvoices.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    val dataSource: CharacterLocalDataSource
) : CharacterRepository {
    override fun getCharacter(): DataResult<List<Character>> {
        //TODO: just for testing purposes

        return try {
            // In a real app, this might be a JSON parse or a Room query.
            // For now, we return our static Werewolf character set.
            val characters = emptyList<Character>()

//                listOf(
//                Character(1, "Werewolf", R.drawable.wolf, R.raw.wolf_howl),
//                Character(2, "Seer", R.drawable.seer, R.raw.seer_magic),
//                Character(3, "Villager", R.drawable.villager, R.raw.villager_idle),
//                Character(4, "Hunter", R.drawable.hunter, R.raw.hunter_shot),
//                Character(5, " Witch ", R.drawable.witch, R.raw.witch_laugh)
//            )
            DataResult.Success(characters)
        } catch (e: Exception) {
            DataResult.Error("Could not load characters", e)
        }
    }
}
