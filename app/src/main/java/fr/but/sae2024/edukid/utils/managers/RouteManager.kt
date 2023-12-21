package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.content.Intent
import android.util.Log
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.enums.GameName
import fr.but.sae2024.edukid.views.Games.Menu.GameSelectionActivity
import fr.but.sae2024.edukid.views.Games.Play.DrawOnItGame
import fr.but.sae2024.edukid.views.Games.Play.MemoryGame
import fr.but.sae2024.edukid.views.Games.Play.PlayWithSoundGame
import fr.but.sae2024.edukid.views.Games.Play.WordWithHoleGame
import fr.but.sae2024.edukid.views.ResultsGame.ResultGameActivity
import fr.but.sae2024.edukid.views.Statistics.StatisticActivity
import fr.but.sae2024.edukid.views.Themes.ThemeSelectionActivity
import fr.but.sae2024.edukid.views.Users.Edit.UserManagingActivity
import fr.but.sae2024.edukid.views.Users.Menu.UserSelectionActivity
import fr.but.sae2024.edukid.views.Users.Resume.UserDetailActivity
import timber.log.Timber

class RouteManager {

    public fun startActicity(context : Context, activityName: ActivityName, wantToFinish: Boolean, animation: Boolean){

        when (activityName) {

            ActivityName.UserSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserSelectionActivity::class.java
                )
            )

            ActivityName.ThemeSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    ThemeSelectionActivity::class.java
                )
            )

            ActivityName.GameSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    GameSelectionActivity::class.java
                )
            )

            ActivityName.SubGameSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    ActivityName.SubGameSelectionActivity::class.java
                )
            )

            ActivityName.ResultGameActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    ResultGameActivity::class.java
                )
            )

            ActivityName.UserDetailActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserDetailActivity::class.java
                )
            )

            ActivityName.UserManagingActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserManagingActivity::class.java
                )
            )

            ActivityName.StatisticActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    StatisticActivity::class.java
                )
            )

            else -> Timber.tag(Log.ERROR.toString()).e("Problem with the activity name")
        }
    }

    public fun startGame(context : Context, gamesName: GameName, wantToFinish: Boolean, animation: Boolean) {

        when (gamesName) {


            GameName.Draw -> context.startActivity(
                Intent().setClass(
                    context,
                    DrawOnItGame::class.java
                )
            )

            GameName.Listen -> context.startActivity(
                Intent().setClass(
                    context,
                    PlayWithSoundGame::class.java
                )
            )

            GameName.WordBlank -> context.startActivity(
                Intent().setClass(
                    context,
                    WordWithHoleGame::class.java
                )
            )

            GameName.Memory -> context.startActivity(
                Intent().setClass(
                    context,
                    MemoryGame::class.java
                )
            )

            else -> Timber.tag(Log.ERROR.toString()).e("Problem with the game name")

        }
    }
}
