package fr.but.sae2024.edukid.utils

import androidx.room.TypeConverter
import fr.but.sae2024.edukid.utils.enums.UserRole

class Converters {
    @TypeConverter
    fun fromUserRole(value: UserRole):String {
        return value.text
    }
}