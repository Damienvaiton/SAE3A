package fr.but.sae2024.edukid.views.games.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.app.Subgame
import timber.log.Timber

class SubGameSelectionAdapter(val listSubGame : List<Subgame?>) : RecyclerView.Adapter<SubGameSelectionAdapter.SubGameSelectionViewHolder>(){

    private val _subGameLD : MutableLiveData<Subgame> = MutableLiveData()
    val subGameLD : LiveData<Subgame> = _subGameLD

    inner class SubGameSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val subGameName = itemview.findViewById<TextView>(R.id.tv_title)
        val subGameImage = itemview.findViewById<ImageView>(R.id.Iv_preview)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubGameSelectionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.theme_card_list_item, parent, false)
        return SubGameSelectionViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: SubGameSelectionViewHolder, position: Int) {
        val subGame = listSubGame[position]

        if (listSubGame == null) {
            return
        }

        holder.subGameName.text = subGame?.name
        Glide
            .with(holder.itemView.context)
            .load(subGame?.image)
            .into(holder.subGameImage)

        Timber.tag("GameSelectionAdapter").e("Game : ${subGame?.name}")

        holder.itemView.setOnClickListener {
            Timber.tag("GameSelectionAdapter").e("Game : ${subGame?.name}")
            _subGameLD.postValue(subGame!!)
        }
    }

    override fun getItemCount(): Int {
        return listSubGame.size
    }
}