package fr.but.sae2024.edukid.database.dao

import android.os.Build
import android.support.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.models.entities.games.DrawOnItData
import fr.but.sae2024.edukid.models.entities.games.MemoryData
import fr.but.sae2024.edukid.models.entities.games.MemoryDataCardCrossRef
import fr.but.sae2024.edukid.models.entities.games.PlayWithSoundData
import fr.but.sae2024.edukid.models.entities.games.WordWithHoleData

@Dao
interface GameDao {
    //WordWithHoleData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWWHData(wordWithHoleStats: WordWithHoleData?)

    @Update
    fun updateWWHData(wordWithHoleStats: WordWithHoleData?)

    @Query("UPDATE word_with_hole_data SET last_used = 0 WHERE user_id = :userId AND last_used = 1")
    fun updateAllWWHDataLastUsed(userId: Int)

    @Query("SELECT max(difficulty) FROM word_with_hole_data WHERE user_id = :userId")
    fun getWWHDataMaxDifficulty(userId: Int): Int

    @Query("SELECT * FROM word_with_hole_data WHERE user_id = :userId AND result = :result")
    fun getWWHDataByResult(userId: Int, result: String?): WordWithHoleData?

    @Query("SELECT * FROM word_with_hole_data WHERE user_id = :userId")
    fun getAllWWHData(userId: Int): List<WordWithHoleData?>?

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun getAllWWHDataLastUsed(
        listData: List<WordWithHoleData>,
        difficulty: Int,
        lastUsed: Int
    ): List<String>? {
        val listRes: MutableList<String> = ArrayList()
        val listDataSort: MutableList<WordWithHoleData> = ArrayList()
        val listSortLose: MutableList<WordWithHoleData> = ArrayList()
        val listSortWin: MutableList<WordWithHoleData> = ArrayList()
        val listNeverUsed: MutableList<WordWithHoleData> = ArrayList()
        for (i in listData.indices) {
            if (listData[i].loseStreak > 0) listSortLose.add(listData[i]) else if (listData[i].winStreak > 0) listSortWin.add(
                listData[i]
            ) else listNeverUsed.add(listData[i])
        }
        listSortLose.sortWith(Comparator.comparing(WordWithHoleData::loseStreak).reversed())
        listSortWin.sortWith(Comparator.comparing(WordWithHoleData::loseStreak))
        listDataSort.addAll(listSortLose)
        listDataSort.addAll(listNeverUsed)
        listDataSort.addAll(listSortWin)
        for (i in listDataSort.indices) {
            if (listDataSort[i].lastUsed == lastUsed && listDataSort[i].difficulty == difficulty) {
                listRes.add(listDataSort[i].result)
            }
        }
        return listRes
    }

    @Query("DELETE FROM word_with_hole_data WHERE user_id = :userId")
    fun deleteWWHDataByUser(userId: Int)


    //PlayWithSoundData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPWSData(playWithSoundData: PlayWithSoundData?)

    @Update
    fun updatePWSData(playWithSoundData: PlayWithSoundData?)

    @Query("UPDATE play_with_sound_data SET last_used = 0 WHERE user_id = :userId AND last_used = 1")
    fun updateAllPWSDataLastUsed(userId: Int)

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND result LIKE :result")
    fun getPWSDataByResult(userId: Int, result: String?): PlayWithSoundData?

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName")
    fun getAllPWSDataByTheme(userId: Int, themeName: String?): List<PlayWithSoundData?>?

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName AND difficulty = :difficulty")
    fun getAllPWSDataByThemeAndDifficulty(
        userId: Int,
        themeName: String?,
        difficulty: Int
    ): List<PlayWithSoundData?>?

    @Query("SELECT max(difficulty) FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName")
    fun getPWSDataMaxDifficulty(userId: Int, themeName: String?): Int

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun getAllPWSDataLastUsed(
        listData: List<PlayWithSoundData>,
        difficulty: Int,
        lastUsed: Int
    ): List<String>? {
        val listRes: MutableList<String> = ArrayList()
        val listDataSort: MutableList<PlayWithSoundData> = ArrayList()
        val listSortLose: MutableList<PlayWithSoundData> = ArrayList()
        val listSortWin: MutableList<PlayWithSoundData> = ArrayList()
        val listNeverUsed: MutableList<PlayWithSoundData> = ArrayList()
        for (i in listData.indices) {
            if (listData[i].loseStreak > 0) listSortLose.add(listData[i]) else if (listData[i].winStreak > 0) listSortWin.add(
                listData[i]
            ) else listNeverUsed.add(listData[i])
        }
        listSortLose.sortWith(Comparator.comparing(PlayWithSoundData::loseStreak).reversed())
        listSortWin.sortWith(Comparator.comparing(PlayWithSoundData::loseStreak))
        listDataSort.addAll(listSortLose)
        listDataSort.addAll(listNeverUsed)
        listDataSort.addAll(listSortWin)
        for (i in listDataSort.indices) {
            if (listDataSort[i].lastUsed == lastUsed && listDataSort[i].difficulty == difficulty) {
                listRes.add(listDataSort[i].result)
            }
        }
        return listRes
    }


