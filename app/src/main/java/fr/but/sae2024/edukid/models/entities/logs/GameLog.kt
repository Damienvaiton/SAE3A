package fr.but.sae2024.edukid.models.entities.logs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_logs")
data class GameLog(

    @ColumnInfo(name = "game_id")
    val gameId: Int,

    @ColumnInfo(name = "subgame_id")
    val subgameId: Int? = null,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "stars")
    val stars: Int,

    @ColumnInfo(name = "difficulty")
    val diffuculty: Int,

    @ColumnInfo(name = "ended_at")
    val ended_at: Long,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}

