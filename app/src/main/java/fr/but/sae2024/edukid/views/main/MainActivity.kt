package fr.but.sae2024.edukid.views.main

import android.app.Activity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import fr.but.sae2024.edukid.R

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Thread.sleep(3000) //si on veut un splash screen mais bloque le thread donc pas propre
        setContentView(R.layout.activity_main)

    }
}