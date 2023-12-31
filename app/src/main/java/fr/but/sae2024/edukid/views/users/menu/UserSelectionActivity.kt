package fr.but.sae2024.edukid.views.users.menu

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
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
        userViewModel.getListUser(applicationContext)
    }


    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
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


}




