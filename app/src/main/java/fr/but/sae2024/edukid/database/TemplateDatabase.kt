package fr.but.sae2024.edukid.database

import android.content.Context
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.Word
import fr.but.sae2024.edukid.models.entities.games.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber


object TemplateDatabase {
    private var db = EdukidDatabase.getInstance()
    private val themeLettres = "Lettres"
    val themeChiffres = "Chiffres"

    suspend fun initDatabase(context: Context) : Flow<Boolean> = flow {
        db  = EdukidDatabase.getInstance()
        try{
            createThemes()
            createGames()
            createSubGames()
            createWords()
            createCards(context)
            emit(true)
        } catch (e: Exception) {
            Timber.e("Error in initDatabase : $e")
            emit(false)
        } finally {
            Timber.i("initDatabase finished")
        }
    }

    private suspend fun createThemes() {
        if (db.themeDao().tabThemeIsEmpty()) {
            db.themeDao().insertTheme(Theme(themeLettres, R.drawable.logo_theme_lettres))
            db.themeDao().insertTheme(Theme(themeChiffres, R.drawable.logo_theme_chiffres))
        }
    }

    private suspend fun createGames() {
        if (db.gameDao().tabGameIsEmpty()) {
            db.gameDao().insertGame(Game("Memory", themeLettres, R.drawable.logo_memory_lettre))
            db.gameDao().insertGame(Game("Mot à trou", themeLettres, R.drawable.logo_wordwithhole_lettre))
            db.gameDao().insertGame(Game("Ecoute", themeLettres, R.drawable.logo_playwithsound_lettre))
            db.gameDao().insertGame(Game("Memory", themeChiffres, R.drawable.logo_memory))
            db.gameDao().insertGame(Game("Dessine", themeChiffres, R.drawable.logo_drawonit))
            db.gameDao().insertGame(Game("Ecoute", themeChiffres, R.drawable.logo_playwithsound))
        }
    }

    private suspend fun createSubGames() {

        insertSubgameIfNotExist(
            1,
            db.gameDao().getGameId("Memory", themeLettres),
            R.drawable.memory_majuscule_majuscule
        )

        insertSubgameIfNotExist(
            2,
            db.gameDao().getGameId("Memory", themeLettres),
            R.drawable.memory_majuscule_majuscule_diff
        )

        insertSubgameIfNotExist(
            3,
            db.gameDao().getGameId("Memory", themeLettres),
            R.drawable.memory_majuscule_miniscule
        )

        insertSubgameIfNotExist(
            4,
            db.gameDao().getGameId("Memory", themeLettres),
            R.drawable.memory_majuscule_miniscule_diff
        )

        insertSubgameIfNotExist(
            1,
            db.gameDao().getGameId("Memory", themeChiffres),
            R.drawable.logo_memory_img_img
        )

        insertSubgameIfNotExist(
            2,
            db.gameDao().getGameId("Memory", themeChiffres),
            R.drawable.logo_memory_img_imgdiff
        )

        insertSubgameIfNotExist(
            3,
            db.gameDao().getGameId("Memory", themeChiffres),
            R.drawable.logo_memory_chiffre_chiffre
        )

        insertSubgameIfNotExist(
            4,
            db.gameDao().getGameId("Memory", themeChiffres),
            R.drawable.logo_memory_img_chiffre
        )
    }

    private suspend fun createWords() {
        db.wordDao().insertWord(Word("AVION", R.drawable.image_avion))
        db.wordDao().insertWord(Word("MAISON", R.drawable.image_maison))
        db.wordDao().insertWord(Word("POULE", R.drawable.image_poule))
        db.wordDao().insertWord(Word("BOUCHE", R.drawable.image_bouche))
        db.wordDao().insertWord(Word("LIVRE", R.drawable.image_livre))
        db.wordDao().insertWord(Word("VACHE", R.drawable.image_vache))
        db.wordDao().insertWord(Word("TOMATE", R.drawable.image_tomate))
        db.wordDao().insertWord(Word("CHIEN", R.drawable.image_chien))
        db.wordDao().insertWord(Word("ARBRE", R.drawable.image_arbre))
        db.wordDao().insertWord(Word("BALLON", R.drawable.image_ballon))
        db.wordDao().insertWord(Word("BATEAU", R.drawable.image_bateau))
        db.wordDao().insertWord(Word("BOUTEILLE", R.drawable.image_bouteille))
        db.wordDao().insertWord(Word("CAROTTE", R.drawable.image_carotte))
        db.wordDao().insertWord(Word("CHAISE", R.drawable.image_chaise))
        db.wordDao().insertWord(Word("CHEVAL", R.drawable.image_cheval))
        db.wordDao().insertWord(Word("LION", R.drawable.image_lion))
        db.wordDao().insertWord(Word("POMME", R.drawable.image_pomme))
        db.wordDao().insertWord(Word("SOLEIL", R.drawable.image_soleil))
        db.wordDao().insertWord(Word("TÉLÉPHONE", R.drawable.image_telephone))
        db.wordDao().insertWord(Word("VOITURE", R.drawable.image_voiture))
        db.wordDao().insertWord(Word("ZÈBRE", R.drawable.image_zebre))
    }

    private suspend fun createCards(context: Context) {
        db.cardDao().insertCard(Card("1", "Chiffres", R.drawable.number_one))
        db.cardDao().insertCard(Card("2", "Chiffres", R.drawable.number_two))
        db.cardDao().insertCard(Card("3", "Chiffres", R.drawable.number_three))
        db.cardDao().insertCard(Card("4", "Chiffres", R.drawable.number_four))
        db.cardDao().insertCard(Card("5", "Chiffres", R.drawable.number_five))
        db.cardDao().insertCard(Card("6", "Chiffres", R.drawable.number_six))
        db.cardDao().insertCard(Card("7", "Chiffres", R.drawable.number_seven))
        db.cardDao().insertCard(Card("8", "Chiffres", R.drawable.number_eight))
        db.cardDao().insertCard(Card("9", "Chiffres", R.drawable.number_nine))
        val alphabetList = context.resources.getStringArray(R.array.alphabet)
        for (i in alphabetList.indices) {
            db.cardDao().insertCard(Card(alphabetList[i], "Lettres", 0))
        }
    }

    private suspend fun insertSubgameIfNotExist(num : Int, gameId : Int, image : Int? = null) {
        val name = "Niveau $num"
        if (db.subgameDao().getSubGameByNameAndGame(gameId, name) == null) {
            db.subgameDao().insertSubGame(
                Subgame(
                    num,
                    name,
                    gameId,
                    image
                )
            )
        }
    }
}