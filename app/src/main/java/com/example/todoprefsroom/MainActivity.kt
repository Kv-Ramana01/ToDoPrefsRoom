package com.example.todoprefsroom

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoprefsroom.data.LoginPrefs
import com.example.todoprefsroom.ui.LoginScreen
import com.example.todoprefsroom.ui.TaskListScreen
import com.example.todoprefsroom.ui.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun AppRoot(context: Context = LocalContext.current) {
    var loggedIn by remember { mutableStateOf(LoginPrefs.isLoggedIn) }
    if (loggedIn) {
        val vm: TaskViewModel = viewModel(factory = TaskViewModel.factory(context))
        TaskListScreen(vm)
    } else {
        LoginScreen { loggedIn = true }
    }
}