package com.nesterov.veld.domain.usecases

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.domain.model.SpellDomainModel

class FetchSpellListUseCase(private val repository: SpellRepository) {
    suspend operator fun invoke(): RequestResult<List<SpellDomainModel>> = repository.fetchSpellList()
}