package fr.but.sae2024.edukid.views.games.menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.repositories.GameRepository
import fr.but.sae2024.edukid.repositories.ThemeRepository
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.utils.managers.VibrateManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class GameViewModel : ViewModel() {
    val db = EdukidDatabase.getInstance()
    val vibrator = VibrateManager

    private val _listGameLiveData : MutableLiveData<List<Game?>> = MutableLiveData<List<Game?>>()
    val listGameLiveData : MutableLiveData<List<Game?>> = _listGameLiveData

    private val gameRepo = GameRepository

    fun getAllGame(context : Context){
        viewModelScope.launch {
            gameRepo.getAllGame()
                .collect {

                    _listGameLiveData.postValue(it)
                }
        }
    }

    fun getAllGamesByTheme(themeName: String, context : Context){
        viewModelScope.launch {
            gameRepo.getAllGamesByTheme(themeName)
                .collect {

                    _listGameLiveData.postValue(it)
                }
        }
    }

    fun gameDefine(game : Game, context: Context){
        viewModelScope.launch {
            gameRepo.getNbSubGameByGame(game.id!!).collect{ nbSubgame ->
                gameRepo.setSelectedGame(game).collect {
                    if (it) {
                        vibrator.vibrate(context, 500)
                        Timber.tag("GameViewModel").e("Nombre de Subgame : ${nbSubgame}")
                        if (nbSubgame > 1) {
                            Timber.tag("GameViewModel").e("Game by the observe : ${game.name}")
                            RouteManager.startActivity(
                                context,
                                ActivityName.SubGameSelectionActivity,
                                true,
                                true
                            )
                        }
                        else {
                            Timber.tag("GameViewModel").e("Pas de subgames pour : ${game.name}")
                        }
                    }
                }
            }
        }
    }
}