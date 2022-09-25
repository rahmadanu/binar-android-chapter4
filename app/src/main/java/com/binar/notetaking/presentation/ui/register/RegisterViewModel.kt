package com.binar.notetaking.presentation.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.notetaking.data.local.user.UserDao
import com.binar.notetaking.data.local.user.UserEntity
import com.binar.notetaking.data.repository.LocalRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: LocalRepository) : ViewModel() {

    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            repository.registerUser(user)
        }
    }
}