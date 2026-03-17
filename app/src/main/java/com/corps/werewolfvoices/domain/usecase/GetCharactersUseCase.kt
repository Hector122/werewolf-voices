package com.corps.werewolfvoices.domain.usecase

import com.corps.werewolfvoices.domain.repository.CharacterRepository

class GetCharactersUseCase (
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() = repository.getCharacter()
}
