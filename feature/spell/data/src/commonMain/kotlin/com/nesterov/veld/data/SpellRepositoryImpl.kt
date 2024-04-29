package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.network.dnd.DNDSpellSource

class SpellRepositoryImpl(
    private val spellSource: DNDSpellSource,
): SpellRepository {
    override suspend fun fetchSpellList(): RequestResult<List<SpellDomainModel>> {
        return spellSource.fetchSpellList().map { it.toSpellDomainModel() }
    }
}