package fr.but.sae2024.edukid.models.entities.logs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_logs")
data class GameLog(

    @ColumnInfo(name = "game")
    val game: Int,

    @ColumnInfo(name = "subgame")
    val subgame: Int? = null,

    @ColumnInfo(name = "user")
    val user: Int,

    @ColumnInfo(name = "stars")
    val stars: Int,

    @ColumnInfo(name = "diffuculty")
    val diffuculty: Int,

    @ColumnInfo(name = "ended_at")
    val ended_at: Long,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}

