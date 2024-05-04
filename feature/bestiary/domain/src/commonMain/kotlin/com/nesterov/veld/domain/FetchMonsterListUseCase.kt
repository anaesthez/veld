package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

class FetchMonsterListUseCase(private val repository: BestiaryRepository) {
    suspend operator fun invoke(): RequestResult<List<MonsterDomainModel>> =
        repository.fetchMonsterList()
}