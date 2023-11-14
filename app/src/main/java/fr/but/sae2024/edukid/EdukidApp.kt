package fr.but.sae2024.edukid

import android.app.Application
import timber.log.Timber

class EdukidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}