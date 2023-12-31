package fr.but.sae2024.edukid.views.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import androidx.appcompat.app.AppCompatActivity
import fr.but.sae2024.edukid.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenViewModel by viewModels<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val applicationNameView = findViewById<View>(R.id.applicationName)

        // Créer une animation YoYo avec un listener
        val yoYoAnimator = YoYo.with(Techniques.Tada).duration(1000)
            .onEnd {
                splashScreenViewModel.initDatabase(this@SplashScreenActivity)
            }

        // Jouer l'animation
        yoYoAnimator.playOn(applicationNameView)
    }
}