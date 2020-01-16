package com.example.tchoutchou.logic.story

typealias StoryNodeId = String
class StoryNode (val id: StoryNodeId, val title: String, val subtitle: String, val sentence: String, val background: Int, val choices: Array<Choice>)