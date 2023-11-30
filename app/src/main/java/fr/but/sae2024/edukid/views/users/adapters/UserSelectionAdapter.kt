package fr.but.sae2024.edukid.views.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.app.User

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


    override fun onBindViewHolder(holder: UserSelectionViewHolder, position: Int) {
        val user = listUser[position]
        holder.username.text = user.username
        holder.profilPicture.setImageResource()
        holder.paramPicture.setImageResource(R.drawable.)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

}