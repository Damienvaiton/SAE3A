package fr.but.sae2024.edukid.views.splashscreen

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.database.TemplateDatabase
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.utils.managers.TextToSpeechManager
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val template = TemplateDatabase
    private val user = DatabaseDatasource

    fun initDatabase(context : Context) {
        viewModelScope.launch {
            template.initDatabase(context).collect {success ->
                if (success) {
                    chooseStartActivity(context)
                } else {
                    if (context is Activity) {
                        context.finishAffinity()
                    }
                }
            }
        }
    }



    fun chooseStartActivity(context : Context) {
        viewModelScope.launch {
            if (user.IsUserEmpty()) {
                RouteManager.startActivity(context, ActivityName.UserAddActivity, true, true)
            } else {
                RouteManager.startActivity(context, ActivityName.ThemeSelectionActivity, true, true)
            }
        }
    }
}