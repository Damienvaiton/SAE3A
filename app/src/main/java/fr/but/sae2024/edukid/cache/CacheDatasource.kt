package fr.but.sae2024.edukid.cache

import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User

object CacheDatasource {

    private var authUser : User? = null

    private var selectedUser : User? = null
    private var selectedTheme : Theme? = null
    private var selectedGame : Game? = null
    private var selectedSubGame : Subgame? = null

    fun getAuthUser() : User? {
        return authUser
    }

    fun setAuthUser(user : User) {
        authUser = user
    }

    fun getSelectedUser() : User? {
        return selectedUser
    }

    fun setSelectedUser(user : User) {
        selectedUser = user
    }

    fun getSelectedTheme() : Theme? {
        return selectedTheme
    }

    fun setSelectedTheme(theme : Theme) {
        selectedTheme = theme
    }

    fun getSelectedGame() : Game? {
        return selectedGame
    }

    fun setSelectedGame(game : Game) {
        selectedGame = game
    }

    fun getSelectedSubGame() : Subgame? {
        return selectedSubGame
    }

    fun setSelectedSubGame(subgame : Subgame) {
        selectedSubGame = subgame
    }
}