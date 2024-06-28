package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

interface BestiaryRepository {
    suspend fun fetchRemoteCreatureList(): RequestResult<List<CreatureDomainModel>>
    suspend fun fetchLocalCreatureList(): RequestResult<List<CreatureDomainModel>>
    suspend fun addCreature(index: String): RequestResult<Unit>
    suspend fun deleteCreature(index: String): RequestResult<Unit>
}