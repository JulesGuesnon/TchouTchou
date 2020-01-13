package com.example.tchoutchou.logic.story

typealias StoryNodeId = String
class StoryNode (val id: StoryNodeId, val sentence: String, val choices: Array<Choice>)