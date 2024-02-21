package fr.but.sae2024.edukid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.but.sae2024.edukid.database.dao.CardDao
import fr.but.sae2024.edukid.database.dao.GameDao
import fr.but.sae2024.edukid.database.dao.GameDataDao
import fr.but.sae2024.edukid.database.dao.GameLogDao
import fr.but.sae2024.edukid.database.dao.SubgameDao
import fr.but.sae2024.edukid.database.dao.ThemeDao
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.database.dao.WordDao
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.app.Word
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.models.entities.games.GameData
import fr.but.sae2024.edukid.models.entities.logs.GameLog
import fr.but.sae2024.edukid.utils.managers.ConverterManager

@Database(entities = [
    Game::class,
    User::class,
    Theme::class,
    Subgame::class,
    Word::class,
    Card::class,
    GameData::class,
    GameLog::class], version = 1)
@TypeConverters(ConverterManager::class)
abstract class EdukidDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun wordDao(): WordDao
    abstract fun gameDao(): GameDao
    abstract fun subgameDao(): SubgameDao
    abstract fun themeDao(): ThemeDao
    abstract fun gameLogDao(): GameLogDao
    abstract fun cardDao(): CardDao
    abstract fun gameDataDao(): GameDataDao


    companion object {
        private lateinit var instance: EdukidDatabase
        fun initDatabase(context: Context) {
            this.instance = Room.databaseBuilder(
                context,
                EdukidDatabase::class.java,
                "Edukid"
            ).build()
        }

        fun getInstance(): EdukidDatabase {
            return instance
        }
    }
}