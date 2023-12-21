package fr.but.sae2024.edukid.views.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.but.sae2024.edukid.database.EdukidDatabase
import fr.but.sae2024.edukid.database.dao.UserDao
import fr.but.sae2024.edukid.models.entities.app.User
import fr.but.sae2024.edukid.repositories.UserRepository
import fr.but.sae2024.edukid.utils.enums.UserRole
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel : ViewModel() {

    val db = EdukidDatabase.getInstance()
    val userDao: UserDao = db.userDao()



    private val _listUserLiveData : MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val listUserLiveData : MutableLiveData<List<User>> = _listUserLiveData

    private val userRepo = UserRepository


    fun displayListUser(listUser: List<User>) {

        if (listUser.isEmpty()) {
            Timber.tag("UserSelectionActivity").e("List is empty")
        }
        else {
            for (user in listUser) {
                Timber.tag("UserSelectionActivity").e("User : $user \n")
            }
        }

    }
    fun addManyUser(){
            viewModelScope.launch {

                userDao.insertUser(
                    User(
                        "admin",
                        "admin",
                        "admin@mailtest.com",
                        null,
                        1,
                        UserRole.PARENT,
                        0
                    )
                )
                for (i in 1..3) {
                    userDao.insertUser(
                        User(
                            "child$i",
                            "child$i",
                            "child$i@mailtest.com",
                            null,
                            1,
                            UserRole.CHILD,
                            0
                        )
                    )
                }
            }
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



}