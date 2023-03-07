package com.joblogic.todo

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApplication : Application() {

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()
    }
}
