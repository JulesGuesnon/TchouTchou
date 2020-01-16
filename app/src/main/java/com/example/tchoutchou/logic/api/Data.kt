package com.example.tchoutchou.logic.api

import com.google.gson.annotations.SerializedName

class StopArea {
    var name: String = ""
}

class StationsModel {
    @SerializedName("stop_areas")
    lateinit var stopAreas: Array<StopArea>
}