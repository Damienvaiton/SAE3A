package fr.but.sae2024.edukid.views.users

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.models.app.User
import fr.but.sae2024.edukid.utils.enums.UserRole
import fr.but.sae2024.edukid.views.users.adapters.UserSelectionAdapter
import timber.log.Timber

class UserSelectionActivity : AppCompatActivity() {

    val db = EdukidDatabase.getInstance()
    val userDao: UserDao = db.userDao()

    val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("UserSelectionActivity").e("onCreate called")
        setContentView(R.layout.user_selection_activity)

        val userRv: RecyclerView = findViewById<RecyclerView>(R.id.recyclerview_users)

        var listUser = listOf<User>()

        val user = User(
            "admin",
            "admin",
            "adminmailtest.com",
            null,
            1,
            UserRole.PARENT,
            0
        )

        userViewModel.listUserLiveData.observe(this) {


            listUser = it
            val adapter = UserSelectionAdapter(listUser)
            userRv.adapter = adapter
            userRv.layoutManager = LinearLayoutManager(this@UserSelectionActivity)
            userRv.setHasFixedSize(true)

        }



        userViewModel.getListUser()


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




