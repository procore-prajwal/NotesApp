package com.onboarding.notesapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.onboarding.notesapp.R
import com.onboarding.notesapp.presentation.NotesViewModel
import com.onboarding.notesapp.ui.util.setScreenTitle
import com.onboarding.notesapp.ui.util.setStatusBarColorCompat
import kotlinx.coroutines.launch

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NotesViewModel
    private var noteId: Long = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        setStatusBarColorCompat(color = R.color.purple_700)
        setScreenTitle(getString(R.string.screen_title_create_note))

        noteViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        // Check if editing existing note
        noteId = intent.getLongExtra("NOTE_ID", -1)
        isEditMode = noteId != -1L

        setupToolbar()
        if (isEditMode) {
            loadNoteForEditing()
        }

        setupSaveButton()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            title = if (isEditMode) "Edit Note" else "Create Note"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun loadNoteForEditing() {
        lifecycleScope.launch {
            val note = noteViewModel.getNoteById(noteId)
            note?.let {
                findViewById<EditText>(R.id.etTitle).setText(it.title)
                findViewById<EditText>(R.id.etContent).setText(it.content)
            }
        }
    }

    private fun setupSaveButton() {
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = findViewById<EditText>(R.id.etTitle).text.toString().trim()
        val content = findViewById<EditText>(R.id.etContent).text.toString().trim()

        if (title.isEmpty()) {
            findViewById<EditText>(R.id.etTitle).error = "Title is required"
            return
        }

        if (isEditMode) {
            lifecycleScope.launch {
                // TODO
            }
        } else {
            // TODO
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}