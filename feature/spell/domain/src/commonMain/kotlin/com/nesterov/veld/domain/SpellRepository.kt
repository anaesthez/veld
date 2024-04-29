package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.model.SpellDomainModel

interface SpellRepository {
    suspend fun  fetchSpellList(): RequestResult<List<SpellDomainModel>>
}