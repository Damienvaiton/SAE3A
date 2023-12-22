package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(memoryCard: Card?)

    @Update
    suspend fun updateCard(memoryCard: Card?)

    @Query("SELECT * FROM cards WHERE value LIKE :cardValue")
    suspend fun getCard(cardValue: String?): Card?

    @Query("SELECT image FROM cards WHERE value LIKE :cardValue")
    suspend fun getCardDrawableImage(cardValue: String?): Int

    @Query("SELECT * FROM cards WHERE type LIKE :type")
    suspend fun getAllCardByType(type: String?): List<Card?>?

    @Query("SELECT * FROM cards")
    suspend fun getAllCard(): List<Card?>?
}