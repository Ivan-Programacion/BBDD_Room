package com.example.bbdd_room

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Punto inicial de entrada para que Hilt y Dagger sepa que ésta es nuestra app
@HiltAndroidApp // Obtener en contexto de forma automática. Hay que añadir android.name en el AndroidManifest
class MyApp : Application() {
}