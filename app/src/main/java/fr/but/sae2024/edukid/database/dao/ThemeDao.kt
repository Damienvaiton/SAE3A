package fr.but.sae2024.edukid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.but.sae2024.edukid.models.entities.app.Theme

@Dao
interface ThemeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTheme(theme: Theme)

    @Query("SELECT * FROM themes")
    suspend fun getAllThemes(): List<Theme?>

    @Query("SELECT * FROM themes WHERE name = :themeName")
    suspend fun getThemeByName(themeName: String?): Theme?

    @Update
    suspend fun updateTheme(theme: Theme)

    @Query("DELETE FROM themes WHERE name = :themeName")
    suspend fun deleteThemeByName(themeName: Int)

    @Query("DELETE FROM themes")
    suspend fun deleteAllThemes()

    suspend fun tabThemeIsEmpty(): Boolean {
        return getAllThemes().isEmpty()
    }
}