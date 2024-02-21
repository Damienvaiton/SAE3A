package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.games.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MemoryRepository {

    private val database = DatabaseDatasource

    fun getTotalNumberCard(): Flow<Int?> = flow {
        emit(database.getTotalNumberCard())
    }

    fun getAllCard(theme : String): Flow<List<Card?>?> = flow {
        emit(database.getAllCard(theme))
    }
}