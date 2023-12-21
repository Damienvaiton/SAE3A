package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.content.Intent
import android.util.Log
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.enums.GameName
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
                    SubGameSelectionActivity::class.java
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

            else -> if (activityName.toString().isNotEmpty()) {
                Timber.tag(Log.ERROR.toString())
                    .e("%sis not allowed for a name's activity", activityName.toString())
            } else {
                Timber.tag(Log.ERROR.toString()).e("The name's activity is empty")
            }
        }
    }

    public fun startGame(context : Context, gamesName: GameName, wantToFinish: Boolean, animation: Boolean) {

        when (gamesName) {


            GameName.Draw -> context.startActivity(
                Intent().setClass(
                    context,
                    UserManagingActivity::class.java
                )
            )

            GameName.Listen -> context.startActivity(
                Intent().setClass(
                    context,
                    UserManagingActivity::class.java
                )
            )

            else -> {
                Timber.tag(Log.ERROR.toString())
                    .e("%s isn't used in the game", gamesName.toString())
            }

        }
    }
}
