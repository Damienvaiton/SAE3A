package fr.but.sae2024.edukid.models.entities.games

import androidx.room.Entity

@Entity (
    tableName = "play_with_sound_data",
    primaryKeys = ["userId", "result"]
)
data class PlayWithSoundData (

        var userId : Int,

        var result : String,
        var theme : String?,

        var difficulty : Int,
        var win : Int,
        var winStreak : Int,
        var lose : Int,
        var loseStreak : Int,
        var lastUsed : Int,
)