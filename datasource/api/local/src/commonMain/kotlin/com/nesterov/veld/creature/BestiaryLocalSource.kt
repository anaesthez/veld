package com.nesterov.veld.creature

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.creature.entity.CreatureEntity

interface BestiaryLocalSource {
    suspend fun getAllCreatures(): RequestResult<List<CreatureEntity>>
    suspend fun addCreature(creature: CreatureEntity): RequestResult<Unit>
    suspend fun deleteCreatureByIndex(index: String): RequestResult<Unit>
}