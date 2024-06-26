package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

class FetchCreatureRemoteListUseCase(private val repository: BestiaryRepository) {
    suspend operator fun invoke(): RequestResult<List<CreatureDomainModel>> =
        repository.fetchRemoteCreatureList()
}