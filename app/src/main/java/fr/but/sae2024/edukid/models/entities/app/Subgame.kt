package fr.but.sae2024.edukid.models.entities.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subgames")
data class Subgame(

    @ColumnInfo(name = "num")
    val num: Int,

    @ColumnInfo(name = "name")
    val name: String = "Niveau $num",

    @ColumnInfo(name = "game_id")
    val gameId: Int,

    @ColumnInfo(name = "image")
    val image: Int? = null,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
