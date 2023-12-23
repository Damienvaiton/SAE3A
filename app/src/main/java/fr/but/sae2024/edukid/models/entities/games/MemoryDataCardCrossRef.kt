package fr.but.sae2024.edukid.models.entities.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "memory_data_card_cross_ref",
    primaryKeys = ["card_value", "user_id", "category", "subcategory"]
)
data class MemoryDataCardCrossRef(

    @ColumnInfo(name = "card_value")
    var cardValue : String,

    @ColumnInfo(name = "user_id")
    var userId : Int,

    @ColumnInfo(name = "category")
    var category : String,

    @ColumnInfo(name = "subcategory")
    var subCategory : Int,

    @ColumnInfo(name = "used")
    var used : Int,
)
