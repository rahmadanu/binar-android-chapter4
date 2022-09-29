package com.binar.notetaking.di

import android.content.Context
import com.binar.notetaking.data.local.database.AppDatabase
import com.binar.notetaking.data.local.database.note.NoteDao
import com.binar.notetaking.data.local.database.note.NoteDataSource
import com.binar.notetaking.data.local.database.note.NoteDataSourceImpl
import com.binar.notetaking.data.local.database.user.UserDao
import com.binar.notetaking.data.local.database.user.UserDataSource
import com.binar.notetaking.data.local.database.user.UserDataSourceImpl
import com.binar.notetaking.data.local.preference.UserPreference
import com.binar.notetaking.data.local.preference.UserPreferenceDataSource
import com.binar.notetaking.data.local.preference.UserPreferenceDataSourceImpl
import com.binar.notetaking.data.repository.LocalRepository
import com.binar.notetaking.data.repository.LocalRepositoryImpl

object ServiceLocator {

    fun provideUserPreference(context: Context): UserPreference {
        return UserPreference(context)
    }

    fun provideUserPreferenceDataSource(context: Context): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(provideUserPreference(context))
    }

    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    fun provideUserDao(context: Context): UserDao {
        return provideAppDatabase(context).userDao()
    }

    fun provideUserDataSource(context: Context): UserDataSource {
        return UserDataSourceImpl(provideUserDao(context))
    }

    fun provideNoteDao(context: Context): NoteDao {
        return provideAppDatabase(context).noteDao()
    }

    fun provideNoteDataSource(context: Context): NoteDataSource {
        return NoteDataSourceImpl(provideNoteDao(context))
    }

    fun provideServiceLocator(context: Context): LocalRepository {
        return LocalRepositoryImpl(
            provideUserPreferenceDataSource(context),
            provideUserDataSource(context),
            provideNoteDataSource(context)
        )
    }
}