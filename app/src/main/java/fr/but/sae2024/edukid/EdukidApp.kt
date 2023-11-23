package fr.but.sae2024.edukid

import android.app.Application
import fr.but.sae2024.edukid.database.EdukidDatabase
import timber.log.Timber

class EdukidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        EdukidDatabase.initDatabase(context = applicationContext)
    }
}