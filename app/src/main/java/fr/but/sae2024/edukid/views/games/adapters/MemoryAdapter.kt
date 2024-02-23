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
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.MemoryCard
import fr.but.sae2024.edukid.models.entities.app.Theme
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.views.games.adapters.holders.MemoryViewHolder
import timber.log.Timber


class MemoryAdapter(private val context: Context, private val listCards: List<Card>, private val theme : Theme) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseTV: TextView
    private lateinit var courseIV: ImageView

    override fun getCount(): Int {
        return listCards.size
    }

    override fun getItem(position: Int): Any {
        return listCards.get(position)
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            Timber.tag("MemoryAdapter").e("convertView is null")
            convertView = layoutInflater!!.inflate(R.layout.item_card, null)
        }

        courseIV = convertView!!.findViewById(R.id.idIVCourse)
        courseTV = convertView!!.findViewById(R.id.interieurCardLettre)

        Glide.with(context).load(listCards.get(position).image).into(courseIV)

        courseTV.setText(listCards.get(position).value)

        return convertView
    }


}