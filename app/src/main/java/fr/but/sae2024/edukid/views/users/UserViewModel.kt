package fr.but.sae2024.edukid.views.users

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.repositories.UserRepository
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val db = EdukidDatabase.getInstance()

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

    fun saveAuthUser(user : User, context: Context){
        viewModelScope.launch {
            userRepo.setAuthenticatedUser(user).collect{
                if(it){
                    RouteManager.startActivity(context, ActivityName.ThemeSelectionActivity, true, true)
                }
            }
        }
    }

    fun saveSelectedUser(user : User, context: Context){
        viewModelScope.launch {
            userRepo.setSelectedUser(user).collect{
                if(it){
                    RouteManager.startActivity(context, ActivityName.UserManagingActivity, true, true)
                }
            }
        }
    }
}