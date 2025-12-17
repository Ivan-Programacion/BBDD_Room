package com.example.bbdd_room.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bbdd_room.bbdd.User
import com.example.bbdd_room.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Inject --> El viewModel ya no depende del modelo o del dao, sino que
// nos lo pasan de forma automática.
@HiltViewModel
class UserViewModel@Inject constructor (private val userDao: UserDao) : ViewModel() {

    fun deleteUser(user: User) {
        // viewModelScope.launch --> si heredas de ViewModel, se puede poner
        // Para manejo de funciones asíncronas (como eliminar un usario)
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }
    fun updateUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            userDao.updateUser(firstName, lastName)
        }
    }
    fun createUser(user: User) {
        viewModelScope.launch {
            userDao.upsertUser(user)
        }
    }
    fun getAllUsersByName(): Flow<List<User>> {
        return userDao.getUserOrderedByFirstName()
    }


}