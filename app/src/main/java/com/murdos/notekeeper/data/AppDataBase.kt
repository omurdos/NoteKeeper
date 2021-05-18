package com.murdos.notekeeper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murdos.notekeeper.data.dao.NotesDao
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.CoroutineScope

@Database(version = 1, entities = [Note::class], exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "Notes"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}