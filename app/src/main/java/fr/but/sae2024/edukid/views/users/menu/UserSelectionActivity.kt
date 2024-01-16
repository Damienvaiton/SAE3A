package fr.but.sae2024.edukid.views.users.menu

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.views.users.UserViewModel
import fr.but.sae2024.edukid.views.users.adapters.UserSelectionAdapter
import timber.log.Timber

class UserSelectionActivity : AppCompatActivity() {


    val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("UserSelectionActivity").e("onCreate called")
        setContentView(R.layout.user_selection_activity)

        val userRv: RecyclerView = findViewById<RecyclerView>(R.id.recyclerview_users)




        userViewModel.listUserLiveData.observe(this) {


            val listUser = it
            val adapter = UserSelectionAdapter(listUser)
            userRv.adapter = adapter
            userRv.layoutManager = LinearLayoutManager(this@UserSelectionActivity)
            userRv.setHasFixedSize(true)

        }


        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        userViewModel.getListUser(applicationContext)
    }


    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val builder = AlertDialog.Builder(this@UserSelectionActivity)
            builder.setTitle("Quitter l'application")
            builder.setMessage("Voulez-vous vraiment quitter l'application ?")
            builder.setPositiveButton("Oui") { _, _ ->
                finishAffinity()
            }
            builder.setNegativeButton("Non") { _, _ ->

            }
            builder.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }


}




