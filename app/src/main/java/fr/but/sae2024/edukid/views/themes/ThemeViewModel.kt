package fr.but.sae2024.edukid.views.themes

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.repositories.ThemeRepository
import kotlinx.coroutines.launch

class ThemeViewModel: ViewModel(){
    val db = EdukidDatabase.getInstance()

    private val _listThemeLiveData : MutableLiveData<List<Theme?>> = MutableLiveData<List<Theme?>>()
    val listThemeLiveData : MutableLiveData<List<Theme?>> = _listThemeLiveData

    private val themeRepo = ThemeRepository

    fun getListUser(context : Context){
        viewModelScope.launch {
            themeRepo.getAllTheme()
                .collect {

                    _listThemeLiveData.postValue(it)
                }
        }
    }
}