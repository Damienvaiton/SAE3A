package fr.but.sae2024.edukid.views.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.TemplateDatabase
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val template = TemplateDatabase

    fun initDatabase(context : Context) {
        viewModelScope.launch {
            template.initDatabase(context).collect {success ->
                if (success) {
                    RouteManager.startActicity(context, ActivityName.UserSelectionActivity, true, true)
                }
            }
        }
    }
}