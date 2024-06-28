package com.nesterov.veld.creature

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.creature.entity.CreatureEntity
import com.nesterov.veld.creature.entity.CreatureRoomEntity
import com.nesterov.veld.creature.utils.wrapRoomAccessExceptions
import com.nesterov.veld.db.AppDatabase

class BestiaryLocalSourceImpl(private val dependencies: Dependencies) : BestiaryLocalSource {
    private val dao by lazy {
        dependencies.database.getCreatureDao()
    }

    override suspend fun getAllCreatures(): RequestResult<List<CreatureEntity>> =
        wrapRoomAccessExceptions {
            dao.readAllCreatures().map(CreatureRoomEntity::toCreatureEntity)
        }

    override suspend fun addCreature(creature: CreatureEntity): RequestResult<Unit> =
        wrapRoomAccessExceptions {
            dao.addCreature(creature.toCreatureRoomEntity())
        }

    override suspend fun deleteCreatureByIndex(index: String): RequestResult<Unit> =
        wrapRoomAccessExceptions {
            dao.deleteCreatureByIndex(index)
        }

    interface Dependencies {
        val database: AppDatabase
    }
}