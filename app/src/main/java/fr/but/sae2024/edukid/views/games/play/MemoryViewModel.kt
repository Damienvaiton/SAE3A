package fr.but.sae2024.edukid.views.games.play


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.models.MemoryData
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.models.responses.Response
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

    private val _listCardMemory : MutableLiveData<Response<MemoryData>> = MutableLiveData<Response<MemoryData>>()
    val listCardMemory : MutableLiveData<Response<MemoryData>> = _listCardMemory

    fun getData(){
        viewModelScope.launch {
            getCurrentUser() // recupérations des infos en cascade afin d'etre sur d'avoir toutes les données
        }
    }

    suspend private fun getCurrentUser(){

            UserRepo.getAuthenticatedUser().collect{ user ->
                currentUser = user
                getCurrentTheme()
            }

    }

    fun getCurrentTheme(){
        viewModelScope.launch {
            ThemRepo.getSelectedTheme().collect{ theme ->
                selectedTheme = theme
                getCurrentGame()
            }
        }
    }

    fun getCurrentGame(){
        viewModelScope.launch {
            GameRepo.getSelectedGame().collect{ game ->
                selectedGame = game
                getCurrentSubGame()
            }
        }
    }

    fun getCurrentSubGame(){
        viewModelScope.launch {
            SubGameRepo.getSelectedSubGame().collect{ subgame ->
                selectedSubGame = subgame
                getListCard()
            }
        }
    }

    suspend fun getListCard(){

        MemoryRepo.getAllCard(selectedTheme!!.name).collect { listAllCards ->

            totalNumberCard = listAllCards?.size!!

            val listPrintedCard = getPrintedCard(listAllCards)

            val response : Response<MemoryData> = Response()

            val data = MemoryData(
                user = currentUser!!,
                theme = selectedTheme!!,
                game = selectedGame!!,
                subgame = selectedSubGame!!,
                listCards = listPrintedCard,
                numberColumn = getNumberOfColumn(listPrintedCard.size)
            )

            response.addData(data)

            _listCardMemory.postValue(response)

        }
    }

    fun getPrintedCard(listAllCards : List<Card?>?) : List<Card>{

        var listPrintedCard = mutableListOf<Card>()
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
                listPrintedCard.add(listAllCards?.get(nombreAleatoire)!!)
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

    fun getNumberOfColumn(nbCards: Int) : Int{

        return when (nbCards) {
            4 -> 2
            6,8 -> 3
            else -> 4
        }
    }

    fun getNumberOfCardBySubGame() : Int{

        val numSubGame = selectedSubGame?.num

        return when (numSubGame) {
            1 -> 2
            2 -> 3
            3 -> 4
            4 -> 5
            else -> 6
        }
    }

}