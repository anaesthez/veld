package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.BestiaryRepository
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.network.dnd.DND5eRemoteSource

class BestiaryRepositoryImpl(
    private val bestiarySource: DND5eRemoteSource,
) : BestiaryRepository {
    override suspend fun fetchCreatureList(): RequestResult<List<CreatureDomainModel>> =
        bestiarySource.fetchCreaturesList().map { it.toCreatureDomainModel() }
}