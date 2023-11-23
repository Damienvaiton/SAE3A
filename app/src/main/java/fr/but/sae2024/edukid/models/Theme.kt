package fr.but.sae2024.edukid.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "themes")
data class Theme (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val word: String? = null,

    @ColumnInfo(name = "image")
    val image: Int? = null
)
