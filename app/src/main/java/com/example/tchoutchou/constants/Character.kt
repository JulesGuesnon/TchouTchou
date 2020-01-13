package com.example.tchoutchou.constants

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