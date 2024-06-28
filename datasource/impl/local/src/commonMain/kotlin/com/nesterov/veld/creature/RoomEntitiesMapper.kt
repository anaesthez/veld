package com.nesterov.veld.creature

import com.nesterov.veld.creature.entity.CreatureEntity
import com.nesterov.veld.creature.entity.CreatureRoomEntity

fun CreatureRoomEntity.toCreatureEntity(): CreatureEntity =
    CreatureEntity(
        index = index,
        url = url,
        name = name,
    )

fun CreatureEntity.toCreatureRoomEntity(): CreatureRoomEntity =
    CreatureRoomEntity(
        id = 0L,
        index = index,
        url = url,
        name = name,
    )