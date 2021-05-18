package com.murdos.notekeeper.data.repository

import androidx.annotation.WorkerThread
import com.murdos.notekeeper.data.dao.NotesDao
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {
    val notes: Flow<List<Note>> = notesDao.getAll()

    @WorkerThread
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
}