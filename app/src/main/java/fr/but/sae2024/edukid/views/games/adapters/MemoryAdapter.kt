package fr.but.sae2024.edukid.views.games.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.MemoryCard
import fr.but.sae2024.edukid.views.games.adapters.holders.MemoryViewHolder
import timber.log.Timber


class MemoryAdapter(private val context: Context, private val listMemoryCard: List<MemoryCard>, private val numColumns: Int, private val themeName : String) : BaseAdapter() {

    private var position: ArrayList<Int>? = null
    private var holder: MemoryViewHolder? = null

    override fun getCount(): Int {
        return listMemoryCard.size
    }

    override fun getItem(i: Int): Any {
        return listMemoryCard[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
            holder = MemoryViewHolder(convertView)
            convertView.tag = holder
        } else {
            holder = convertView.tag as MemoryViewHolder
        }

        holder!!.returnCard?.setImageResource(R.drawable.imgreturn)
        holder!!.pattern?.setImageResource(R.drawable.patternimg)
        holder!!.background?.setImageResource(R.drawable.backgroundmemory)

        if (themeName == "Chiffres") {
            if (!listMemoryCard[i].value.equals("1") && !isChiffre(listMemoryCard[i].getDrawableImage())) {
                for (k in 0 until Integer.parseInt(listMemoryCard[i].value.toString())) {
                    holder!!.elements[k].setImageResource(listMemoryCard[i].getDrawableImage())
                }
            } else {
                holder!!.elements[0].setImageResource(listMemoryCard[i].getDrawableImage())
            }
        } else {
            holder!!.interieurCardLettre?.text = listMemoryCard[i].value
            holder!!.interieurCardLettre?.typeface = ResourcesCompat.getFont(context, listMemoryCard[i].getFont())
        }

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        val ratio = (display.height - 377.8) / display.width
        Timber.e("memoryAdapter", "RATIO : $ratio")
        val width = display.width / numColumns
        val height = width * (1684.0 / 1094) + 20
        val sizeElement = width - 100
        var layoutParams = RelativeLayout.LayoutParams(width.toInt(), height.toInt())
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        holder!!.viewCard?.layoutParams = layoutParams

        holder!!.returnCard?.layoutParams = layoutParams

        layoutParams = if (themeName == "Chiffres") {
            RelativeLayout.LayoutParams(sizeElement, sizeElement)
        } else {
            RelativeLayout.LayoutParams(sizeElement, height.toInt())
        }

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        holder!!.interieurCardChiffre?.layoutParams = layoutParams
        holder!!.interieurCardLettre?.textSize = (sizeElement / 3.5).toFloat()
        holder!!.interieurCardLettre?.layoutParams = layoutParams

        if (themeName == "Chiffres" && !listMemoryCard[i].value.equals("1") && !isChiffre(listMemoryCard[i].getDrawableImage())) {
            when (listMemoryCard[i].value) {
                "2" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement.toInt() / 2, sizeElement)
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams
                }
                "3" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins((sizeElement / 4), 0, 0, 0)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins(0, (sizeElement / 2), 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement / 2), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams
                }
                "4" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins(0, (sizeElement / 2), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 2)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement / 2), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams
                }
                "5" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 4), (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins(0, (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[4].layoutParams = layoutParams
                }
                "6" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins(0, (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins(0, (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[4].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 3)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[5].layoutParams = layoutParams
                }
                "7" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins((sizeElement / 4), (sizeElement * 0.165).toInt(), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins(0, (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[4].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins(0, (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[5].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 3.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[6].layoutParams = layoutParams
                }
                "8" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement.toInt() / 4)
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins((sizeElement / 4), (sizeElement * 0.165).toInt(), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins(0, (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.33).toInt(), 0, 0)
                    holder!!.elements[4].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins((sizeElement / 4), (sizeElement * 0.495).toInt(), 0, 0)
                    holder!!.elements[5].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins(0, (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[6].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, sizeElement / 4)
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.66).toInt(), 0, 0)
                    holder!!.elements[7].layoutParams = layoutParams
                }
                "9" -> {
                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    holder!!.elements[0].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), 0, 0, 0)
                    holder!!.elements[1].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins(0, (sizeElement * 0.25).toInt(), 0, 0)
                    holder!!.elements[2].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.25).toInt(), 0, 0)
                    holder!!.elements[3].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins((sizeElement / 4), (sizeElement * 0.375).toInt(), 0, 0)
                    holder!!.elements[4].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins(0, (sizeElement * 0.5).toInt(), 0, 0)
                    holder!!.elements[5].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.5).toInt(), 0, 0)
                    holder!!.elements[6].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins(0, (sizeElement * 0.75).toInt(), 0, 0)
                    holder!!.elements[7].layoutParams = layoutParams

                    layoutParams = RelativeLayout.LayoutParams(sizeElement / 2, (sizeElement / 4.5).toInt())
                    layoutParams.setMargins((sizeElement / 2), (sizeElement * 0.75).toInt(), 0, 0)
                    holder!!.elements[8].layoutParams = layoutParams
                }
            }
        } else {
            layoutParams = RelativeLayout.LayoutParams(sizeElement, sizeElement)
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            holder!!.elements[0].layoutParams = layoutParams
        }

        holder!!.hidden = listMemoryCard[i].isHidden()

        if (position == null || position!!.isEmpty()) {
            if (listMemoryCard[i].isHidden()) {
                holder!!.showImageReturnCard()
            } else {
                holder!!.showImageViewCard()
            }
        } else {
            for (j in position!!.indices) {
                if (position!![j] == i) {
                    if (listMemoryCard[i].isHidden()) {
                        holder!!.viewCard?.startAnimation(holder!!.sato0)
                    } else {
                        holder!!.returnCard?.startAnimation(holder!!.sato0)
                    }
                }
            }
        }
        return convertView!!
    }

    fun setCard(position: ArrayList<Int>?) {
        this.position = position
    }

    private fun isChiffre(drawableImage: Int): Boolean {
        return !(drawableImage != R.drawable.number_one && drawableImage != R.drawable.number_two && drawableImage != R.drawable.number_three && drawableImage != R.drawable.number_four && drawableImage != R.drawable.number_five && drawableImage != R.drawable.number_six && drawableImage != R.drawable.number_seven && drawableImage != R.drawable.number_eight && drawableImage != R.drawable.number_nine)
    }
}