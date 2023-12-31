package fr.but.sae2024.edukid.utils.managers

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import timber.log.Timber
import java.util.Locale


object TextToSpeechManager {
    private var voice: Voice? = null
    private var textToSpeech: TextToSpeech? = null

    /* A décommenter a la création de la classe PlayWithSound

    fun speachText(context: Context, text: String) {
        var text = text
        if (text.contains("Y") || text.contains("y") && context.javaClass == PlayWithSound::class.java) text =
            "Trouve la lettre igrec"
        val finalText = text
        textToSpeech = TextToSpeech(context) { status: Int ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.setLanguage(Locale.FRANCE)
                if (voice != null) textToSpeech!!.setVoice(voice)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) textToSpeech!!.speak(
                    finalText,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                ) else textToSpeech!!.speak(finalText, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }*/

    fun initialiser(context: Context?) {
        textToSpeech = TextToSpeech(context) { status: Int ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.setLanguage(Locale.FRANCE)
                for (tmpVoice in textToSpeech!!.voices) {
                    if (tmpVoice.name == "fr-fr-x-fra-network") {
                        voice = tmpVoice
                        textToSpeech!!.setVoice(tmpVoice)
                    }
                }
            } else {
                textToSpeech = null
                Timber.e("Error in initialiser %s", status)
            }
        }
    }

    fun stop() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
            textToSpeech = null
        }
    }

}