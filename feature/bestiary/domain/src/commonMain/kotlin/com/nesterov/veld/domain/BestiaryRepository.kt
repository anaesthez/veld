package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult

interface BestiaryRepository {
    suspend fun fetchMonsterList(): RequestResult<List<MonsterDomainModel>>
}