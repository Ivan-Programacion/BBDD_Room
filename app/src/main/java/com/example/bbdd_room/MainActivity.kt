package com.example.bbdd_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.bbdd_room.bbdd.User
import com.example.bbdd_room.ui.theme.BBDD_RoomTheme
import com.example.bbdd_room.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // hay que ponerlo para que funcione la cofig de BBDD
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BBDD_RoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
// hiltViewModel() --> en vez de buscar el viewModel tu mismo, el propio hiltViewModel lo busca
fun UserScreen(paddingValues: PaddingValues, myViewModel: UserViewModel = hiltViewModel()) {
    // Recoge el flujo de la función indicada (en este caso todos los nombres de usuarios)
    val userList = myViewModel.getAllUsersByName().collectAsState(emptyList())
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button({
            myViewModel.createUser(
                User(
                    0,
                    "Ivan",
                    "Vázquez",
                    "666666666",
                    31
                )
            )
        }) { Text("Create new user") }
        LazyColumn {
            items(userList.value.size) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(userList.value[it].firstName)
                    Text(userList.value[it].lastName)
                    Text(userList.value[it].phoneNumber)
                }
            }
        }
    }
}