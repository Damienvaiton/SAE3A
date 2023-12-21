package fr.but.sae2024.edukid.models.entities.games

import androidx.room.Entity

@Entity (
    tableName = "word_with_hole_data",
    primaryKeys = ["userId", "result"]
)
data class WordWithHoleData(
    var userId : Int,

    var result : String,

    var difficulty : Int,
    var win : Int,
    var winStreak : Int,
    var lose : Int,
    var loseStreak : Int,
    var lastUsed : Int,
)
