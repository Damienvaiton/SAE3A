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
import fr.but.sae2024.edukid.models.entities.games.GameData
import fr.but.sae2024.edukid.models.responses.Response
import fr.but.sae2024.edukid.repositories.GameRepository
import fr.but.sae2024.edukid.repositories.MemoryRepository
import fr.but.sae2024.edukid.repositories.SubgameRepository
import fr.but.sae2024.edukid.repositories.ThemeRepository
import fr.but.sae2024.edukid.repositories.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

class MemoryViewModel : ViewModel() {

    private val NUMBER_SHUFFLE = 50

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

    private lateinit var cards : List<Card>

    private var selectedCards : ArrayList<Card> = arrayListOf()
    private var validatedCards : ArrayList<Card> = arrayListOf()

    private var hitCounteur : Int = 0

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
        if (selectedTheme != null) {
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
    }

    fun getPrintedCard(listAllCards : List<Card?>?) : List<Card>{

        var listPrintedCard = mutableListOf<Card>()
        var numberOfCardBySubGame = getNumberOfCardBySubGame()

        // Récupérer les cartes aléatoirement dans les la liste de cartes possible (selon le theme)

        var nombreAleatoire : Int
        var currentNumberCard = 1
        while (currentNumberCard <= numberOfCardBySubGame){
            nombreAleatoire = Random.nextInt(totalNumberCard)
            // Vérifier si la carte n'est pas déjà dans la liste
            val containFilter = listPrintedCard.filter { it.value == listAllCards?.get(nombreAleatoire)?.value }
            // Si la carte n'est pas dans la liste, on l'ajoute
            if (containFilter.isEmpty()){
                listPrintedCard.add(listAllCards?.get(nombreAleatoire)!!)
                currentNumberCard++
            }
        }

        // Dupliquer les cartes de la liste afin de créer des paires (pour l'affichage)
        // Ici on utilise pas le addAll car on veut des objets uniques
        // Le but est que la carte A et la carte A ait un objet différent et pas une copy de reference
        val tmpList = mutableListOf<Card>()
        tmpList.addAll(listPrintedCard)

        for (card in tmpList){
            listPrintedCard.add(Card(card.value, card.type, card.image, card.isHidden, card.showType))
        }

        // Mélanger les cartes
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

    fun setCards(cards: List<Card>){
        this.cards = cards
    }

    fun addHitCounteur(){
        hitCounteur++
    }

    fun getHitCounteur() : Int{
        return hitCounteur/2 //le nombre de hit pour gagner est divisé par 2 car on compte 2 hit pour chaque paire de carte
    }

    fun getGameData() : GameData{
        val gameData = GameData(
            userId = currentUser!!.id!!,
            theme = selectedTheme!!.name,
            game = selectedGame!!.id!!,
            subgame = selectedSubGame!!.num,
            win = true, //true de base car memory est toujours gagnant
            stars = getNumberOfStars(),
            date = System.currentTimeMillis(),
            draw = null,
            touchTime = null,
            sound = null,
            word = null
        )
        return gameData
    }

    fun getNumberOfStars() : Int{
        when(getNumberOfCardBySubGame()){
            2 -> {
                return when (hitCounteur/2) {
                    in 0..3 -> 3
                    in 4..6 -> 2
                    else -> 1
                }
            }
            3 -> {
                return when (hitCounteur/2) {
                    in 0..4 -> 3
                    in 5..8 -> 2
                    else -> 1
                }
            }
            4 -> {
                return when (hitCounteur/2) {
                    in 0..5 -> 3
                    in 6..10 -> 2
                    else -> 1
                }
            }
            5 -> {
                return when (hitCounteur/2) {
                    in 0..6 -> 3
                    in 7..12 -> 2
                    else -> 1
                }
            }
            else -> {
                return when (hitCounteur/2) {
                    in 0..7 -> 3
                    in 8..14 -> 2
                    else -> 1
                }
            }
        }
    }

    //fonction qui permet de gerer les cartes retournées et de determiner si elles sont identiques ou non, si il y a victoire ou premiere carte retournée
    fun onReturnedCard(card: Card) : String{
        Timber.e("Card returned : $card")

        //Liste des return possible : WIN, MATCH, NO_MATCH, FIRST_CARD
        val WIN = "WIN"
        val MATCH = "MATCH"
        val NOMATCH = "NO_MATCH"
        val FIRS_TCARD = "FIRST_CARD" //premiere carte retournée
        val RETURN_VALUE : String

        //mettre la carte cliqué dans la liste des cartes selectionné
        selectedCards.add(card)

        //si 2 card sont selectionné alors on les compare, on verifie si elles sont identiques ou non et on vide la liste des cartes selectionnées
        if (selectedCards.size == 2) {
            //si les 2 cartes sont identiques alors on les ajoute dans la liste des cartes validées
            if (selectedCards[0].value == selectedCards[1].value) {
                validatedCards.add(selectedCards[0])
                validatedCards.add(selectedCards[1])

                //si toutes les cartes sont validées alors on a gagné
                if (validatedCards.size == cards.size) {
                    //le nombre de hit pour gagner est divisé par 2 car on compte 2 hit pour chaque paire de carte
                    Timber.tag("MemoryGame").e("You win in " + hitCounteur/2 + " hits")
                    RETURN_VALUE = WIN
                }else{
                    RETURN_VALUE = MATCH
                }

            } else {
                //si les 2 cartes ne sont pas identiques alors on les cache
                /*ajouter par la suite les animations*/
                selectedCards[0].isHidden = !selectedCards[0].isHidden
                selectedCards[1].isHidden = !selectedCards[1].isHidden

                RETURN_VALUE = NOMATCH
            }

            //on supprime les cartes selectionnées pour pouvoir en selectionner d'autres
            selectedCards.clear()

        }else{
            RETURN_VALUE = FIRS_TCARD
        }

        return RETURN_VALUE

    }



}