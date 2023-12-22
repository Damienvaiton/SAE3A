package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.MemoryData

@Dao
interface MemoryDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMemoryData(memoryData: MemoryData?)

    @Update
    suspend fun updateMemoryData(memoryData: MemoryData?)

    @Query("SELECT * FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun getMemoryData(userId: Int, category: String?, subCategory: Int): MemoryData?

    @Query("SELECT difficulty FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun getMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT max_difficulty FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun getMemoryDataMaxDifficulty(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT * FROM memory_data")
    suspend fun getAllMemoryData(): List<MemoryData?>?

    @Query("UPDATE memory_data SET difficulty = (difficulty +1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun increaseMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET difficulty = (difficulty -1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun decreaseMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET max_difficulty = (max_difficulty +1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun increaseMemoryDataMaxDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET win_streak = 0, lose_streak = 0 WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun resetAllMemoryDataStreak(userId: Int, category: String?, subCategory: Int)
}