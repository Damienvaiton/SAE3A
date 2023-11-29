package fr.but.sae2024.edukid.views.users

import android.app.AlertDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.MyScharedPreferences
import fr.but.sae2024.edukid.viewmodel.ProfileViewModel
import fr.but.sae2024.edukid.views.users.adapters.UserSelectionAdapter
import timber.log.Timber

class UserSelectionActivity : AppCompatActivity() {

    var UserRv: RecyclerView? = null

    val UserViewModel = ProfileViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("UserSelectionActivity").e("onCreate called")
        setContentView(R.layout.user_selection_activity)


        UserRv = findViewById(R.id.recyclerview_users)

        UserRv?.adapter = UserSelectionAdapter()






    }

    override fun onStart() {
        super.onStart()
        UserViewModel.createAndGetDatabase()
    }



    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Do you want to exit ?")
        builder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }


    fun saveUserScharedPreferences(){
        MyScharedPreferences.saveUser(this, "user")
    }








}

