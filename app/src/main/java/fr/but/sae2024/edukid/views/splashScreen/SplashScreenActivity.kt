package fr.but.sae2024.edukid.views.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import fr.but.sae2024.edukid.R

class SplashScreenActivity : AppCompatActivity() {

    private val splashViewModel : SplashScreenViewModel = SplashScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_splash_screen)

        showLoginModal()
    }

    private fun showLoginModal() : Unit {
        val loginMenu: LoginModal = LoginModal

        loginMenu.show(supportFragmentManager, loginMenu.TAG)

    }
}