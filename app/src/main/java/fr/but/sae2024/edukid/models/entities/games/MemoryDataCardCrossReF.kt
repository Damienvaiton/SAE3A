package fr.but.sae2024.edukid.models.entities.games

import androidx.room.Entity

@Entity(
    tableName = "memory_data_card_cross_ref",
    primaryKeys = ["cardValue", "userId", "category", "subCategory"]
)
data class MemoryDataCardCrossReF(

    var cardValue : String,
    var userId : Int,
    var category : String,
    var subCategory : Int,
    var used : Int,
)
