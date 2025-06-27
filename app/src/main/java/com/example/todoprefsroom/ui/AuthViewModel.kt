package com.example.todoprefsroom.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoprefsroom.data.TaskRepository
import com.example.todoprefsroom.data.local.AppDatabase
import com.example.todoprefsroom.data.local.TaskEntity
import com.example.todoprefsroom.data.remote.AuthRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var error  by mutableStateOf<String?>(null)
        private set

    fun login(email: String, pass: String, onSuccess: () -> Unit) = viewModelScope.launch {
        loading = true; error = null
        AuthRepo.login(email, pass)
            .onSuccess { onSuccess() }
            .onFailure { error = it.message }
        loading = false
    }

    fun register(email: String, pass: String, onSuccess: () -> Unit) = viewModelScope.launch {
        loading = true; error = null
        AuthRepo.register(email, pass)
            .onSuccess { onSuccess() }
            .onFailure { error = it.message }
        loading = false
    }
}