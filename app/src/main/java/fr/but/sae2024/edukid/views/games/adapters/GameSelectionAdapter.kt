package fr.but.sae2024.edukid.views.games.adapters

import android.annotation.SuppressLint
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
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.models.entities.app.Theme
import timber.log.Timber


class GameSelectionAdapter(val listGame : List<Game?>) : RecyclerView.Adapter<GameSelectionAdapter.GameSelectionViewHolder>() {

    private val _gameLD : MutableLiveData<Game> = MutableLiveData()
    val gameLD : LiveData<Game> = _gameLD

    inner class GameSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val gameName = itemview.findViewById<TextView>(R.id.tv_title)
        val gameImage = itemview.findViewById<ImageView>(R.id.Iv_preview)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSelectionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.theme_card_list_item, parent, false)
        return GameSelectionViewHolder(contactView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: GameSelectionAdapter.GameSelectionViewHolder, position: Int) {

        val game = listGame[position]

        if (listGame == null) {
            return
        }

        holder.gameName.text = game?.name
        Glide
            .with(holder.itemView.context)
            .load(game?.image)
            .into(holder.gameImage)

        Timber.tag("GameSelectionAdapter").e("Game : ${game?.name}")

        holder.itemView.setOnClickListener {
            Timber.tag("GameSelectionAdapter").e("Game : ${game?.name}")
            _gameLD.postValue(game!!)
        }
    }

    override fun getItemCount(): Int {
        return listGame.size
    }
}