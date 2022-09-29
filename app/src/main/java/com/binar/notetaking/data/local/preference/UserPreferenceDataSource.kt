package com.binar.notetaking.data.local.preference

import com.binar.notetaking.data.local.database.user.UserEntity

interface UserPreferenceDataSource {
    fun getIfUserLogin(): Boolean

    fun setIfUserLogin(userLoggedIn: Boolean)
}

class UserPreferenceDataSourceImpl(
    private val userPreference: UserPreference
): UserPreferenceDataSource {
    override fun getIfUserLogin(): Boolean {
        return userPreference.loginKeyValue
    }

    override fun setIfUserLogin(userLoggedIn: Boolean) {
        userPreference.loginKeyValue = userLoggedIn
    }

}