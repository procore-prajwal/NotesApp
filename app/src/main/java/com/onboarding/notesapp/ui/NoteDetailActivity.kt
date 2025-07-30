package com.onboarding.notesapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.onboarding.notesapp.R
import com.onboarding.notesapp.data.entities.Note
import com.onboarding.notesapp.presentation.NotesViewModel
import com.onboarding.notesapp.ui.util.setScreenTitle
import com.onboarding.notesapp.ui.util.setStatusBarColorCompat
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NotesViewModel
    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setStatusBarColorCompat(color = R.color.purple_700)
        setScreenTitle(getString(R.string.screenn_title_note_detail))

        noteViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val noteId = intent.getLongExtra("NOTE_ID", -1)
        if (noteId != -1L) {
            loadNoteDetails(noteId)
        }

        setupToolbar()
        setupEditButton()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun loadNoteDetails(noteId: Long) {
        lifecycleScope.launch {
            currentNote = noteViewModel.getNoteById(noteId)
            currentNote?.let { note ->
                findViewById<TextView>(R.id.tvDetailTitle).text = note.title
                findViewById<TextView>(R.id.tvDetailContent).text = note.content
                findViewById<TextView>(R.id.tvDetailDate).text = formatDate(note.createdAt)

                val likeButton = findViewById<ImageView>(R.id.ivDetailLike)
                updateLikeButton(likeButton, note.isLiked)

                likeButton.setOnClickListener {
                    noteViewModel.toggleLike(note.id, note.isLiked)
                    updateLikeButton(likeButton, !note.isLiked)
                    currentNote = note.copy(isLiked = !note.isLiked)
                }
            }
        }
    }

    private fun updateLikeButton(likeButton: ImageView, isLiked: Boolean) {
        likeButton.setImageResource(
            if (isLiked) R.drawable.ic_favorite_filled
            else R.drawable.ic_favorite_border
        )
        likeButton.setColorFilter(
            ContextCompat.getColor(
                this,
                if (isLiked) R.color.red else R.color.gray
            )
        )
    }

    private fun setupEditButton() {
        // TODO
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("MMMM dd, yyyy 'at' HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
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

    override fun onResume() {
        super.onResume()
        // Reload note details when returning from edit
        val noteId = intent.getLongExtra("NOTE_ID", -1)
        if (noteId != -1L) {
            loadNoteDetails(noteId)
        }
    }
}