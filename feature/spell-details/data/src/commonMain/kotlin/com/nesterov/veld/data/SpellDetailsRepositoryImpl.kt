package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.SpellDetailsRepository
import com.nesterov.veld.domain.model.SpellDetailsDomainModel
import com.nesterov.veld.network.dnd.DND5eRemoteSource

class SpellDetailsRepositoryImpl(
    private val detailsSource: DND5eRemoteSource,
): SpellDetailsRepository {
    override suspend fun fetchSpellDetails(index: String): RequestResult<SpellDetailsDomainModel> =
        detailsSource.fetchSpellDetails(index).map { it.toSpellDomainModel() }
}