package com.binar.notetaking.presentation.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.binar.notetaking.data.local.UserDao
import com.binar.notetaking.data.local.UserEntity
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dataSource: UserDao,
    application: Application
) : AndroidViewModel(application){

    private var _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity> get() = _user

    fun getUser(username: String) {
        viewModelScope.launch {
            _user.value = dataSource.getUser(username)
        }
    }
}