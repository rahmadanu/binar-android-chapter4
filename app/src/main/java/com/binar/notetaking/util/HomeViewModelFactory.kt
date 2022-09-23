package com.binar.notetaking.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binar.notetaking.data.local.note.NoteDao
import com.binar.notetaking.presentation.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class HomeViewModelFactory(
    private val noteDao: NoteDao,
    private val application: Application
): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(noteDao, application) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}