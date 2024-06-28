package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.CreatureDetailsDomainModel
import com.nesterov.veld.domain.CreatureRepository
import com.nesterov.veld.network.dnd.DND5eRemoteSource
import com.nesterov.veld.network.dnd.model.creature.CreatureDTO

class CreatureRepositoryImpl(
    private val dndSource: DND5eRemoteSource,
) : CreatureRepository {
    override suspend fun fetchCreature(index: String): RequestResult<CreatureDetailsDomainModel> =
        dndSource.fetchCreature(index).map(CreatureDTO::toCreatureDomainModel)
}