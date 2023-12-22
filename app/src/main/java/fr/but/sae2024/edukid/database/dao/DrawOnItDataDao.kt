package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.DrawOnItData

@Dao
interface DrawOnItDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDOIData(drawOnItData: DrawOnItData?)

    @Update
    suspend fun updateDOIData(drawOnItData: DrawOnItData?)

    @Query("UPDATE draw_on_it SET last_used = 0 WHERE user_id = :userId")
    suspend fun updateAllDOIDataLastUsed(userId: Int)

    @Query("SELECT * FROM draw_on_it WHERE user_id = :userId AND draw = :draw")
    suspend fun getDOIData(userId: Int, draw: String?): DrawOnItData?

    @Query("SELECT difficulty FROM draw_on_it WHERE user_id = :userId AND draw = :draw")
    suspend fun getDOIDataMaxDif(userId: Int, draw: String?): Int

    @Query("SELECT * FROM draw_on_it WHERE user_id = :userId")
    suspend fun getAllDOIData(userId: Int): List<DrawOnItData?>?

    @Query("DELETE FROM draw_on_it WHERE user_id = :userId")
    suspend fun deleteMemoryDataByUser(userId: Int)

    @Query("DELETE FROM draw_on_it WHERE user_id = :userId")
    suspend fun deleteDOIDataByUser(userId: Int)
}