    @Query("DELETE FROM play_with_sound_data WHERE user_id = :userId")
    fun deletePWSDataByUser(userId: Int)


    //DrawOnItData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDOIData(drawOnItData: DrawOnItData?)

    @Update
    fun updateDOIData(drawOnItData: DrawOnItData?)

    @Query("UPDATE draw_on_it SET last_used = 0 WHERE user_id = :userId")
    fun updateAllDOIDataLastUsed(userId: Int)

    @Query("SELECT * FROM draw_on_it WHERE user_id = :userId AND draw = :draw")
    fun getDOIData(userId: Int, draw: String?): DrawOnItData?

    @Query("SELECT difficulty FROM draw_on_it WHERE user_id = :userId AND draw = :draw")
    fun getDOIDataMaxDif(userId: Int, draw: String?): Int

    @Query("SELECT * FROM draw_on_it WHERE user_id = :userId")
    fun getAllDOIData(userId: Int): List<DrawOnItData?>?

    @Query("DELETE FROM draw_on_it WHERE user_id = :userId")
    fun deleteMemoryDataByUser(userId: Int)

    @Query("DELETE FROM draw_on_it WHERE user_id = :userId")
    fun deleteDOIDataByUser(userId: Int)


    //MemoryData
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMemoryData(memoryData: MemoryData?)

    @Update
    fun updateMemoryData(memoryData: MemoryData?)

    @Query("SELECT * FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun getMemoryData(userId: Int, category: String?, subCategory: Int): MemoryData?

    @Query("SELECT difficulty FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun getMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT max_difficulty FROM memory_data WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun getMemoryDataMaxDifficulty(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT * FROM memory_data")
    fun getAllMemoryData(): List<MemoryData?>?

    @Query("UPDATE memory_data SET difficulty = (difficulty +1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun increaseMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET difficulty = (difficulty -1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun decreaseMemoryDataDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET max_difficulty = (max_difficulty +1) WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun increaseMemoryDataMaxDifficulty(userId: Int, category: String?, subCategory: Int)

    @Query("UPDATE memory_data SET win_streak = 0, lose_streak = 0 WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun resetAllMemoryDataStreak(userId: Int, category: String?, subCategory: Int)

    //Card
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCard(memoryCard: Card?)

    @Update
    fun updateCard(memoryCard: Card?)

    @Query("SELECT * FROM cards WHERE value LIKE :cardValue")
    fun getCard(cardValue: String?): Card?

    @Query("SELECT image FROM cards WHERE value LIKE :cardValue")
    fun getCardDrawableImage(cardValue: String?): Int

    @Query("SELECT * FROM cards WHERE type LIKE :type")
    fun getAllCardByType(type: String?): List<Card?>?

    @Query("SELECT * FROM cards")
    fun getAllCard(): List<Card?>?


    //MemoryDataCardCrossRef
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMemoryDataCard(memoryDataCardCrossRef: MemoryDataCardCrossRef?)

    @Update
    fun updateMemoryDataCard(memoryDataCardCrossRef: MemoryDataCardCrossRef?)

    @Query("SELECT * FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    fun getMemoryDataCard(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?
    ): MemoryDataCardCrossRef?

    @Query("SELECT used FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    fun getMemoryDataCardUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?
    ): Int

    @Query("SELECT * FROM memory_data_card_cross_ref")
    fun getAllMemoryDataCard(): List<MemoryDataCardCrossRef?>?

    @Query("SELECT * FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory")
    fun getAllMemoryDataCardByUser(
        userId: Int,
        category: String?,
        subCategory: Int
    ): List<MemoryDataCardCrossRef?>?

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND used = 0")
    fun getMemoryDataCardNbNotUsed(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND :maxUsed != used")
    fun getMemoryDataCardNbNotMaxUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        maxUsed: Int
    ): Int

    @Query("SELECT MAX(used) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun getMemoryDataCardMaxUsed(userId: Int, category: String?, subCategory: Int): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subCategory = :subCategory AND used <= (:value-1)")
    fun getMemoryDataCardNbUsedLessThan(
        userId: Int,
        category: String?,
        subCategory: Int,
        value: Int
    ): Int

    @Query("SELECT COUNT(*) FROM memory_data_card_cross_ref WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun getMemoryDataCardNbTotal(userId: Int, category: String?, subCategory: Int): Int

    @Query("UPDATE memory_data_card_cross_ref SET used = :used WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory AND card_value LIKE :cardValue")
    fun updateMemoryDataCardUsed(
        userId: Int,
        category: String?,
        subCategory: Int,
        cardValue: String?,
        used: Int
    )

    @Query("UPDATE memory_data_card_cross_ref SET used = 0 WHERE user_id = :userId AND category LIKE :category AND subcategory = :subCategory")
    fun resetAllMemoryDataCardUsed(userId: Int, category: String?, subCategory: Int)

}