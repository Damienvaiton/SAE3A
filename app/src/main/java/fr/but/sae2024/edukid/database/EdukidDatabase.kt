package fr.but.sae2024.edukid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.models.app.User
import fr.but.sae2024.edukid.utils.Converters

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class EdukidDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

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