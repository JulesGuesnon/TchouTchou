package com.example.tchoutchou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment: Fragment() {
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
                fragmentManager.popBackStack()
            }
        }
    }

    companion object {
        fun newInstance() = ShopFragment()
    }
}