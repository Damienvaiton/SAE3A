package fr.but.sae2024.edukid.views.games.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.app.Game
import fr.but.sae2024.edukid.views.users.adapters.UserSelectionAdapter
import timber.log.Timber


class GameSelectionAdapter(val listGame : List<Game>) : RecyclerView.Adapter<GameSelectionAdapter.GameSelectionViewHolder>() {

    inner class GameSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val gameName = itemview.findViewById<TextView>(R.id.gameName)
        val gameImage = itemview.findViewById<ImageView>(R.id.gameImage)
        val gameImageLock = itemview.findViewById<ImageView>(R.id.gameImageLock)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSelectionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_game, parent, false)
        return GameSelectionViewHolder(contactView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: GameSelectionAdapter.GameSelectionViewHolder, position: Int) {
        val resources = holder.itemView.context.resources

        val game = listGame[position]

        holder.gameName.text = game.name
        Glide
            .with(holder.itemView.context)
            .load(game.image)
            .into(holder.gameImage)

        Glide
            .with(holder.itemView.context)
            .load(game.image)
            .into(holder.gameImageLock)

        Timber.tag("GameSelectionAdapter").e("Game : ${game.name}")
    }

    override fun getItemCount(): Int {
        return listGame.size
    }
}