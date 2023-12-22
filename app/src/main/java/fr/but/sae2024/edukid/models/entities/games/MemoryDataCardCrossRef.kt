package fr.but.sae2024.edukid.models.entities.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memory_data_card_cross_ref")
data class MemoryDataCardCrossRef(

    @PrimaryKey
    @ColumnInfo(name = "card_value")
    var cardValue : String,

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var userId : Int,

    @PrimaryKey
    @ColumnInfo(name = "category")
    var category : String,

    @PrimaryKey
    @ColumnInfo(name = "subcategory")
    var subCategory : Int,

    @ColumnInfo(name = "used")
    var used : Int,
)
