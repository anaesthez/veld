package com.nesterov.veld.network.dnd

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper
import com.nesterov.veld.network.dnd.model.classes.details.ClassDetailsDTO
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import com.nesterov.veld.network.dnd.model.spell.details.SpellDetailsDTO

interface DND5eRemoteSource {
    suspend fun fetchSpellList(): RequestResult<List<ReferenceOptionDTO>>
    suspend fun fetchCreaturesList(): RequestResult<List<ReferenceOptionDTO>>
    suspend fun fetchSpellDetails(index: String): RequestResult<SpellDetailsDTO>
    suspend fun fetchCharacterClassDetails(index: String): RequestResult<ClassDetailsDTO>

    interface Dependencies {
        val config: HttpClientConfig
        val wrapper: HttpRequestWrapper
    }
}