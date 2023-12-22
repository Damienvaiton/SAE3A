package fr.but.sae2024.edukid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.but.sae2024.edukid.database.dao.AppDao
import fr.but.sae2024.edukid.database.dao.GameDao
import fr.but.sae2024.edukid.database.dao.GameLogDao
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.app.Word
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.models.entities.games.DrawOnItData
import fr.but.sae2024.edukid.models.entities.games.MemoryData
import fr.but.sae2024.edukid.models.entities.games.MemoryDataCardCrossRef
import fr.but.sae2024.edukid.models.entities.games.PlayWithSoundData
import fr.but.sae2024.edukid.models.entities.games.WordWithHoleData
import fr.but.sae2024.edukid.models.entities.logs.GameLog
import fr.but.sae2024.edukid.utils.managers.ConverterManager

@Database(entities = [
    User::class,
    Theme::class,
    Subgame::class,
    Word::class,
    Card::class,
    DrawOnItData::class,
    MemoryData::class,
    MemoryDataCardCrossRef::class,
    PlayWithSoundData::class,
    WordWithHoleData::class,
    GameLog::class
                     ], version = 1)
@TypeConverters(ConverterManager::class)
abstract class EdukidDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun appDao(): AppDao
    abstract fun gameLogDao(): GameLogDao

    abstract fun gameDao(): GameDao

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