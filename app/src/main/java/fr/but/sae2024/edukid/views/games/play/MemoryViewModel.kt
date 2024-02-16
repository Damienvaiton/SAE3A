package fr.but.sae2024.edukid.views.games.play


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.repositories.GameRepository
import fr.but.sae2024.edukid.repositories.MemoryRepository
import fr.but.sae2024.edukid.repositories.SubgameRepository
import fr.but.sae2024.edukid.repositories.ThemeRepository
import fr.but.sae2024.edukid.repositories.UserRepository
import kotlinx.coroutines.launch

private val UserRepo = UserRepository
private val ThemRepo = ThemeRepository
private val GameRepo = GameRepository
private val SubGameRepo = SubgameRepository
private val MemoryRepo = MemoryRepository

private var currentUser : User? = null
private var selectedTheme : Theme? = null
private var selectedGame : Game? = null
private var selectedSubGame : Subgame? = null
private var totalNumberCard : Int? = null

class MemoryViewModel : ViewModel() {

    fun getData(){
        viewModelScope.launch {
            getCurrentUser()
            getCurrentTheme()
            getCurrentGame()
            getCurrentSubGame()
            getTotalNumberCard()
        }
    }

    suspend private fun getCurrentUser(){

            UserRepo.getAuthenticatedUser().collect{ user ->
                currentUser = user
            }

    }

    fun getCurrentTheme(){
        viewModelScope.launch {
            ThemRepo.getSelectedTheme().collect{ theme ->
                selectedTheme = theme
            }
        }
    }

    fun getCurrentGame(){
        viewModelScope.launch {
            GameRepo.getSelectedGame().collect{ game ->
                selectedGame = game
            }
        }
    }

    fun getCurrentSubGame(){
        viewModelScope.launch {
            SubGameRepo.getSelectedSubGame().collect{ subgame ->
                selectedSubGame = subgame
            }
        }
    }

    fun getTotalNumberCard() {
        viewModelScope.launch {
            MemoryRepo.getTotalNumberCard().collect{ numberOfCard ->
                totalNumberCard = numberOfCard
            }
        }
    }

    fun getCardByValue(value: Int) {
        viewModelScope.launch {
            MemoryRepo.getCardByValue(value).collect{ card ->
                // Do something with the card
            }
        }
    }

    fun getCardes() {
        viewModelScope.launch {
            UserRepo.insertUser(user)
        }
    }

}