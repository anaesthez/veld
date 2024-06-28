package com.nesterov.veld.presentation.utils

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.getOrNull
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.presentation.mapper.toCreaturePresentationModel
import com.nesterov.veld.presentation.model.CreatureMemoryStatus
import com.nesterov.veld.presentation.model.CreaturePresentationModel

typealias LocalList = RequestResult<List<CreatureDomainModel>>
typealias RemoteList = RequestResult<List<CreatureDomainModel>>
typealias OutputList = RequestResult<List<CreaturePresentationModel>>

enum class MergeStrategy {
    DROP_LOCAL_ON_CONFLICT
}

fun mergeCreaturesList(
    strategy: MergeStrategy = MergeStrategy.DROP_LOCAL_ON_CONFLICT,
    remoteList: LocalList,
    localList: RemoteList,
): OutputList {
    return when (strategy) {
        MergeStrategy.DROP_LOCAL_ON_CONFLICT -> {
            dropLocalMerge(
                localList = localList,
                remoteList = remoteList,
            )
        }
    }
}

private fun dropLocalMerge(
    remoteList: RemoteList,
    localList: LocalList,
): OutputList {
    val localIndices = localList.getOrNull()?.map { it.index }
    return remoteList.map { creatures ->
        val list = creatures.map {
            val status =
                if (localIndices?.contains(it.index) == true) CreatureMemoryStatus.LOCAL else CreatureMemoryStatus.REMOTE
            it.toCreaturePresentationModel(status = status)
        }
        list
    }
}