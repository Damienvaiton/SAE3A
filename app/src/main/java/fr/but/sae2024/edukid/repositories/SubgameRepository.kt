package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.cache.CacheDatasource
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object SubgameRepository {

    private val database = DatabaseDatasource
    private val cache = CacheDatasource

    fun setSelectedSubGame(subgame : Subgame) : Flow<Boolean> = flow {
        try {
            cache.setSelectedSubGame(subgame)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun getSelectedSubGame(): Flow<Subgame?> = flow {
        emit(cache.getSelectedSubGame())
    }

    fun getAllSubGamesByGame(game: Game): Flow<List<Subgame?>> = flow {
        emit(database.getAllSubGamesByGame(game.id!!))
    }
}