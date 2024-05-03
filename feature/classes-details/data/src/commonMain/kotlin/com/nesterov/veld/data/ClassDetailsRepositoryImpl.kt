package com.nesterov.veld.data

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.map
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.network.dnd.DNDSpellSource
import com.nesterov.veld.network.dnd.model.classes.details.ClassDetailsDTO

class ClassDetailsRepositoryImpl(
    private val spellSource: DNDSpellSource,
) : ClassDetailsRepository {
    override suspend fun fetchClassDetails(index: String): RequestResult<CharacterClassDetailsDomainModel> =
        spellSource.fetchCharacterClassDetails(index)
            .map(ClassDetailsDTO::toCharacterClassDetailsDomainModel)
}