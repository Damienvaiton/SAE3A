package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object VibrateManager {
    private var vibe: Vibrator? = null

    fun vibrate(context: Context, duration: Int) {
        if (vibe == null) vibe = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) vibe!!.vibrate(
            VibrationEffect.createOneShot(
                duration.toLong(),
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        ) else vibe!!.vibrate(duration.toLong())
    }

}