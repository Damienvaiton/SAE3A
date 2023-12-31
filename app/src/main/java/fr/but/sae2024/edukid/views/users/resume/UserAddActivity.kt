package fr.but.sae2024.edukid.views.users.resume

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.users.UserViewModel
import timber.log.Timber


class UserAddActivity : AppCompatActivity()  {

    val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_add_activity)
        Timber.tag("UserAddActivity").e("onCreate called")


        val username = findViewById<TextInputEditText>(R.id.textField_userName)
        val validateButton = findViewById<Button>(R.id.buttonValider_userEditPage)
        validateButton.setOnClickListener {
            userViewModel.createUserChild(username.text.toString(), this)
            RouteManager.startActivity(this, ActivityName.UserSelectionActivity, false, true)
        }
    }

}