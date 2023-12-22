package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.media.MediaPlayer


object MediaPlayerManager {
    private var ref: MediaPlayer? = null

    fun playSound(context: Context?, song: Int) {
        ref = MediaPlayer.create(context, song)
        if (ref != null) {
            ref!!.start()
        }
    }

    fun stop() {
        if (ref != null) {
            ref!!.stop()
            ref = null
        }
    }

}