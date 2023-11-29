package fr.but.sae2024.edukid.views.users.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.but.sae2024.edukid.models.app.User

class UserSelectionAdapter(val listUser : List<User>) : RecyclerView.Adapter<UserSelectionAdapter.UserSelectionViewHolder>() {

    inner class UserSelectionViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

    }



    //A modifier
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSelectionViewHolder {
        //  TODO("Not yet implemented")
        return UserSelectionViewHolder(View(parent.context))
    }


    override fun onBindViewHolder(holder: UserSelectionViewHolder, position: Int) {
      //  TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

}