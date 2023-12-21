package fr.but.sae2024.edukid.models.entities.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.but.sae2024.edukid.utils.enums.UserRole

@Entity(tableName = "users")
data class User (

    @ColumnInfo(name = "username")
    val username: String? = null,

    @ColumnInfo(name = "password")
    val password: String? = null,

    @ColumnInfo(name = "mail")
    val mail: String? = null,

    @ColumnInfo(name = "picture")
    val picture: String? = null,

    @ColumnInfo(name = "picture_type")
    val pictureType: Int? = null,

    @ColumnInfo(name = "role")
    val role: UserRole = UserRole.CHILD,

    @ColumnInfo(name = "created")
    val created: Long? = null,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}