package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

interface CreatureRepository {
    suspend fun fetchCreature(index: String): RequestResult<CreatureDomainModel>
}