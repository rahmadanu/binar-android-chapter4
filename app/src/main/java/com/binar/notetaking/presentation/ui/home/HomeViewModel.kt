package com.binar.notetaking.presentation.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.binar.notetaking.data.local.note.NoteDao
import com.binar.notetaking.data.local.note.NoteEntity
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataSource: NoteDao,
    application: Application
): AndroidViewModel(application){

    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            dataSource.insert(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            dataSource.update(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            dataSource.delete(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        val notes = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            notes.value = dataSource.getAllNotes()
        }
        return notes
    }
}