package com.example.tchoutchou.utils

import android.app.Application
import android.content.Context
import android.content.res.Resources

class Resource: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context

        fun retrieveContext(): Context {
            return context
        }
    }
}