package com.example.tchoutchou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tchoutchou.logic.Character
import com.example.tchoutchou.logic.CharacterStats

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val character = Character("Billy", CharacterStats(10.0, 10.0, 10.0, 1.0))
    }
}
