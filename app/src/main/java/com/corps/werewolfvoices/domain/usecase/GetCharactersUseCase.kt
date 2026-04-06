package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() = repository.getCharacters()
}
