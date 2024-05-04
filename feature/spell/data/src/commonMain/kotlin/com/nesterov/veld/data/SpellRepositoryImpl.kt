package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.network.dnd.DND5eRemoteSource

class SpellRepositoryImpl(
    private val spellSource: DND5eRemoteSource,
): SpellRepository {
    override suspend fun fetchSpellList(): RequestResult<List<SpellDomainModel>> {
        return spellSource.fetchSpellList().map { it.toSpellDomainModel() }
    }
}