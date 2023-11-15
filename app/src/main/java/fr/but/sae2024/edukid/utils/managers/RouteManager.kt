package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.content.Intent
import android.util.Log


enum class ActivitysName {
    UserSelectionActivity, ThemeSelectionActivity, GameSelectionActivity, SubGameSelectionActivity, ResultGameActivity, UserDetailActivity, UserManagingActivity
}

enum class GamesName {
    Listen, Draw, Memory, WordBlank}

class RouteManager {

    public fun startActicity(context : Context, activitysName: ActivitysName, wantToFinish: Boolean, animation: Boolean){

        when (activitysName) {

            ActivitysName.UserSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserSelectionActivity::class.java
                )
            )

            ActivitysName.ThemeSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    ThemeSelectionActivity::class.java
                )
            )

            ActivitysName.GameSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    GameSelectionActivity::class.java
                )
            )

            ActivitysName.SubGameSelectionActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    SubGameSelectionActivity::class.java
                )
            )

            ActivitysName.ResultGameActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    ResultGameActivity::class.java
                )
            )

            ActivitysName.UserDetailActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserDetailActivity::class.java
                )
            )

            ActivitysName.UserManagingActivity -> context.startActivity(
                Intent().setClass(
                    context,
                    UserManagingActivity::class.java
                )
            )

            else -> if (activitysName.toString().isNotEmpty()) {
                Log.e(
                    Log.ERROR.toString(),
                    activitysName.toString() + "is not allowed for a name's activity"
                )
            } else {
                Log.e(Log.ERROR.toString(), "The name's activity is empty")
            }
        }
    }

    public fun startGame(context : Context, gamesName: GamesName, wantToFinish: Boolean, animation: Boolean) {

        when (gamesName) {


            GamesName.Draw -> context.startActivity(
                Intent().setClass(
                    context,
                    UserManagingActivity::class.java
                )
            )

            else -> if (GamesName.toString().isNotEmpty()) {
                Log.e(
                    Log.ERROR.toString(),
                    GamesName.toString() + "is not allowed for a name's game"
                )
            } else {
                Log.e(Log.ERROR.toString(), "The name's game is empty")
            }

        }
    }
}
