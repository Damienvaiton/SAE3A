package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.cache.CacheDatasource
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object GameRepository {

    private val database = DatabaseDatasource
    private val cache = CacheDatasource

    fun getAllGame(): Flow<List<Game?>> = flow {
        emit(database.getAllGame())
    }

    fun getAllGamesByTheme(themeName: String): Flow<List<Game?>> = flow {
        emit(database.getAllGamesByTheme(themeName))
    }

    fun setSelectedGame(game : Game) : Flow<Boolean> = flow {
        try {
            cache.setSelectedGame(game)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun getSelectedGame(): Flow<Game?> = flow {
        emit(cache.getSelectedGame())
    }

    fun getNbSubGameByGame(gameId: Int): Flow<Int> = flow {
        // Recuperer la liste de subgame pour ce gameid
        val listSubGame = database.getAllSubGamesByGame(gameId)
        // Retourner le nombre de subgame
        emit(listSubGame.size)
    }
}