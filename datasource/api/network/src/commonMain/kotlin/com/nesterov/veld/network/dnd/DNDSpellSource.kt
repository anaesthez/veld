package com.nesterov.veld.network.dnd

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper
import com.nesterov.veld.network.dnd.model.details.SpellDetailsDTO
import com.nesterov.veld.network.dnd.model.spell.SpellDTO

interface DNDSpellSource {
    suspend fun fetchSpellList(): RequestResult<List<SpellDTO>>
    suspend fun fetchSpellDetails(index: String): RequestResult<SpellDetailsDTO>

    interface Dependencies {
        val config: HttpClientConfig
        val wrapper: HttpRequestWrapper
    }
}