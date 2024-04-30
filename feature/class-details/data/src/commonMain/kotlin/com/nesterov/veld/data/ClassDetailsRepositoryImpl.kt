package com.nesterov.veld.data

import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.network.dnd.DNDSpellSource

class ClassDetailsRepositoryImpl(
    private val spellSource: DNDSpellSource,
) : ClassDetailsRepository {
    override fun fetchSpellDetails(): CharacterClassDetailsDomainModel {
        TODO("Not yet implemented")
    }
}