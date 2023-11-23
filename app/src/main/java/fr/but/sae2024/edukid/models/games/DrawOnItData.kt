package fr.but.sae2024.edukid.models.games

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "draw_on_it",
    primaryKeys = ["user", "draw"]
)
data class DrawOnItData(

    @ColumnInfo(name = "user")
    val user : Int,

    @ColumnInfo(name = "draw")
    val draw : String,

    @ColumnInfo(name = "theme")
    val theme : String,

    @ColumnInfo(name = "touch_time")
    val touchTime : Long,

    @ColumnInfo(name = "difficulty")
    val difficulty : Int,

    @ColumnInfo(name = "win")
    val win : Int,

    @ColumnInfo(name = "lose")
    val lose : Int,

    @ColumnInfo(name = "win_streak")
    val winStreak : Int,

    @ColumnInfo(name = "lose_streak")
    val loseStreak : Int,

    @ColumnInfo(name = "last_used")
    val lastUsed : Long,
)
