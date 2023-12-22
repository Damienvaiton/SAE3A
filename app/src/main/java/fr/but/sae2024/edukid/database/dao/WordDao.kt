package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.but.sae2024.edukid.models.entities.app.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word?)

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word?>?

    @Query("SELECT COUNT(*) FROM words")
    suspend fun getNbWords(): Int

    @Query("SELECT * FROM words WHERE word LIKE :str")
    suspend fun getWordIfContain(str: String?): List<Word?>?
}