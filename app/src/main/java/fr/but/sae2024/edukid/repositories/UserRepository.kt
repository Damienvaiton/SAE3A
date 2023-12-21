package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserRepository {
    private val database = DatabaseDatasource

    suspend fun insertUser(user: User) {
        database.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        database.deleteUser(user)
    }


     fun getAllUsers(): Flow<List<User>> = flow {
        emit(database.getAllUsers())
    }
}