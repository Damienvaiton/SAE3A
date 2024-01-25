package fr.but.sae2024.edukid.views.games.adapters.holders

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R


class MemoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var hidden = false
    var viewCard: View? = null
    var interieurCardChiffre: View? = null
    var interieurCardLettre: TextView? = null
    var title: TextView? = null
    var labelDifficulty: TextView? = null
    var elements = ArrayList<ImageView>()
    var progressBar: ProgressBar? = null
    var pattern: ImageView? = null
    var returnCard: ImageView? = null
    var background: ImageView? = null
    var sato0 = ScaleAnimation(
        1f, 0f, 1f, 1f,
        Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
        0.5f
    )
    var sato1 = ScaleAnimation(
        0f, 1f, 1f, 1f,
        Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
        0.5f
    )

    init {
        viewCard = itemView.findViewById(R.id.viewCard)
        title = itemView.findViewById(R.id.titleMemory)
        labelDifficulty = itemView.findViewById(R.id.labelDifficulty)
        progressBar = itemView.findViewById(R.id.progressBarMemoryLock)
        interieurCardChiffre = itemView.findViewById(R.id.interieurCardChiffre)
        interieurCardLettre = itemView.findViewById(R.id.interieurCardLettre)
        elements.add(itemView.findViewById(R.id.elementMemory1))
        elements.add(itemView.findViewById(R.id.elementMemory2))
        elements.add(itemView.findViewById(R.id.elementMemory3))
        elements.add(itemView.findViewById(R.id.elementMemory4))
        elements.add(itemView.findViewById(R.id.elementMemory5))
        elements.add(itemView.findViewById(R.id.elementMemory6))
        elements.add(itemView.findViewById(R.id.elementMemory7))
        elements.add(itemView.findViewById(R.id.elementMemory8))
        elements.add(itemView.findViewById(R.id.elementMemory9))
        pattern = itemView.findViewById(R.id.patternImgMemory)
        background = itemView.findViewById(R.id.backgroundImgMemory)
        returnCard = itemView.findViewById(R.id.returnImgMemory)
        sato0.duration = 250
        sato1.duration = 250
        sato0.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                if (hidden) {
                    viewCard?.animation = null
                    showImageReturnCard()
                    returnCard?.startAnimation(sato1)
                } else {
                    returnCard?.animation = null
                    showImageViewCard()
                    viewCard?.startAnimation(sato1)
                }
            }
        })
    }

    fun getPresentationHeight(): Double {
        return title?.height?.toDouble() ?: (0.0 + progressBar?.height?.toDouble()!!)
    }

    fun showImageReturnCard() {
        returnCard!!.visibility = View.VISIBLE
        viewCard!!.visibility = View.INVISIBLE
    }

    fun showImageViewCard() {
        returnCard!!.visibility = View.INVISIBLE
        viewCard!!.visibility = View.VISIBLE
    }
}