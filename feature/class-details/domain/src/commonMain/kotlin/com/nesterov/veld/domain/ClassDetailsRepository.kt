package com.nesterov.veld.domain

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel

interface ClassDetailsRepository {
    fun fetchSpellDetails(): RequestResult<CharacterClassDetailsDomainModel>
}