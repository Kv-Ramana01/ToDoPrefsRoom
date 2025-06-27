package com.example.todoprefsroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoprefsroom.data.LoginPrefs
import com.example.todoprefsroom.ui.LoginScreen
import com.example.todoprefsroom.ui.theme.ToDoPrefsRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LoginPrefs.init(applicationContext)

        setContent {
            ToDoPrefsRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MaterialTheme { AppRoot() }
                }
            }
        }
    }
}

@Composable
fun AppRoot() {
    var loggedIn by remember { mutableStateOf(LoginPrefs.isLoggedIn) }
    if (loggedIn) {
        Text("Logged-in home - coming soon")
    } else {
        LoginScreen { loggedIn = true }
    }
}