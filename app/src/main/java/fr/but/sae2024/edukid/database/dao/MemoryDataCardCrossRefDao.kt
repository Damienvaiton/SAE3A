package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.MemoryDataCardCrossRef

@Dao
interface MemoryDataCardCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMemoryDataCard(memoryDataCardCrossRef: MemoryDataCardCrossRef)

    @Update
    suspend fun updateMemoryDataCard(memoryDataCardCrossRef: MemoryDataCardCrossRef)

    @Query("SELECT * FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    suspend fun getMemoryDataCard(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?
    ): MemoryDataCardCrossRef?

    @Query("SELECT used FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    suspend fun getMemoryDataCardUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?
    ): Int

    @Query("SELECT * FROM memory_data_card_cross_ref")
    suspend fun getAllMemoryDataCard(): List<MemoryDataCardCrossRef?>?

    @Query("SELECT * FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory")
    suspend fun getAllMemoryDataCardByUser(
        userId: Int,
        category: String?,
        subCategory: Int
    ): List<MemoryDataCardCrossRef?>?

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND used = 0")
    suspend fun getMemoryDataCardNbNotUsed(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND :maxUsed != used")
    suspend fun getMemoryDataCardNbNotMaxUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        maxUsed: Int
    ): Int

    @Query("SELECT MAX(used) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun getMemoryDataCardMaxUsed(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND used <= (:value-1)")
    suspend fun getMemoryDataCardNbUsedLessThan(
        userId: Int,
        category: String?,
        subCategory: Int,
        value: Int
    ): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun getMemoryDataCardNbTotal(userId: Int, category: String?, subCategory: Int): Int

    @Query("UPDATE memory_data_card_cross_ref SET used = :used WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    suspend fun updateMemoryDataCardUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?,
        used: Int
    )

    @Query("UPDATE memory_data_card_cross_ref SET used = 0 WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    suspend fun resetAllMemoryDataCardUsed(userId: Int, category: String?, subCategory: Int)
}