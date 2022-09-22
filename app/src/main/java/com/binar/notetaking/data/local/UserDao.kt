package com.binar.notetaking.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(user: UserEntity)

    @Query("SELECT * FROM user_information WHERE username = :username")
    suspend fun getUser(username: String) : UserEntity
}