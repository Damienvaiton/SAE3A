package fr.but.sae2024.edukid.models.entities.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "memory_data",
    primaryKeys = ["user_id", "category", "subcategory"]
)
data class MemoryData (

    @ColumnInfo(name = "user_id")
    var userId : Int,

    @ColumnInfo(name = "category")
    var category : String,

    @ColumnInfo(name = "subcategory")
    var subCategory : Int,

    @ColumnInfo(name = "difficulty")
    var difficulty : Int,

    @ColumnInfo(name = "max_difficulty")
    var maxDifficulty : Int,

    @ColumnInfo(name = "win_streak")
    var winStreak : Int,

    @ColumnInfo(name = "lose_streak")
    var loseStreak : Int,
)