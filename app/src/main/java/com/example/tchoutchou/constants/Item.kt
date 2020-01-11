package com.example.tchoutchou.constants

import com.example.tchoutchou.logic.Statistics

val itemStatsValidator: (Statistics) -> Unit = {
    if (it.luck > 1.0 || it.luck < -1.0) {
        throw Exception("Luck is greater than 1. or lower than 0. (provided value: ${it.luck}), it must be between 0. and 1.")
    }
}