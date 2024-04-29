package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.model.SpellDetailsDomainModel

interface SpellDetailsRepository {
    suspend fun  fetchSpellDetails(index: String): RequestResult<SpellDetailsDomainModel>
}