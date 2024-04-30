package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel

interface ClassDetailsRepository {
    suspend fun fetchClassDetails(index: String): RequestResult<CharacterClassDetailsDomainModel>
}