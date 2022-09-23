package com.binar.notetaking.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binar.notetaking.data.local.user.UserDao
import com.binar.notetaking.presentation.ui.home.HomeViewModel
import com.binar.notetaking.presentation.ui.login.LoginViewModel
import com.binar.notetaking.presentation.ui.register.RegisterViewModel
import java.lang.IllegalArgumentException

class LoginRegisterViewModelFactory(
    private val dataSource: UserDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(dataSource, application) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(dataSource, application) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}