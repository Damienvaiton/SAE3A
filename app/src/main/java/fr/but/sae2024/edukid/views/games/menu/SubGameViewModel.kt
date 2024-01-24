package fr.but.sae2024.edukid.views.games.menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.repositories.SubgameRepository
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import kotlinx.coroutines.launch

class SubGameViewModel : ViewModel(){
    val db = EdukidDatabase.getInstance()

    private val _listSubGameLiveData : MutableLiveData<List<Subgame?>> = MutableLiveData<List<Subgame?>>()
    val listSubGameLiveData : MutableLiveData<List<Subgame?>> = _listSubGameLiveData
    var selectedGame : Subgame? = null

    private val subGameRepo = SubgameRepository

    fun getAllSubGame(SubGameName: String, context : Context){
        viewModelScope.launch {
            subGameRepo.getAllSubGamesByGame(SubGameName)
                .collect {

                    _listSubGameLiveData.postValue(it)
                }
        }
    }

    fun subGameDefine(subGame : Subgame, context: Context){
        viewModelScope.launch {
            subGameRepo.setSelectedSubGame(subGame).collect{
                if(it){
                    RouteManager.startActivity(context, ActivityName.ThemeSelectionActivity, true, true)
                }
            }
        }
    }

    fun getSelectedSubGame(context : Context){
        viewModelScope.launch {
            subGameRepo.getSelectedSubGame()
                .collect {

                    selectedGame = it
                }

        }
    }

    fun getAllSubGamesByGame(themeName: String, context : Context){
        viewModelScope.launch {
            subGameRepo.getAllSubGamesByGame(themeName)
                .collect {

                    _listSubGameLiveData.postValue(it)
                }
        }
    }

}