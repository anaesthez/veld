package com.nesterov.veld.network.dnd

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper
import com.nesterov.veld.network.dnd.model.details.SpellDetailsDTO
import com.nesterov.veld.network.dnd.model.spell.ResponseSpellDTO
import com.nesterov.veld.network.dnd.model.spell.SpellDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText

class DNDSpellSourceImpl(
    private val dependencies: DNDSpellSource.Dependencies,
) : DNDSpellSource,
    HttpClientConfig by dependencies.config,
    HttpRequestWrapper by dependencies.wrapper {

    override suspend fun fetchSpellList(): RequestResult<List<SpellDTO>> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url(HttpRoutes.SPELLS)
            }.body<ResponseSpellDTO>().results
        }

    override suspend fun fetchSpellDetails(index: String): RequestResult<SpellDetailsDTO> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url("${HttpRoutes.DETAILS}/$index")
            }.body<SpellDetailsDTO>()
        }
}