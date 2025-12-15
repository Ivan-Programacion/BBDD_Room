package com.example.bbdd_room.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bbdd_room.bbdd.User
import com.example.bbdd_room.dao.UserDao

// Para crear la base de datos
@Database(
    entities = [User::class], // Array de entidades
    version = 1
)
abstract class UsersDataBase: RoomDatabase() {

    // Accedemos a los métodos de la interfaz mediante "dao"
    abstract val dao: UserDao
}