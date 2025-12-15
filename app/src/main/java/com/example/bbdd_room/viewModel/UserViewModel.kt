package com.example.bbdd_room.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bbdd_room.bbdd.User
import com.example.bbdd_room.dao.UserDao
import jakarta.inject.Inject
import kotlinx.coroutines.launch

// Inject --> El viewModel ya no depende del modelo o del dao, sino que
// nos lo pasan de forma automática.
class UserViewModel(@Inject private val userDao: UserDao) : ViewModel() {

    fun deleteUser(user: User) {
        // viewModelScope.launch --> si heredas de ViewModel, se puede poner
        // Para manejo de funciones asíncronas (como eliminar un usario)
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }
    fun udpateUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            userDao.updateUser(firstName, lastName)
        }
    }

}