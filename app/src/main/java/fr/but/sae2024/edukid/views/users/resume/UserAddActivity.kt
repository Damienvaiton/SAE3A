package fr.but.sae2024.edukid.views.users.resume

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class UserAddActivity : AppCompatActivity() {

    val userViewModel by viewModels<UserViewModel>()

    var idPicture: Int = 1


    var pictureURI: String = ""

    var hasCustomPhoto = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_add_activity)
        Timber.tag("UserAddActivity").e("onCreate called")


        val username = findViewById<TextInputEditText>(R.id.textField_userName)
        val importPictureButton = findViewById<Button>(R.id.buttonImport_userEditPage)
        val validateButton = findViewById<Button>(R.id.buttonValider_userEditPage)
        val picture = findViewById<ImageView>(R.id.userAvatar_editPage)
        val cancelButton = findViewById<Button>(R.id.buttonCancel_userEditPage)

        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { media ->
                if (media != null) {
                    picture.setImageURI(media)
                    pictureURI = media.toString()
                }
            }

        cancelButton.visibility = Button.GONE

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)


        importPictureButton.setOnClickListener {
            //Open a dialog to choose between taking a picture or choosing one from the gallery
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choisissez une image")
            builder.setMessage("Voulez-vous prendre une photo ou en choisir une dans votre galerie ?")
            builder.setPositiveButton("Prendre une photo") { _, _ ->
                Timber.tag("UserAddActivity").e("Prendre une photo")
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePictureIntent, 20)

            }
            builder.setNegativeButton("Choisir une photo") { _, _ ->
                Timber.tag("UserAddActivity").e("Choisir une photo")
                hasCustomPhoto = true
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            builder.show()

        }

        picture.setOnClickListener() {
            pictureURI = ""
            idPicture++
            if (idPicture == 9) {
                idPicture = 1
            }
            picture.setImageResource(
                resources.getIdentifier(
                    "profil$idPicture",
                    "drawable",
                    packageName
                )
            )
            Timber.tag("UserAddActivity").e("idPicture : $idPicture")
        }

        validateButton.setOnClickListener {
            if (pictureURI == "" || !hasCustomPhoto) {
                pictureURI = "android.resource://fr.but.sae2024.edukid/drawable/profil$idPicture"
            }
            userViewModel.createUserChild(username.text.toString(), pictureURI, this)
            RouteManager.startActivity(this, ActivityName.UserSelectionActivity, false, true)
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            RouteManager.startActivity(
                this@UserAddActivity,
                ActivityName.UserSelectionActivity,
                false,
                true
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == 20) && (resultCode == RESULT_OK)) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val picture = findViewById<ImageView>(R.id.userAvatar_editPage)
            picture.setImageBitmap(imageBitmap)
            val uri = saveBitmap(imageBitmap, this)
            pictureURI = uri.toString()
            Timber.tag("UserAddActivity").e("pictureURI is : $pictureURI")
        }
    }

    fun saveBitmap(bitmap: Bitmap, context: Context): String? {
        val filename = "image_${System.currentTimeMillis()}.jpg"

        var fos: OutputStream? = null
        var uri: String? = null

        try {
            // Crée un fichier dans le répertoire Pictures de l'application
            val imageFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename)
            fos = FileOutputStream(imageFile)

            // Compression de l'image et écriture dans le flux de sortie
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)

            // Ajout de l'image à la galerie
            MediaStore.Images.Media.insertImage(
                context.contentResolver,
                imageFile.absolutePath,
                imageFile.name,
                imageFile.name
            )

            // Obtention de l'URI de l'image
            uri = imageFile.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return uri
    }


}

