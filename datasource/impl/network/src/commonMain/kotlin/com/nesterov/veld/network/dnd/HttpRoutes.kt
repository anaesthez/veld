package com.nesterov.veld.network.dnd

object HttpRoutes {
    private const val BASE_URL = "https://www.dnd5eapi.co/api"
    const val SPELLS = "$BASE_URL/spells"
    const val BESTIARY = "$BASE_URL/monsters"
    const val SPELL_DETAILS = "$SPELLS/"
    const val CLASSES_DETAILS = "$BASE_URL/classes"
}