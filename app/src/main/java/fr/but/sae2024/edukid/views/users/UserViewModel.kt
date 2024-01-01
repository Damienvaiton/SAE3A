package fr.but.sae2024.edukid.views.users

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val db = EdukidDatabase.getInstance()
    val userDao: UserDao = db.userDao()



    private val _listUserLiveData : MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val listUserLiveData : MutableLiveData<List<User>> = _listUserLiveData

    private val userRepo = UserRepository

    fun createUserChild(username : String, picture : String, context : Context){
        val user = User(username , "", "", picture, 0)
        insertUser(user)
    }


    fun getListUser(context : Context){
        viewModelScope.launch {
            userRepo.getAllUsers()
                .collect {

                    _listUserLiveData.postValue(it)
                }
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepo.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepo.deleteUser(user)
        }
    }





}