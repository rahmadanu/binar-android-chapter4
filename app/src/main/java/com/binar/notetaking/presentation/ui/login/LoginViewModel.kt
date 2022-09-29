package com.binar.notetaking.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.notetaking.data.local.database.user.UserEntity
import com.binar.notetaking.data.repository.LocalRepository
import com.binar.notetaking.wrapper.Resource
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LocalRepository) : ViewModel(){

    private var _getUserResult = MutableLiveData<Resource<UserEntity>>()
    val getUserResult: LiveData<Resource<UserEntity>> get() = _getUserResult

    fun getUser(username: String) {
        viewModelScope.launch {
            _getUserResult.postValue(repository.getUser(username))
        }
    }

    fun checkIfUserLoggedIn(): Boolean {
        return repository.checkIfUserLoggedIn()
    }

    fun setIfUserLogin(userLoggedIn: Boolean){
        return repository.setIfUserLogin(userLoggedIn)
    }
}