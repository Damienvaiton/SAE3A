package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.app.Game

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: Game?)

    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<Game?>

    @Query("SELECT * FROM games WHERE theme = :themeName")
    suspend fun getAllGamesByTheme(themeName: String?): List<Game?>?

    @Query("SELECT * FROM games WHERE id = :gameId")
    suspend fun getGameById(gameId: Int): Game?

    @Query("SELECT name FROM games WHERE id = :gameId")
    suspend fun getGameNameByGameId(gameId: Int): String?

    @Query("SELECT theme FROM games WHERE id = :gameId")
    suspend fun getThemeByGameId(gameId: Int): String?

    @Query("SELECT id FROM games WHERE name = :gameName AND theme = :themeName")
    suspend fun getGameId(gameName: String?, themeName: String?): Int

    @Update
    suspend fun updateGame(game: Game?)

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGameById(gameId: Int)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    suspend fun tabGameIsEmpty(): Boolean {
        return getAllGames().isEmpty()
    }
}