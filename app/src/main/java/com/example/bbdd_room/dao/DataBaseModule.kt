package com.example.bbdd_room.dao

import android.content.Context
import androidx.room.Room
import com.example.bbdd_room.bbdd.UsersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Módulo: provee información
// 1. Provee la base de datos ÚNICA que hay en la app
// 2. Provee el dao
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    // Punto 1 --> Getter de la base de datos
    @Provides
    @Singleton
    // ApplicationContext --> coge el contexto de TODA la aplicación desde MyApp
    fun provideDatabase(@ApplicationContext context: Context): UsersDataBase {
        return Room.databaseBuilder(context, UsersDataBase::class.java, "my_database").build()
    }

    // Punto 2 --> Getter del dao (interfaz)
    @Provides
    @Singleton
    fun provideUserDao(db: UsersDataBase): UserDao {
        return db.dao
    }
}