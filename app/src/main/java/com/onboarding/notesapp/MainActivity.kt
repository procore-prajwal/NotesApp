package com.onboarding.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onboarding.notesapp.presentation.NotesViewModel
import com.onboarding.notesapp.ui.CreateNoteActivity
import com.onboarding.notesapp.ui.NotesAdapter
import com.onboarding.notesapp.ui.util.setStatusBarColorCompat
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setStatusBarColorCompat(color = R.color.purple_700)
        setupRecyclerView()
        setupViewModel()
        setupFab()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNotes)
        notesAdapter = NotesAdapter(
            onNoteClick = { note ->
                // TODO
            },
            onLikeClick = { note ->
                // TODO
            },
            onDeleteClick = { note ->
                // TODO
            }
        )

        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModel() {
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                notesViewModel.notesFlow.collect { notes ->
                    notesAdapter.setNotes(notes)
                }
            }
        }
    }

    private fun setupFab() {
        val fab = findViewById<FloatingActionButton>(R.id.fabAddNote)
        fab.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
        }
    }
}