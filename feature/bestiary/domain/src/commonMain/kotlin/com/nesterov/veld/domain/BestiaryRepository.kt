package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

interface BestiaryRepository {
    suspend fun fetchCreatureList(): RequestResult<List<CreatureDomainModel>>
}