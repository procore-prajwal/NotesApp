package com.onboarding.notesapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.onboarding.notesapp.R
import com.onboarding.notesapp.data.entities.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onLikeClick: (Note) -> Unit,
    private val onDeleteClick: (Note) -> Unit,
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var notes = listOf<Note>()

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(noteList: List<Note>) {
        notes = noteList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardNote)
        private val titleText: TextView = itemView.findViewById(R.id.tvTitle)
        private val contentText: TextView = itemView.findViewById(R.id.tvContent)
        private val dateText: TextView = itemView.findViewById(R.id.tvDate)
        private val likeButton: ImageView = itemView.findViewById(R.id.ivLike)
        private val deleteButton: ImageView = itemView.findViewById(R.id.ivDelete)

        fun bind(note: Note) {
            titleText.text = note.title
            contentText.text = note.content
            dateText.text = formatDate(note.createdAt)

            // Set like icon based on status
            likeButton.setImageResource(
                if (note.isLiked) R.drawable.ic_favorite_filled
                else R.drawable.ic_favorite_border
            )

            likeButton.setColorFilter(
                ContextCompat.getColor(
                    itemView.context,
                    if (note.isLiked) R.color.red else R.color.gray
                )
            )

            // Click listeners
            cardView.setOnClickListener { onNoteClick(note) }
            likeButton.setOnClickListener { onLikeClick(note) }
            deleteButton.setOnClickListener { onDeleteClick(note) }
        }

        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }
}