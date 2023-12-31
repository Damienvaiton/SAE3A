package fr.but.sae2024.edukid.views.users.resume

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.but.sae2024.edukid.R
import timber.log.Timber


class UserAddActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_add_activity)
        Timber.tag("UserAddActivity").e("onCreate called")
    }

}