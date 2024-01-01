package fr.but.sae2024.edukid.views.users.resume

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
        val importPictureButton = findViewById<Button>(R.id.buttonImport_userEditPage)
        val validateButton = findViewById<Button>(R.id.buttonValider_userEditPage)
        val picture = findViewById<ImageView>(R.id.userAvatar_editPage)
        var pictureURI: String = ""
        val cancelButton = findViewById<Button>(R.id.buttonCancel_userEditPage)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { media ->
            if (media != null) {
                picture.setImageURI(media)
                pictureURI = media.toString()
            }
        }

        cancelButton.visibility = Button.GONE


        importPictureButton.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        validateButton.setOnClickListener {
            userViewModel.createUserChild(username.text.toString(), pictureURI , this)
            RouteManager.startActivity(this, ActivityName.UserSelectionActivity, false, true)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AlertDialog.Builder(this)
            .setTitle("Attention")
            .setMessage("Vous allez perdre les modifications non sauvegardées")
            .setPositiveButton("Quitter") { _, _ ->
                RouteManager.startActivity(this, ActivityName.UserSelectionActivity, false, true)
            }
            .setNegativeButton("Rester") { _, _ -> }
            .show()
    }
}