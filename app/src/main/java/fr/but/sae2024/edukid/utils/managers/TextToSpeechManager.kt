package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import timber.log.Timber
import java.util.Locale


object TextToSpeechManager {

    private var voice: Voice? = null
    private lateinit var tts: TextToSpeech
    private var isInitialized = false

    fun init(context: Context) {
        tts = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                Timber.d("TextToSpeech initialized")
                tts.language = Locale.FRENCH
                tts.setSpeechRate(1.0f)
                tts.setPitch(0.8f)
                voice = tts.voices.find { voice -> voice.name == "fr-fr-x-afb#male_2-local" }
                isInitialized = true
            } else {
                Timber.e("TextToSpeech initialization failed")
            }
        })
    }

    fun speak(text: String) {
        if (isInitialized) {
            tts.voice = voice
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            Timber.e("TextToSpeech not initialized")
        }
    }

    fun stop() {
        tts.stop()
    }
}