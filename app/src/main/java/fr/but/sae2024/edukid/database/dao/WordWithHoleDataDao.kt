package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.WordWithHoleData

@Dao
interface WordWithHoleDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWWHData(wordWithHoleStats: WordWithHoleData?)

    @Update
    suspend fun updateWWHData(wordWithHoleStats: WordWithHoleData?)

    @Query("UPDATE word_with_hole_data SET last_used = 0 WHERE user_id = :userId AND last_used = 1")
    suspend fun updateAllWWHDataLastUsed(userId: Int)

    @Query("SELECT max(difficulty) FROM word_with_hole_data WHERE user_id = :userId")
    suspend fun getWWHDataMaxDifficulty(userId: Int): Int

    @Query("SELECT * FROM word_with_hole_data WHERE user_id = :userId AND result = :result")
    suspend fun getWWHDataByResult(userId: Int, result: String?): WordWithHoleData?

    @Query("SELECT * FROM word_with_hole_data WHERE user_id = :userId")
    suspend fun getAllWWHData(userId: Int): List<WordWithHoleData?>?

    suspend fun getAllWWHDataLastUsed(
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
    suspend fun deleteWWHDataByUser(userId: Int)
}