package fr.but.sae2024.edukid.views.themes.menu

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.themes.ThemeViewModel
import fr.but.sae2024.edukid.views.themes.adapters.ThemeSelectionAdapter
import timber.log.Timber

class ThemeSelectionActivity() : AppCompatActivity() {

    val themeViewModel by viewModels<ThemeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("ThemeSelectionActivity").e("onCreate called")
        setContentView(R.layout.theme_selection_activity)

        val themeRv: RecyclerView = findViewById(R.id.rv_theme_selection_activity)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        themeViewModel.listThemeLiveData.observe(this) { themes ->

            val listTheme = themes
            val adapter = ThemeSelectionAdapter(listTheme)
            themeRv.adapter = adapter
            themeRv.layoutManager = LinearLayoutManager(this@ThemeSelectionActivity)

            adapter.themeLD.observe(this) {theme ->
                themeViewModel.themeDefine(theme, this)
            }
        }
        themeViewModel.getListTheme()
    }


    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            RouteManager.startActivity(
                this@ThemeSelectionActivity,
                ActivityName.UserSelectionActivity,
                false,
                true
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}