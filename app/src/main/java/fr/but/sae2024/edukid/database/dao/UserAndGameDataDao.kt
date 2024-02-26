package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import fr.but.sae2024.edukid.models.entities.relations.UserAndGameData

@Dao
interface UserAndGameDataDao {
    @Transaction
    @Query("SELECT * FROM users")
    fun getUserAndGameData(): List<UserAndGameData>
}