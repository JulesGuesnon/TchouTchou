package com.example.tchoutchou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tchoutchou.logic.Game
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment(val game: Game): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onStart() {
        super.onStart()
        setBackEvent()
    }

    private fun setBackEvent() {
        return_menu.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager

            if (fragmentManager != null) {
                game.musicManager.load(R.raw.sound_home, true, true, 1f)
                fragmentManager.popBackStack()
            }
        }
    }

    companion object {
        fun newInstance(game: Game) = ShopFragment(game)
    }
}