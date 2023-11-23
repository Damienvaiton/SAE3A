package fr.but.sae2024.edukid.views.users

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R

class UserSelectionActivity : AppCompatActivity() {

    var UserRv : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.user_selection_activity)

        UserRv = findViewById(R.id.recyclerview_users)


    }
}