package com.murdos.notekeeper

import android.app.Application
import com.murdos.notekeeper.data.AppDataBase
import com.murdos.notekeeper.data.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesKeeperApplication : Application() {
    val scope = CoroutineScope(SupervisorJob())
    val database by lazy {
        AppDataBase.getDatabase(this, scope)
    }
    val repository by lazy {
        NotesRepository(database.notesDao())
    }
}