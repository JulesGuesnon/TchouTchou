package com.example.tchoutchou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Item
import com.example.tchoutchou.logic.character.Statistics
import com.example.tchoutchou.logic.story.StoryManager
import com.example.tchoutchou.logic.story.StoryUiElements
import com.example.tchoutchou.logic.train.Station
import com.example.tchoutchou.logic.train.Train
import com.example.tchoutchou.story.greenStory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game(
            Train.Builder().driver(Character("Billy")).currentStation(Station("Montparnasse")).build()
        )

        game.storyManager.setUiElements(
            StoryUiElements(sentence, choice1, choice2)
        )

        game.init()

        GlobalScope.async {
            game.run()
        }
    }
}
