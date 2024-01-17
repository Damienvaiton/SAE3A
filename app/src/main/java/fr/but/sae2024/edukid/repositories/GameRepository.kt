package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.cache.CacheDatasource
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object GameRepository {

    private val database = DatabaseDatasource
    private val cache = CacheDatasource

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
}