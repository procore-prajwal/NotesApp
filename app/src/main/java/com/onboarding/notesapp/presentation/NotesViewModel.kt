package com.onboarding.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.onboarding.notesapp.data.NotesDatabase
import com.onboarding.notesapp.data.entities.Note
import com.onboarding.notesapp.data.repository.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    val notesFlow = NotesDatabase.getDatabase(application)
        .noteDao()
        .getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        val noteDao = NotesDatabase.getDatabase(application).noteDao()
        repository = NotesRepository(noteDao)
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun toggleLike(noteId: Long, currentLikeStatus: Boolean) = viewModelScope.launch {
        repository.updateLikeStatus(noteId, !currentLikeStatus)
    }

    suspend fun getNoteById(id: Long): Note? = repository.getNoteById(id)
}