package fr.but.sae2024.edukid.models

import androidx.room.ColumnInfo
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Subgame
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.games.Card

data class MemoryData(
    var user : User,
    var theme : Theme,
    var game : Game,
    var subgame : Subgame,
    var listCards : List<Card> = listOf(),
    var numberColumn : Int
)
