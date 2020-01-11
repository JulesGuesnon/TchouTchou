package com.example.tchoutchou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tchoutchou.logic.Character
import com.example.tchoutchou.logic.CharacterStats

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = Character("Jojo", CharacterStats(life = 10.0, luck = 1.0, food = 10.0, strength = 10.0))
        test.effect()
    }
}
