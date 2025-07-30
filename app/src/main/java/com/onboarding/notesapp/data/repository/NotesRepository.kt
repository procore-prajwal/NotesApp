package com.onboarding.notesapp.data.repository

import com.onboarding.notesapp.data.dao.NotesDao
import com.onboarding.notesapp.data.entities.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {
    fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()

    suspend fun getNoteById(id: Long): Note? = notesDao.getNoteById(id)

    suspend fun insertNote(note: Note): Long = notesDao.insertNote(note)

    suspend fun updateNote(note: Note): Int = notesDao.updateNote(note)

    suspend fun deleteNote(note: Note): Int = notesDao.deleteNote(note)

    suspend fun updateLikeStatus(id: Long, isLiked: Boolean): Int =
        notesDao.updateLikeStatus(id, isLiked)
}