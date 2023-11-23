package fr.but.sae2024.edukid.views.users

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.views.users.adapters.UserSelectionAdapter
import timber.log.Timber

class UserSelectionActivity : AppCompatActivity() {

    var UserRv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.user_selection_activity)

        UserRv = findViewById(R.id.recyclerview_users)

        UserRv?.adapter = UserSelectionAdapter()






    }

    override fun onStart() {
        super.onStart()
        createAndGetDatabase()
       // TODO("Creataion de la recycler view")
    }

    fun createAndGetDatabase() {
        /*
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()*/
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

