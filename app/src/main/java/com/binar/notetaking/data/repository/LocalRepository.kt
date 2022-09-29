package com.binar.notetaking.data.repository

import com.binar.notetaking.data.local.database.note.NoteDataSource
import com.binar.notetaking.data.local.database.note.NoteEntity
import com.binar.notetaking.data.local.database.user.UserDataSource
import com.binar.notetaking.data.local.database.user.UserEntity
import com.binar.notetaking.data.local.preference.UserPreferenceDataSource
import com.binar.notetaking.wrapper.Resource

interface LocalRepository {
    fun checkIfUserLoggedIn(): Boolean
    fun setIfUserLogin(userLoggedIn: Boolean)

    suspend fun registerUser(user: UserEntity): Resource<Number>
    suspend fun getUser(username: String): Resource<UserEntity>
    //suspend fun checkIsLoginValid(username: String, password: String): Boolean

    suspend fun insertNote(note: NoteEntity): Resource<Number>
    suspend fun updateNote(note: NoteEntity): Resource<Number>
    suspend fun deleteNote(note: NoteEntity): Resource<Number>
    suspend fun getNoteList(): Resource<List<NoteEntity>>
    suspend fun getNoteById(id: Long) : Resource<NoteEntity>
}

class LocalRepositoryImpl(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val userDataSource: UserDataSource,
    private val noteDataSource: NoteDataSource,
): LocalRepository {
    override fun checkIfUserLoggedIn(): Boolean {
        return userPreferenceDataSource.getIfUserLogin()
    }

    override fun setIfUserLogin(userLoggedIn: Boolean){
        return userPreferenceDataSource.setIfUserLogin(userLoggedIn)
    }

    override suspend fun registerUser(user: UserEntity): Resource<Number> {
        return proceed {
            userDataSource.registerUser(user)
        }
    }

    override suspend fun getUser(username: String): Resource<UserEntity> {
        return proceed {
            userDataSource.getUser(username)
        }
    }

/*    override suspend fun checkIsLoginValid(username: String, password: String): Boolean {
        return userDataSource.getUser(username).username.equals(username, false)
                && userDataSource.getUser(username).password.equals(password, false)
    }*/

    override suspend fun insertNote(note: NoteEntity): Resource<Number> {
        return proceed {
            noteDataSource.insertNote(note)
        }
    }

    override suspend fun updateNote(note: NoteEntity): Resource<Number> {
        return proceed {
            noteDataSource.updateNote(note)
        }
    }

    override suspend fun deleteNote(note: NoteEntity): Resource<Number> {
        return proceed {
            noteDataSource.deleteNote(note)
        }
    }

    override suspend fun getNoteList(): Resource<List<NoteEntity>> {
        return proceed {
            noteDataSource.getNoteList()
        }
    }

    override suspend fun getNoteById(id: Long): Resource<NoteEntity> {
        return proceed {
            noteDataSource.getNoteById(id)
        }
    }

    private suspend fun <T> proceed(coroutine: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

}