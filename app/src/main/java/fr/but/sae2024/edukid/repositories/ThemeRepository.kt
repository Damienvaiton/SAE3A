package fr.but.sae2024.edukid.repositories

import fr.but.sae2024.edukid.cache.CacheDatasource
import fr.but.sae2024.edukid.database.DatabaseDatasource
import fr.but.sae2024.edukid.models.entities.app.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ThemeRepository {

    private val database = DatabaseDatasource
    private val cache = CacheDatasource

    fun getAllTheme(): Flow<List<Theme?>> = flow {
        emit(database.getAllTheme())
    }

    fun setSelectedTheme(theme : Theme): Flow<Boolean> = flow {
        try {
            cache.setSelectedTheme(theme)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun getSelectedTheme(): Flow<Theme?> = flow {
        emit(cache.getSelectedTheme())
    }
}