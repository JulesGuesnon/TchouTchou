package com.example.tchoutchou.constants

import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Statistics
import java.lang.Exception

val characterStatsValidator: (Statistics) -> Unit = {

        if (it.luck > 1.0 || it.luck < 0.0) {
            throw Exception("Luck is greater than 1. or lower than 0. (provided value: ${it.luck}), it must be between 0. and 1.")
        }

        if (it.life > 10.0) {
            throw Exception("Life is greater than 10 (provided value: ${it.luck}), it must be between 0. and 10.")
        }

        if (it.food > 10.0) {
            throw Exception("Life is greater than 10 (provided value: ${it.food}), it must be between 0. and 10.")
        }

}

val rascal1 = Character("Cantal", Statistics.Builder().build(), R.drawable.character_rascal1)
val rascal2 = Character("Pascal", Statistics.Builder().build(), R.drawable.character_rascal2)
val rascal3 = Character("Chantal", Statistics.Builder().build(), R.drawable.character_rascal3)

val seller = Character("Paul", Statistics.Builder().build(), R.drawable.character_seller)

val reaper = Character("Reaper", Statistics.Builder().build(), R.drawable.character_reaper)

val theManWhoSuffer = Character("TheManWhoSuffer", Statistics.Builder().build(), R.drawable.character_lemecquiamal)

val joe = Character("Joe", Statistics.Builder().build(), R.drawable.character_joe)

val goul = Character("Goul", Statistics.Builder().build(), R.drawable.character_goul)