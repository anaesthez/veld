package com.nesterov.veld.domain.usecases

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.SpellDetailsRepository
import com.nesterov.veld.domain.model.SpellDetailsDomainModel

class FetchSpellDetailsUseCase(private val repository: SpellDetailsRepository) {
    suspend operator fun invoke(index: String): RequestResult<SpellDetailsDomainModel> =
        repository.fetchSpellDetails(index)
}