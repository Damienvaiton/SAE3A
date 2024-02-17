package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.games.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MemoryRepository {

    private val database = DatabaseDatasource

    fun getTotalNumberCard(): Flow<Int?> = flow {
        emit(database.getTotalNumberCard())
    }

    fun getAllCard(): Flow<List<Card?>?> = flow {
        emit(database.getAllCard())
    }
}