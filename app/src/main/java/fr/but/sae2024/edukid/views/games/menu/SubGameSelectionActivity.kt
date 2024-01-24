package fr.but.sae2024.edukid.views.games.menu

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.games.adapters.GameSelectionAdapter
import fr.but.sae2024.edukid.views.games.adapters.SubGameSelectionAdapter
import fr.but.sae2024.edukid.views.themes.ThemeViewModel
import timber.log.Timber

class SubGameSelectionActivity : AppCompatActivity() {

    private val subGameViewModel by viewModels<SubGameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_game_menu)

        val gameRV: RecyclerView = findViewById(R.id.recyclerview_sub_game)

        subGameViewModel.listSubGameLiveData.observe(this) { subGames ->
            val listSubGame = subGames
            val adapter = SubGameSelectionAdapter(listSubGame)
            gameRV.adapter = adapter
            gameRV.layoutManager = LinearLayoutManager(this@SubGameSelectionActivity)
            gameRV.setHasFixedSize(true)

            adapter.subGameLD.observe(this) { subGame ->
                subGameViewModel.subGameDefine(subGame, this)
                Timber.tag("SubGameSelectionActivity").e("SubGame by the observe : ${subGame.name}")
            }
        }

        subGameViewModel.getAllSubGamesByGame(this)

    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Timber.e("onBackPressed called")
        RouteManager.startActivity(this, ActivityName.ThemeSelectionActivity, true, true)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }

}