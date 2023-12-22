package fr.but.sae2024.edukid.database

import android.content.Context
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.Word
import fr.but.sae2024.edukid.models.entities.games.Card


object DatabaseTemplate {
    private var db = EdukidDatabase.getInstance()
    private val themeLettres = "Lettres"
    val themeChiffres = "Chiffres"


    fun ajoutDatabase(context: Context) {
        createThemes()
        createGames()
        createSubGames()
        createWords()
        createCards(context)
    }

    fun createThemes() {
        if (db.appDao().tabGameIsEmpty()) {
            db.appDao().insertTheme(Theme(themeLettres, R.drawable.logo_theme_lettres))
            db.appDao().insertTheme(Theme(themeChiffres, R.drawable.logo_theme_chiffres))
        }
    }

    fun createGames() {
        if (db.appDao().tabGameIsEmpty()) {
            db.appDao().insertGame(Game("Memory", themeLettres, R.drawable.logo_memory_lettre))
            db.appDao().insertGame(Game("Mot à trou", themeLettres, R.drawable.logo_wordwithhole_lettre))
            db.appDao().insertGame(Game("Ecoute", themeLettres, R.drawable.logo_playwithsound_lettre))
            db.appDao().insertGame(Game("Memory", themeChiffres, R.drawable.logo_memory))
            db.appDao().insertGame(Game("Dessine", themeChiffres, R.drawable.logo_drawonit))
            db.appDao().insertGame(Game("Ecoute", themeChiffres, R.drawable.logo_playwithsound))
        }
    }

    fun createSubGames() {
        if (db.appDao().tabSubGameIsEmpty()) {
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 1",
                    db.appDao().getGameId("Memory", themeLettres),
                    R.drawable.memory_majuscule_majuscule
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 2",
                    db.appDao().getGameId("Memory", themeLettres),
                    R.drawable.memory_majuscule_majuscule_diff
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 3",
                    db.appDao().getGameId("Memory", themeLettres),
                    R.drawable.memory_majuscule_miniscule
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 4",
                    db.appDao().getGameId("Memory", themeLettres),
                    R.drawable.memory_majuscule_miniscule_diff
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 1",
                    db.appDao().getGameId("Memory", themeChiffres),
                    R.drawable.logo_memory_img_img
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 2",
                    db.appDao().getGameId("Memory", themeChiffres),
                    R.drawable.logo_memory_img_imgdiff
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 3",
                    db.appDao().getGameId("Memory", themeChiffres),
                    R.drawable.logo_memory_chiffre_chiffre
                )
            )
            db.appDao().insertSubGame(
                Subgame(
                    "Niveau 4",
                    db.appDao().getGameId("Memory", themeChiffres),
                    R.drawable.logo_memory_img_chiffre
                )
            )
        }
    }

    fun createWords() {
        db.appDao().insertWord(Word("AVION", R.drawable.image_avion))
        db.appDao().insertWord(Word("MAISON", R.drawable.image_maison))
        db.appDao().insertWord(Word("POULE", R.drawable.image_poule))
        db.appDao().insertWord(Word("BOUCHE", R.drawable.image_bouche))
        db.appDao().insertWord(Word("LIVRE", R.drawable.image_livre))
        db.appDao().insertWord(Word("VACHE", R.drawable.image_vache))
        db.appDao().insertWord(Word("TOMATE", R.drawable.image_tomate))
        db.appDao().insertWord(Word("CHIEN", R.drawable.image_chien))
        db.appDao().insertWord(Word("ARBRE", R.drawable.image_arbre))
        db.appDao().insertWord(Word("BALLON", R.drawable.image_ballon))
        db.appDao().insertWord(Word("BATEAU", R.drawable.image_bateau))
        db.appDao().insertWord(Word("BOUTEILLE", R.drawable.image_bouteille))
        db.appDao().insertWord(Word("CAROTTE", R.drawable.image_carotte))
        db.appDao().insertWord(Word("CHAISE", R.drawable.image_chaise))
        db.appDao().insertWord(Word("CHEVAL", R.drawable.image_cheval))
        db.appDao().insertWord(Word("LION", R.drawable.image_lion))
        db.appDao().insertWord(Word("POMME", R.drawable.image_pomme))
        db.appDao().insertWord(Word("SOLEIL", R.drawable.image_soleil))
        db.appDao().insertWord(Word("TÉLÉPHONE", R.drawable.image_telephone))
        db.appDao().insertWord(Word("VOITURE", R.drawable.image_voiture))
        db.appDao().insertWord(Word("ZÈBRE", R.drawable.image_zebre))
    }

    fun createCards(context: Context) {
        db.gameDao().insertCard(Card("1", "Chiffres", R.drawable.number_one))
        db.gameDao().insertCard(Card("2", "Chiffres", R.drawable.number_two))
        db.gameDao().insertCard(Card("3", "Chiffres", R.drawable.number_three))
        db.gameDao().insertCard(Card("4", "Chiffres", R.drawable.number_four))
        db.gameDao().insertCard(Card("5", "Chiffres", R.drawable.number_five))
        db.gameDao().insertCard(Card("6", "Chiffres", R.drawable.number_six))
        db.gameDao().insertCard(Card("7", "Chiffres", R.drawable.number_seven))
        db.gameDao().insertCard(Card("8", "Chiffres", R.drawable.number_eight))
        db.gameDao().insertCard(Card("9", "Chiffres", R.drawable.number_nine))
        val alphabetList = context.resources.getStringArray(R.array.alphabet)
        for (i in alphabetList.indices) {
            db.gameDao().insertCard(Card(alphabetList[i], "Lettres", 0))
        }
    }
}