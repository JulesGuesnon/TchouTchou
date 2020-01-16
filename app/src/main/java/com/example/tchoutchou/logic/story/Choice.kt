package com.example.tchoutchou.logic.story

import com.example.tchoutchou.logic.Game

data class Choice (val choice: String, var transition: String, val to: StoryNodeId, val callback: (Game) -> Unit = {})