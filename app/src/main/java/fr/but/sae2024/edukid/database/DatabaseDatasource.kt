package fr.but.sae2024.edukid.database

import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User

object DatabaseDatasource {


    suspend fun insertUser(user: User) {
        return EdukidDatabase.getInstance().userDao().insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        return EdukidDatabase.getInstance().userDao().deleteUser(user)
    }

    suspend fun getAllUsers(): List<User> {
        return EdukidDatabase.getInstance().userDao().getAllUsers()
    }

    suspend fun IsUserEmpty(): Boolean {
        return EdukidDatabase.getInstance().userDao().tabUserIsEmpty()
    }

    suspend fun getAllTheme(): List<Theme?> {
        return EdukidDatabase.getInstance().themeDao().getAllThemes()
    }

    suspend fun getAllGame(): List<Game?> {
        return EdukidDatabase.getInstance().gameDao().getAllGames()
    }

    suspend fun getAllGamesByTheme(themeName: String): List<Game?> {
        return EdukidDatabase.getInstance().gameDao().getAllGamesByTheme(themeName)
    }

    suspend fun getAllSubGamesByGame(gameId: Int): List<Subgame?> {
        return EdukidDatabase.getInstance().subgameDao().getAllSubGamesByGame(gameId)
    }

}