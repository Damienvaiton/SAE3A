package fr.but.sae2024.edukid.views.games.play


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.repositories.GameRepository
import fr.but.sae2024.edukid.repositories.MemoryRepository
import fr.but.sae2024.edukid.repositories.SubgameRepository
import fr.but.sae2024.edukid.repositories.ThemeRepository
import fr.but.sae2024.edukid.repositories.UserRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class MemoryViewModel : ViewModel() {

    private val NUMBER_SHUFFLE = 100

    private val UserRepo = UserRepository
    private val ThemRepo = ThemeRepository
    private val GameRepo = GameRepository
    private val SubGameRepo = SubgameRepository
    private val MemoryRepo = MemoryRepository

    private var currentUser : User? = null
    private var selectedTheme : Theme? = null
    private var selectedGame : Game? = null
    private var selectedSubGame : Subgame? = null
    private var totalNumberCard : Int = 0

    fun getData(){
        viewModelScope.launch {
            getCurrentUser()
            getCurrentTheme()
            getCurrentGame()
            getCurrentSubGame()
            getListCard()
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

        suspend fun getListCard(){

        MemoryRepo.getAllCard(/*theme*/).collect { listAllCards ->

            totalNumberCard = listAllCards?.size!!

            var listPrintedCard = getPrintedCard(listAllCards)

        }
    }

    fun getPrintedCard(listAllCards : List<Card?>?) : List<Card?>?{

        var listPrintedCard = mutableListOf<Card?>()
        var numberOfCardBySubGame = getNumberOfCardBySubGame()

        //récupérer les cartes aléatoirement dans les la liste de cartes possible (selon le theme)

        var nombreAleatoire = 0
        var currentNumberCard = 1
        while (currentNumberCard <= numberOfCardBySubGame){
            nombreAleatoire = Random.nextInt(totalNumberCard)
            //vérifier si la carte n'est pas déjà dans la liste
            val containFilter = listPrintedCard.filter { it?.value == listAllCards?.get(nombreAleatoire)?.value }
            //si la carte n'est pas dans la liste, on l'ajoute
            if (containFilter.isEmpty()){
                listPrintedCard.add(listAllCards?.get(nombreAleatoire))
                currentNumberCard++
            }
        }

        //dupliquer les cartes de la liste afin de créer des paires (pour l'affichage)
        listPrintedCard.addAll(listPrintedCard)

        //mélanger les cartes
        for (i in 1..NUMBER_SHUFFLE){
            listPrintedCard.shuffle()
        }

        return listPrintedCard
    }

    fun getNumberOfCardBySubGame() : Int{

        val numSubGame = selectedSubGame?.num

        when (numSubGame) {
            1 -> return 2
            2 -> return 3
            3 -> return 4
            4 -> return 5
            else -> return 6
        }
    }

}