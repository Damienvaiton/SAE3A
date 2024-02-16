package fr.but.sae2024.edukid.models.entities.games

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "memory_data",
    primaryKeys = ["user_id", "category", "subcategory"]
)
data class GameData(

    @ColumnInfo(name = "user_id")
    var userId : Int,

    @ColumnInfo(name = "theme")
    var theme : String,

    @ColumnInfo(name = "game")
    var game : Int,

    @ColumnInfo(name = "subgame")
    var subgame : Int? = null,

    @ColumnInfo(name = "win")
    var win : Boolean = true, //true de base car memory est toujours gagnant

    @ColumnInfo(name = "stars")
    var stars : Int = 0,

    @ColumnInfo(name = "date")
    var date : Long = 0,

    //Memory

    //Draw on it

    @ColumnInfo(name = "draw")
    var draw : String? = null,

    @ColumnInfo(name = "touch_time")
    var touchTime : Long? = null,

    //Play with sounds

    @ColumnInfo(name = "sound")
    var sound : String? = null,

    //Word with hole

    @ColumnInfo(name = "word")
    var word : String? = null,

    )