package fr.but.sae2024.edukid.views.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.database.TemplateDatabase
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.utils.managers.TextToSpeechManager
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashScreenViewModel : ViewModel() {

    private val template = TemplateDatabase
    private val user = DatabaseDatasource

    fun initDatabase(context : Context) {
        TextToSpeechManager.initialiser(context)
        viewModelScope.launch {
            template.initDatabase(context).collect {success ->
                if (success) {
                    chooseStartActivity(context)
                } else {
                    Timber.e("Error in initDatabase")
                }
            }
        }
    }

    fun chooseStartActivity(context : Context) {
        viewModelScope.launch {
            if (user.IsUserEmpty()) {
                RouteManager.startActivity(context, ActivityName.UserAddActivity, false, true)
            } else {
                RouteManager.startActivity(context, ActivityName.UserSelectionActivity, false, true)
            }
        }
    }
}