package fr.but.sae2024.edukid.models.entities.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "draw_on_it",
    primaryKeys = ["user_id", "draw"]
)
data class DrawOnItData(

    @ColumnInfo(name = "user_id")
    val userId : Int,

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
