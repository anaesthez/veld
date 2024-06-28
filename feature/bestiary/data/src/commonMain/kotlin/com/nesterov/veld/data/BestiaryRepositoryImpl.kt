package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.creature.BestiaryLocalSource
import com.nesterov.veld.creature.entity.CreatureEntity
import com.nesterov.veld.domain.BestiaryRepository
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.network.dnd.DND5eRemoteSource
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

class BestiaryRepositoryImpl(
    private val bestiaryRemoteSource: DND5eRemoteSource,
    private val bestiaryLocalSource: BestiaryLocalSource,
) : BestiaryRepository {
    override suspend fun fetchRemoteCreatureList(): RequestResult<List<CreatureDomainModel>> =
        bestiaryRemoteSource.fetchCreaturesList()
            .map(List<ReferenceOptionDTO>::toCreatureDomainModel)

    override suspend fun fetchLocalCreatureList(): RequestResult<List<CreatureDomainModel>> =
        bestiaryLocalSource.getAllCreatures()
            .map(List<CreatureEntity>::toCreatureDomainModel)

    override suspend fun addCreature(index: String): RequestResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCreature(index: String): RequestResult<Unit> {
        TODO("Not yet implemented")
    }
}