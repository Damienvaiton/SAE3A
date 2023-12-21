package fr.but.sae2024.edukid.models.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(

    @PrimaryKey
    @ColumnInfo(name = "value")
    val value: String,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "image")
    val image: Int? = null,
)
