package fr.but.sae2024.edukid.utils.managers

import androidx.room.TypeConverter
import fr.but.sae2024.edukid.utils.enums.UserRole

object ConverterManager {
    @TypeConverter
    fun fromUserRole(value: UserRole):String {
        return value.text
    }
}