package com.nesterov.veld.creature.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nesterov.veld.creature.entity.CreatureRoomEntity

@Dao
interface CreatureDAO {
    @Upsert
    suspend fun addCreature(index: CreatureRoomEntity)

    @Query("DELETE FROM creatures WHERE :index")
    suspend fun deleteCreatureByIndex(index: String)

    @Query("SELECT * FROM creatures")
    suspend fun readAllCreatures(): List<CreatureRoomEntity>
}