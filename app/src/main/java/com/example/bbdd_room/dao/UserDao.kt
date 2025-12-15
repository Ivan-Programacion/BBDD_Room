package com.example.bbdd_room.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bbdd_room.bbdd.User
import kotlinx.coroutines.flow.Flow

// Data Access Object (puente entre la vista y BBDD)
interface UserDao {

    // Hay que hacerlas suspend para que no paren el programa y se ejecuten de forma asíncrona
    // Suspend solo las funciones de ESCRITURA
    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    // Se pueden ahcer querys personalziadas
    @Query("SELECT * FROM users ORDER BY firstName ASC")
    fun getUserOrderedByFirstName(user: User): Flow<List<User>>

    @Query("UPDATE users SET firstName = :firstName, lastName = :lastName")
    suspend fun updateUser(firstName: String, lastName: String)

    @Query("SELECT * FROM users WHERE age > :minimumAge")
    fun getOldestUsers(minimumAge: Int): Flow<List<User>>


}