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
import timber.log.Timber

class UserViewModel : ViewModel() {

    val db = EdukidDatabase.getInstance()

    private val _listUserLiveData : MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    private val _selectedUserLiveData : MutableLiveData<User?> = MutableLiveData<User?>()
    val listUserLiveData : MutableLiveData<List<User>> = _listUserLiveData
    val selectedUserLiveData : MutableLiveData<User?> = _selectedUserLiveData

    private val userRepo = UserRepository

    fun createUserChild(username: String, picture: String){
        val user = User(username , "", "", picture, 0)
        insertUser(user)
    }

    fun updateUserChild(id : Int, username : String, picture : String, context : Context){
        val user = User(username , "", "", picture, 0)
        Timber.tag("UserViewModel").e("User : ${user}")
        user.id = id
        updateUser(user, context)
    }


    fun getListUser() {
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

    fun updateUser(user : User, context: Context){
        viewModelScope.launch {
            userRepo.editUser(user).collect{
                if(it){
                    RouteManager.startActivity(context, ActivityName.UserSelectionActivity, true, true)
                }
            }
        }
    }

    fun getSelectedUser() {
        viewModelScope.launch {
            userRepo.getSelectedUser().collect {
                _selectedUserLiveData.postValue(it)
            }
        }
    }
}