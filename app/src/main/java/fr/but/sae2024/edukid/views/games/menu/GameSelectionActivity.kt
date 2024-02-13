package fr.but.sae2024.edukid.views.games.menu

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.games.adapters.GameSelectionAdapter
import timber.log.Timber

class GameSelectionActivity : AppCompatActivity() {

    private val gameViewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)

        val gameRV: RecyclerView = findViewById(R.id.recyclerview_game)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        gameViewModel.listGameLiveData.observe(this) { games ->
            val listGame = games
            val adapter = GameSelectionAdapter(listGame)
            gameRV.adapter = adapter
            gameRV.layoutManager = LinearLayoutManager(this@GameSelectionActivity)
            gameRV.setHasFixedSize(true)

            adapter.gameLD.observe(this) { game ->
                gameViewModel.gameDefine(game, this)
                Timber.tag("GameSelectionActivity").e("Game by the observe : ${game.name}")
            }
        }


        gameViewModel.getAllGamesByTheme(this)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            RouteManager.startActivity(
                this@GameSelectionActivity,
                ActivityName.ThemeSelectionActivity,
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