package com.murdos.notekeeper.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.murdos.notekeeper.data.repository.NotesRepository
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.launch
import java.lang.Exception

class NewNoteViewModel(private val repository: NotesRepository) : ViewModel() {

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    class NewNoteViewModelFactory(private val repository: NotesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewNoteViewModel::class.java)) {
                return NewNoteViewModel(repository) as T
            }
            throw Exception()
        }

    }

}