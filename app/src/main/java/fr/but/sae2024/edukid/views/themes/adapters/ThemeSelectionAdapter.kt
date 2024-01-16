package fr.but.sae2024.edukid.views.themes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.app.Theme

class ThemeSelectionAdapter(val listTheme : List<Theme?>): RecyclerView.Adapter<ThemeSelectionAdapter.ThemeSelectionViewHolder>() {

    inner class ThemeSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val themename = itemview.findViewById<TextView>(R.id.tv_title)
        val themePicture = itemview.findViewById<ImageView>(R.id.Iv_preview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeSelectionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.theme_card_list_item, parent, false)
        return ThemeSelectionViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ThemeSelectionViewHolder, position: Int) {
        val theme = listTheme[position]
        holder.themename.text = theme?.name
        Glide
            .with(holder.itemView.context)
            .load(theme?.image)
            .into(holder.themePicture)
    }

    override fun getItemCount(): Int {
        return listTheme.size
    }
}