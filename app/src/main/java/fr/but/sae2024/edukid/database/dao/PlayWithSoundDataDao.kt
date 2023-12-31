package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.games.PlayWithSoundData

@Dao
interface PlayWithSoundDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPWSData(playWithSoundData: PlayWithSoundData)

    @Update
    suspend fun updatePWSData(playWithSoundData: PlayWithSoundData)

    @Query("UPDATE play_with_sound_data SET last_used = 0 WHERE user_id = :userId AND last_used = 1")
    suspend fun updateAllPWSDataLastUsed(userId: Int)

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND result LIKE :result")
    fun getPWSDataByResult(userId: Int, result: String?): PlayWithSoundData?

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName")
    suspend fun getAllPWSDataByTheme(userId: Int, themeName: String?): List<PlayWithSoundData?>?

    @Query("SELECT * FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName AND difficulty = :difficulty")
    suspend fun getAllPWSDataByThemeAndDifficulty(
        userId: Int,
        themeName: String?,
        difficulty: Int
    ): List<PlayWithSoundData?>?

    @Query("SELECT max(difficulty) FROM play_with_sound_data WHERE user_id = :userId AND theme LIKE :themeName")
    suspend fun getPWSDataMaxDifficulty(userId: Int, themeName: String?): Int

    suspend fun getAllPWSDataLastUsed(
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
    suspend fun deletePWSDataByUser(userId: Int)
}