package com.nesterov.veld

import com.nesterov.veld.common.CharClassContract

enum class CharClass(val index: String) {
    BARBARIAN(CharClassContract.BARBARIAN),
    BARD(CharClassContract.BARD),
    CLERIC(CharClassContract.CLERIC),
    DRUID(CharClassContract.DRUID),
    FIGHTER(CharClassContract.FIGHTER),
    MONK(CharClassContract.MONK),
    PALADIN(CharClassContract.PALADIN),
    RANGER(CharClassContract.RANGER),
    ROGUE(CharClassContract.ROGUE),
    SORCERER(CharClassContract.SORCERER),
    WARLOCK(CharClassContract.WARLOCK),
    WIZARD(CharClassContract.WIZARD),
}