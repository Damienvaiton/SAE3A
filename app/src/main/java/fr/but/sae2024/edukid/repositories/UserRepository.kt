package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.cache.CacheDatasource
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserRepository {

    private val database = DatabaseDatasource
    private val cache = CacheDatasource

    suspend fun insertUser(user: User) {
        database.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        database.deleteUser(user)
    }

    fun getAllUsers(): Flow<List<User>> = flow {
        emit(database.getAllUsers())
    }

    fun setSelectedUser(user : User) : Flow<Boolean> = flow {
        try {
            cache.setSelectedUser(user)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun getSelectedUser(): Flow<User?> = flow {
        emit(cache.getSelectedUser())
    }

    fun setAuthenticatedUser(user : User) : Flow<Boolean> = flow {
        try {
            cache.setAuthUser(user)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
    
    fun getAuthenticatedUser(): Flow<User?> = flow {
        emit(cache.getAuthUser())
    }
}