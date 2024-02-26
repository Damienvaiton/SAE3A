package fr.but.sae2024.edukid.models.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.models.entities.games.GameData

data class UserAndGameData(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val gameData: List<GameData>
)
