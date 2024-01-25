package fr.but.sae2024.edukid.utils.managers

import android.app.Activity
import android.app.Activity.OVERRIDE_TRANSITION_OPEN
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.enums.GameName
import fr.but.sae2024.edukid.views.games.menu.GameSelectionActivity
import fr.but.sae2024.edukid.views.games.menu.SubGameSelectionActivity
import fr.but.sae2024.edukid.views.games.play.DrawOnItGame
import fr.but.sae2024.edukid.views.games.play.MemoryGame
import fr.but.sae2024.edukid.views.games.play.PlayWithSoundGame
import fr.but.sae2024.edukid.views.games.play.WordWithHoleGame
import fr.but.sae2024.edukid.views.resultsGame.ResultGameActivity
import fr.but.sae2024.edukid.views.statistics.StatisticActivity
import fr.but.sae2024.edukid.views.themes.menu.ThemeSelectionActivity
import fr.but.sae2024.edukid.views.users.edit.UserManagingActivity
import fr.but.sae2024.edukid.views.users.menu.UserSelectionActivity
import fr.but.sae2024.edukid.views.users.resume.UserAddActivity
import timber.log.Timber


object RouteManager {

    fun startActivity(context : Context, activityName: ActivityName, wantToFinish: Boolean = true, animation: Boolean = false) {

        if (animation) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                (context as Activity).overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.fadein, R.anim.fadeout)
            } else {
                (context as Activity).overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }
        }

        when (activityName) {

            ActivityName.UserSelectionActivity -> {
                context.startActivity(
                    Intent(context, UserSelectionActivity::class.java)
                )
            }


            ActivityName.ThemeSelectionActivity -> {
                context.startActivity(
                    Intent(context, ThemeSelectionActivity::class.java)
                )
            }

            ActivityName.GameSelectionActivity -> {
                context.startActivity(
                    Intent(context, GameSelectionActivity::class.java)
                )
            }

            ActivityName.SubGameSelectionActivity -> {
                context.startActivity(
                    Intent(context, SubGameSelectionActivity::class.java)
                )
            }

            ActivityName.ResultGameActivity -> {
                context.startActivity(
                    Intent(context, ResultGameActivity::class.java)
                )
            }

            ActivityName.UserAddActivity -> {
                context.startActivity(
                    Intent(context, UserAddActivity::class.java)
                )
            }

            ActivityName.UserManagingActivity -> {
                context.startActivity(
                    Intent(context, UserManagingActivity::class.java)
                )
            }

            ActivityName.StatisticActivity -> {
                context.startActivity(
                    Intent(context, StatisticActivity::class.java)
                )
            }

            else -> Timber.tag(Log.ERROR.toString()).e("Problem with the activity name %s", activityName.toString())
        }

        if (wantToFinish) {
            (context as Activity).finish()
        }
        this.stopAllSound();
    }

    fun startGame(context : Context, gamesName: GameName, wantToFinish: Boolean = true, animation: Boolean = false) {

        if (animation) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                (context as Activity).overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.fadein, R.anim.fadeout)
            } else {
                (context as Activity).overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }
        }

        when (gamesName) {

            GameName.Draw -> {
                context.startActivity(
                    Intent(context, DrawOnItGame::class.java)
                )
            }

            GameName.Listen -> {
                context.startActivity(
                    Intent(context, PlayWithSoundGame::class.java)
                )
            }

            GameName.WordBlank -> {
                context.startActivity(
                    Intent(context, WordWithHoleGame::class.java)
                )
            }

            GameName.Memory -> {
                context.startActivity(
                    Intent(context, MemoryGame::class.java)
                )
            }

            else -> Timber.tag(Log.ERROR.toString()).e("Problem with the game name")
        }

        if (wantToFinish) {
            (context as Activity).finish()
        }
        this.stopAllSound();
    }

    fun stopAllSound() {
        MediaPlayerManager.stop()
    }
}
