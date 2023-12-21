package fr.but.sae2024.edukid.models.entities.games

import androidx.room.Entity

@Entity(
    tableName = "memory_data",
    primaryKeys = ["userId", "category", "subCategory"]
)
data class MemoryData (

    var userId : Int,

    var category : String,
    var subCategory : Int,
    var difficulty : Int,
    var maxDifficulty : Int,
    var winStreak : Int,
    var loseStreak : Int,
)