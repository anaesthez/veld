package com.nesterov.veld.domain.usecase

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel

class FetchClassDetailsUseCase(private val repository: ClassDetailsRepository) {
    suspend operator fun invoke(index: String): RequestResult<CharacterClassDetailsDomainModel> =
        repository.fetchClassDetails(index)
}