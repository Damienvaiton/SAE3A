package fr.but.sae2024.edukid.views.users.menu

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
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
        val addNewUserButton = findViewById<ImageView>(R.id.addUser)




        userViewModel.listUserLiveData.observe(this) {
            val listUser = it
            val adapter = UserSelectionAdapter(listUser)
            userRv.adapter = adapter
            userRv.layoutManager = LinearLayoutManager(this@UserSelectionActivity)

            adapter.userLD.observe(this) { user ->
                userViewModel.saveAuthUser(user, this)
            }

            adapter.userLongClickLD.observe(this) { user ->
                AlertDialog.Builder(this@UserSelectionActivity)
                    .setTitle("Modification d'utilisateur")
                    .setMessage("Que voulez-vous faire ?")
                    .setPositiveButton("Modifier le user") { _, _ ->
                        userViewModel.saveSelectedUser(user, this)
                    }
                    .setNegativeButton("Supprimer le user") { _, _ ->
                        AlertDialog.Builder(this@UserSelectionActivity)
                            .setTitle("Suppression d'utilisateur")
                            .setMessage("Voulez-vous vraiment supprimer l'utilisateur "+user.username)
                            .setPositiveButton("Oui") { _, _ ->
                                userViewModel.deleteUser(user)
                            }
                            .setNegativeButton("Non") { _, _ ->

                            }
                            .show()
                    }
                    .show()
            }
        }


        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        userViewModel.getListUser()


        addNewUserButton.setOnClickListener {
            RouteManager.startActivity(this, ActivityName.UserAddActivity, false, true)
        }


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




