package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.app.Word

@Dao
interface AppDao {
    //ThemeDao
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTheme(theme: Theme?)

    @Query("SELECT * FROM themes")
    fun getAllThemes(): List<Theme?>?

    @Query("SELECT * FROM themes WHERE name = :themeName")
    fun getThemeByName(themeName: String?): Theme?

    @Update
    fun updateTheme(theme: Theme?)

    @Query("DELETE FROM themes WHERE name = :themeName")
    fun deleteThemeByName(themeName: Int)

    @Query("DELETE FROM themes")
    fun deleteAllThemes()


    //GameDao
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGame(game: Game?)

    @Query("SELECT * FROM games")
    fun getAllGames(): List<Game?>

    @Query("SELECT * FROM games WHERE theme = :themeName")
    fun getAllGamesByTheme(themeName: String?): List<Game?>?

    @Query("SELECT * FROM games WHERE id = :gameId")
    fun getGameById(gameId: Int): Game?

    @Query("SELECT name FROM games WHERE id = :gameId")
    fun getGameNameByGameId(gameId: Int): String?

    @Query("SELECT theme FROM games WHERE id = :gameId")
    fun getThemeByGameId(gameId: Int): String?

    @Query("SELECT id FROM games WHERE name = :gameName AND theme = :themeName")
    fun getGameId(gameName: String?, themeName: String?): Int

    @Update
    fun updateGame(game: Game?)

    @Query("DELETE FROM games WHERE id = :gameId")
    fun deleteGameById(gameId: Int)

    @Query("DELETE FROM games")
    fun deleteAllGames()

    fun tabGameIsEmpty(): Boolean {
        return getAllGames().isEmpty()
    }


    //SubGame
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubGame(subGame: Subgame?)

    @Query("SELECT * FROM subgames")
    fun getAllSubGames(): List<Subgame?>

    @Query("SELECT * FROM subgames WHERE game_id = :gameId")
    fun getAllSubGamesByGame(gameId: Int): List<Subgame?>?

    @Query("SELECT * FROM subgames WHERE id = :subGameId")
    fun getSubGameById(subGameId: Int): Subgame?

    @Query("SELECT * FROM subgames WHERE game_id = :gameId AND name LIKE :subGameName")
    fun getSubGameByNameAndGame(gameId: Int, subGameName: String?): Subgame?

    @Query("SELECT g.name FROM games AS g NATURAL JOIN subgames AS s WHERE g.id = s.game_id AND s.id != -1")
    fun getAllGameNameWithSubGame(): List<String?>?

    @Update
    fun updateSubGame(subGame: Subgame?)

    @Query("DELETE FROM subgames WHERE id = :subGameId")
    fun deleteSubGameById(subGameId: Int)

    @Query("DELETE FROM subgames")
    fun deleteAllSubGames()

    fun tabSubGameIsEmpty(): Boolean {
        return getAllSubGames().isEmpty()
    }


    //UserDao
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User?)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User?>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): User?

    @Query("SELECT * FROM users WHERE userName = :userName")
    fun getUserByName(userName: String?): User?

    @Query("SELECT picture FROM users WHERE picture_type != 0 ORDER BY picture DESC LIMIT 1")
    fun getUserImageMaxInt(): String?

    @Update
    fun updateUser(user: User?)

    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUserById(userId: Int)

    @Query("DELETE FROM users")
    fun deleteAllUsers()

    fun tabUserIsEmpty(): Boolean {
        return getAllUsers().isEmpty()
    }


    //Word
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(word: Word?)

    @Query("SELECT * FROM words")
    fun getAllWords(): List<Word?>?

    @Query("SELECT COUNT(*) FROM words")
    fun getNbWords(): Int

    @Query("SELECT * FROM words WHERE word LIKE :str")
    fun getWordIfContain(str: String?): List<Word?>?
}