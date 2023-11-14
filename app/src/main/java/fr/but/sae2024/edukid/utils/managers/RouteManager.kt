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

            ActivitysName.UserSelectionActivity -> context.startActivity(Intent().setClass(context, UserSelectionActivity::class.java))

            ActivitysName.ThemeSelectionActivity -> context.startActivity(Intent().setClass(context, ThemeSelectionActivity::class.java))

            else -> if(activitysName.toString().isNotEmpty()){
                    Log.e(Log.ERROR.toString(),activitysName.toString() + "is not allowed for a name's activity")
                }else {
                    Log.e(Log.ERROR.toString(),"The name activity is empty")
                }
            }
        }

    }

}
