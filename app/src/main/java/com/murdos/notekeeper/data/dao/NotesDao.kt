package com.murdos.notekeeper.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM Notes")
    fun getAll(): Flow<List<Note>>

    @Delete
    suspend fun delete(note: Note)
}