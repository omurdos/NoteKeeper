package com.murdos.notekeeper.ui.notes

import androidx.lifecycle.*
import com.murdos.notekeeper.data.repository.NotesRepository
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.launch


class NotesViewModel(private val repository: NotesRepository) : ViewModel() {


    var notes: LiveData<List<Note>> = repository.notes.asLiveData()

    class NotesViewModelFactory(private val repository: NotesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
                return NotesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}