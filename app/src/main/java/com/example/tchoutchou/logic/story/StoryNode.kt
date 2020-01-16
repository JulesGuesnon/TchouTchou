package com.example.tchoutchou.logic.story

import com.example.tchoutchou.logic.character.Character

typealias StoryNodeId = String

class StoryNode (val id: StoryNodeId, val title: String, val subtitle: String, val sentence: String, val background: Int, val choices: Array<Choice>, val characters: Array<Character>)