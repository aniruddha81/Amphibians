package com.aniruddha81.amphibians

import android.app.Application

class AmphibiansApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}