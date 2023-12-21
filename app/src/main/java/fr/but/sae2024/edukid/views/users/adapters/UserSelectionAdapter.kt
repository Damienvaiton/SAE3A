package fr.but.sae2024.edukid.views.users.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.app.User
import timber.log.Timber

class UserSelectionAdapter(val listUser : List<User>) : RecyclerView.Adapter<UserSelectionAdapter.UserSelectionViewHolder>() {

    inner class UserSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val username = itemview.findViewById<TextView>(R.id.userName)
        val profilPicture = itemview.findViewById<ImageView>(R.id.userAvatar)
        val paramPicture = itemview.findViewById<ImageView>(R.id.icon_modif)
    }



    //A modifier
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSelectionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.user_item, parent, false)
        return UserSelectionViewHolder(contactView)
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: UserSelectionViewHolder, position: Int) {
        val resources = holder.itemView.context.resources

        val user = listUser[position]

        holder.username.text = user.username

        Timber.tag("UserSelectionAdapter").e("Username : ${user.username}")

        Timber.tag("UserSelectionAdapter").e("J'entre dans la recherche de l'image")
        val resourceId: Int = resources.getIdentifier("profil1", "drawable", holder.itemView.context.packageName)
        holder.profilPicture.setImageDrawable(resources.getDrawable(resourceId,null))
        Timber.tag("UserSelectionAdapter").e("J'ai fini de chercher l'image")


        holder.paramPicture.setImageResource(R.drawable.settings_icon)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

}