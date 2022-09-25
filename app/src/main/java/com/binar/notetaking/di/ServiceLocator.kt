package com.binar.notetaking.di

import android.content.Context
import com.binar.notetaking.data.local.AppDatabase
import com.binar.notetaking.data.local.note.NoteDao
import com.binar.notetaking.data.local.note.NoteDataSource
import com.binar.notetaking.data.local.note.NoteDataSourceImpl
import com.binar.notetaking.data.local.user.UserDao
import com.binar.notetaking.data.local.user.UserDataSource
import com.binar.notetaking.data.local.user.UserDataSourceImpl
import com.binar.notetaking.data.repository.LocalRepository
import com.binar.notetaking.data.repository.LocalRepositoryImpl

object ServiceLocator {

    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    fun provideUserDao(context: Context): UserDao {
        return provideAppDatabase(context).userDao
    }

    fun provideUserDataSource(context: Context): UserDataSource {
        return UserDataSourceImpl(provideUserDao(context))
    }

    fun provideNoteDao(context: Context): NoteDao {
        return provideAppDatabase(context).noteDao
    }

    fun provideNoteDataSource(context: Context): NoteDataSource {
        return NoteDataSourceImpl(provideNoteDao(context))
    }

    fun provideServiceLocator(context: Context): LocalRepository {
        return LocalRepositoryImpl(
            provideUserDataSource(context),
            provideNoteDataSource(context)
        )
    }
}