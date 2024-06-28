package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

class FetchCreatureLocalListUseCase(private val repository: BestiaryRepository) {
    suspend operator fun invoke(): RequestResult<List<CreatureDomainModel>> =
        repository.fetchLocalCreatureList()
}