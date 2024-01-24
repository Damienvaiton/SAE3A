package fr.but.sae2024.edukid.views.games.menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.repositories.GameRepository
import fr.but.sae2024.edukid.repositories.SubgameRepository
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.utils.managers.VibrateManager
import kotlinx.coroutines.launch

class SubGameViewModel : ViewModel(){
    val db = EdukidDatabase.getInstance()
    val vibrator = VibrateManager

    private val _listSubGameLiveData : MutableLiveData<List<Subgame?>> = MutableLiveData<List<Subgame?>>()
    val listSubGameLiveData : MutableLiveData<List<Subgame?>> = _listSubGameLiveData
    var selectedSubGame : Subgame? = null
    var selectedGame : Game? = null

    private val subGameRepo = SubgameRepository
    private val gameRepo = GameRepository

    fun subGameDefine(subGame : Subgame, context: Context){
        viewModelScope.launch {
            subGameRepo.setSelectedSubGame(subGame).collect{
                if(it){
                    vibrator.vibrate(context, 500)
                    RouteManager.startActivity(context, ActivityName.ThemeSelectionActivity, true, true)
                }
            }
        }
    }

    fun getSelectedSubGame(context : Context){
        viewModelScope.launch {
            subGameRepo.getSelectedSubGame()
                .collect {
                    selectedSubGame = it
                }
        }
    }

    fun getAllSubGamesByGame(context : Context){
        viewModelScope.launch {
            gameRepo.getSelectedGame()
                .collect {game ->
                    selectedGame = game
                    subGameRepo.getAllSubGamesByGame(selectedGame!!)
                        .collect {listGame ->
                            _listSubGameLiveData.postValue(listGame)
                        }
                }
        }
    }

}