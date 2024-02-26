package fr.but.sae2024.edukid.views.games.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.games.Card

class MemoryAdapter(private val context: Context, private val listCards: List<Card>, private val numberColumn : Int) : BaseAdapter() {

    private inner class ViewHolder(view: View) {
        val backgroundMemory: ConstraintLayout = view.findViewById(R.id.background)
        val imageMemory: ImageView = view.findViewById(R.id.image_memoryCard)
        val valueMemory: TextView = view.findViewById(R.id.text_memoryCard)
    }

    private var layoutInflater: LayoutInflater? = null
    private lateinit var valueMemory: TextView
    private lateinit var imageMemory: ImageView
    private lateinit var backgroundMemory : ConstraintLayout

    override fun getCount(): Int {
        return listCards.size
    }

    override fun getItem(position: Int): Any {
        return listCards[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val card = listCards[position]
        val holder: ViewHolder

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            view = layoutInflater!!.inflate(R.layout.item_card_memory, null)

            // Utilisez une classe interne (inner class) pour ViewHolder
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            // Si la vue est recyclée, récupérer le ViewHolder existant
            holder = view!!.tag as ViewHolder
        }

        // En fonction du numéro de colonne, on adapte la taille des images
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val width = (display.width / numberColumn)

        // Calcule la hauteur pour le contour en fonction de la largeur et d'une proportion (par exemple, 1:2)
        val proportion = 1.6
        val height = (width * proportion).toInt()

        // Ajout de marges entre les cartes
        val horizontalMargin = 40
        val bottomMargin = 32

        // Ajuste la hauteur de backgroundMemory avec les marges
        val backgroundParams =
            ConstraintLayout.LayoutParams(width - 2 * horizontalMargin, height - 2 * bottomMargin)
        backgroundParams.setMargins(horizontalMargin, horizontalMargin, horizontalMargin, bottomMargin)
        holder.backgroundMemory.layoutParams = backgroundParams

        // Ajoute une marge en dessous de la carte
        val cardParams = RelativeLayout.LayoutParams(width, height)
        cardParams.setMargins(0, 0, 0, bottomMargin)
        view!!.layoutParams = cardParams

        // Ajuste la taille du texte en fonction du nombre de colonnes
        val textSizeInSp = 110 - (numberColumn * 10)
        holder.valueMemory.textSize = textSizeInSp.toFloat()

        val imageWidth = width - 2 * horizontalMargin

        if (card.isHidden) {
            holder.imageMemory.visibility = View.INVISIBLE
            holder.valueMemory.visibility = View.INVISIBLE
            holder.backgroundMemory.setBackgroundResource(R.drawable.imgreturn)
        } else {
            holder.imageMemory.visibility = View.VISIBLE
            holder.valueMemory.visibility = View.VISIBLE
            holder.backgroundMemory.setBackgroundResource(R.drawable.patternimg)
        }

        // Si la carte affiche une image, on charge l'image
        if (card.showType!!.lowercase() == "image") {
            Glide.with(context).load(card.image).override(imageWidth, imageWidth).into(holder.imageMemory)
            holder.valueMemory.visibility = View.INVISIBLE

            // Sinon on affiche la valeur de la carte
        } else if (card.showType.lowercase() == "texte") {
            holder.valueMemory.text = card.value
            holder.imageMemory.visibility = View.INVISIBLE
        }

        return view
    }
}