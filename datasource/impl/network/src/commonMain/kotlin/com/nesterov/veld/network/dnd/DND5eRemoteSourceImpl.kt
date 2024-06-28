package com.nesterov.veld.network.dnd

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.wrapHttpExceptions
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.model.bestiary.ResponseBestiaryDTO
import com.nesterov.veld.network.dnd.model.classes.details.ClassDetailsDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureDTO
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import com.nesterov.veld.network.dnd.model.spell.ResponseSpellDTO
import com.nesterov.veld.network.dnd.model.spell.details.SpellDetailsDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class DND5eRemoteSourceImpl(
    private val dependencies: Dependencies,
) : DND5eRemoteSource, HttpClientConfig by dependencies.config {

    override suspend fun fetchSpellList(): RequestResult<List<ReferenceOptionDTO>> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url(HttpRoutes.SPELLS)
            }.body<ResponseSpellDTO>().results
        }

    override suspend fun fetchCreaturesList(): RequestResult<List<ReferenceOptionDTO>> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url(HttpRoutes.BESTIARY)
            }.body<ResponseBestiaryDTO>().results
        }

    override suspend fun fetchSpellDetails(index: String): RequestResult<SpellDetailsDTO> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url("${HttpRoutes.SPELL_DETAILS}/$index")
            }.body<SpellDetailsDTO>()
        }

    override suspend fun fetchCharacterClassDetails(index: String): RequestResult<ClassDetailsDTO> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url("${HttpRoutes.CLASSES_DETAILS}/$index")
            }.body<ClassDetailsDTO>()
        }

    override suspend fun fetchCreature(index: String): RequestResult<CreatureDTO> =
        wrapHttpExceptions {
            return@wrapHttpExceptions client.get {
                url("${HttpRoutes.CREATURE}/$index")
            }.body<CreatureDTO>()
        }

    interface Dependencies {
        val config: HttpClientConfig
    }
}