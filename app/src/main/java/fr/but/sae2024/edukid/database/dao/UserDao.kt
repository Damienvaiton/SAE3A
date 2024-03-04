package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.app.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE LOWER(mail) = :mail")
    suspend fun getUserByMail(mail: String): User?

    @Query("SELECT * FROM users WHERE LOWER(username) = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE LOWER(mail) = :mail")
    suspend fun isEmailUsed(mail: String): Boolean {
        return getUserByMail(mail) != null
    }

    @Query("SELECT * FROM users WHERE LOWER(username) = :username")
    suspend fun isUsernameUsed(username: String): Boolean {
        return getUserByUsername(username) != null
    }
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT picture FROM users WHERE picture_type != 0 ORDER BY picture DESC LIMIT 1")
    suspend fun getUserImageMaxInt(): String?

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    suspend fun tabUserIsEmpty(): Boolean {
        return getAllUsers().isEmpty()
    }
}