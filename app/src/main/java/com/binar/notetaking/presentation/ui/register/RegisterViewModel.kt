package com.binar.notetaking.presentation.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.binar.notetaking.data.local.user.UserDao
import com.binar.notetaking.data.local.user.UserEntity
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val dataSource: UserDao,
    application: Application
) : AndroidViewModel(application) {

    fun registerUser(user: UserEntity) {
        viewModelScope.launch {
            registerUserFromDao(user)
        }
    }

    private suspend fun registerUserFromDao(user: UserEntity) {
        dataSource.registerUser(user)
    }
}