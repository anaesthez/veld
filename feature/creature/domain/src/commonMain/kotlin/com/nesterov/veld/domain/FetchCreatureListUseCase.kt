package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

class FetchCreatureUseCase(private val repository: CreatureRepository) {
    suspend operator fun invoke(index: String): RequestResult<CreatureDomainModel> =
        repository.fetchCreature(index)
}