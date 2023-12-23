package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.app.Subgame

@Dao
interface SubgameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubGame(subGame: Subgame)

    @Query("SELECT * FROM subgames")
    suspend fun getAllSubGames(): List<Subgame>

    @Query("SELECT * FROM subgames WHERE game_id = :gameId")
    suspend fun getAllSubGamesByGame(gameId: Int): List<Subgame>

    @Query("SELECT * FROM subgames WHERE id = :subGameId")
    suspend fun getSubGameById(subGameId: Int): Subgame

    @Query("SELECT * FROM subgames WHERE game_id = :gameId AND name LIKE :subGameName")
    suspend fun getSubGameByNameAndGame(gameId: Int, subGameName: String?): Subgame?

    @Query("SELECT g.name FROM games AS g NATURAL JOIN subgames AS s WHERE g.id = s.game_id AND s.id != -1")
    suspend fun getAllGameNameWithSubGame(): List<String>

    @Update
    suspend fun updateSubGame(subGame: Subgame)

    @Query("DELETE FROM subgames WHERE id = :subGameId")
    suspend fun deleteSubGameById(subGameId: Int)

    @Query("DELETE FROM subgames")
    suspend fun deleteAllSubGames()

    suspend fun tabSubGameIsEmpty(): Boolean {
        return getAllSubGames().isEmpty()
    }
}