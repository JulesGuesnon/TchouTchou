package com.example.tchoutchou.logic.Story

typealias StoryNodeId = String
class StoryNode (val id: StoryNodeId, val sentence: String, val choices: Array<Choice>)