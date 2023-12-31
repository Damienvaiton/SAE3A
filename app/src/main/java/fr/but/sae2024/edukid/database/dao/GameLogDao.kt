package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.logs.GameLog


@Dao
interface GameLogDao {
    //GAME LOG
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameLog(gameLog: GameLog)

    @Query("SELECT * FROM games_logs WHERE user_id = :userId")
    suspend fun getAllGameLogByUserId(userId: Int): List<GameLog?>

    @Query("SELECT * FROM games_logs WHERE user_id = :userId ORDER BY ended_at DESC LIMIT 60")
    suspend fun getAllGameLogByUserLimit(userId: Int): List<GameLog>

    @Query("SELECT l.* FROM games_logs  AS l NATURAL JOIN games AS g WHERE l.user_id = :userId AND g.theme LIKE :themeName ORDER BY ended_at DESC LIMIT 60")
    suspend fun getAllGameLogByUserLimitByTheme(userId: Int, themeName: String?): List<GameLog>

    @Query("SELECT * FROM games_logs WHERE user_id = :userId AND ended_at >= :minTime")
    suspend fun getAllGameLogAfterTime(userId: Int, minTime: Long): List<GameLog>

    @Query("SELECT l.* FROM games_logs as l NATURAL JOIN games AS g WHERE l.user_id = :userId AND g.theme LIKE :themeName AND l.ended_at >= :minTime")
    suspend fun getAllGameLogAfterTimeByTheme(
        userId: Int,
        themeName: String?,
        minTime: Long
    ): List<GameLog?>?

    @Query("SELECT * FROM games_logs WHERE user_id = :userId AND game_id = :gameId")
    suspend fun getAllGameLogByGame(userId: Int, gameId: Int): List<GameLog?>?

    @Query("SELECT count(*) FROM games_logs WHERE user_id = :userId AND game_id = :gameId")
    suspend fun getGameLogNbGame(userId: Int, gameId: Int): Int

    @Query("SELECT stars FROM games_logs WHERE user_id = :userId AND game_id = :gameId ORDER BY ended_at DESC LIMIT :limit")
    suspend fun getAllGameLogStarsLimit(userId: Int, gameId: Int, limit: Int): List<Int?>?

    @Query("SELECT avg(stars) FROM games_logs WHERE user_id = :userId")
    suspend fun getGameLogAvgStars(userId: Int): Float

    @Query("SELECT count(*) FROM games_logs WHERE user_id = :userId")
    suspend fun getGameLogNb(userId: Int): Int

    @Query("SELECT max(difficulty) FROM games_logs WHERE user_id = :userId AND game_id = :gameId")
    suspend fun getGameLogMaxDifByGame(userId: Int, gameId: Int): Int

    @Query("SELECT * FROM games_logs WHERE user_id = :userId AND game_id = :gameId AND subgame_id = :subGameId")
    suspend fun getAllGameLogBySubGame(userId: Int, gameId: Int, subGameId: Int): List<GameLog?>?

    @Query("SELECT g.* FROM games AS g NATURAL JOIN games_logs AS l WHERE l.user_id = :userId AND g.id = l.game_id AND g.theme LIKE :themeName GROUP BY g.id")
    suspend fun getAllGamePlayed(userId: Int, themeName: String?): List<Game>

    @Query("SELECT s.* FROM subgames AS s NATURAL JOIN games_logs AS l WHERE l.user_id = :userId AND l.game_id = :gameId AND s.game_id = l.game_id GROUP BY s.id")
    suspend fun getAllSubGamePlayed(userId: Int, gameId: Int): List<Subgame>

    @Query("SELECT avg(stars) FROM games_logs WHERE user_id = :userId AND game_id = :gameId AND difficulty = :difficulty")
    suspend fun getGameAvgByGameIdAndDifficulty(userId: Int, gameId: Int, difficulty: Int): Float

    @Query("SELECT avg(stars) FROM games_logs AS l NATURAL JOIN subgames AS s WHERE l.user_id = :userId AND l.game_id = :gameId AND l.game_id = s.game_id AND l.subgame_id = :subGameId AND l.difficulty = :difficulty")
    suspend fun getGameAvgBySubGameIdAndDifficulty(
        userId: Int,
        gameId: Int,
        subGameId: Int,
        difficulty: Int
    ): Float

    suspend fun tabGameLogIsEmpty(userId: Int): Boolean {
        return getAllGameLogByUserId(userId).isEmpty()
    }

    @Query("DELETE FROM games_logs WHERE user_id = :userId")
    suspend fun deleteGameLogDataByUser(userId: Int)

}