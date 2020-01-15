package com.example.tchoutchou.constants

import android.graphics.Point

val backgroundWidth = 512.0
val backgroundHeight = 128.0
val backgroundRatio = backgroundWidth / backgroundHeight
val trackHeight = 11.0

val trainWidth = 128.0
val trainHeight = 80.0
val trainRatio = trainWidth / trainHeight

val fireplace = Point(90, 68)
val fireplaceWidth = 13.0
val fireplaceWidthRatio = fireplace.x.toDouble() / trainWidth
val fireplaceHeightRatio = fireplace.y.toDouble() / trainHeight

val smokeWidth = 32
val smokeHeight = smokeWidth
val smokeRatio = smokeHeight / smokeHeight
