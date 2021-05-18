package com.murdos.notekeeper.ui.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murdos.notekeeper.R
import com.murdos.notekeeper.models.Note
import com.murdos.notekeeper.ui.notes.adapters.NotesListAdapter.ViewHolder


class NotesListAdapter(val notes: List<Note>) : RecyclerView.Adapter<ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var noteTitle: TextView = view.findViewById(R.id.noteTitle)
        var noteShort: TextView = view.findViewById(R.id.noteShort)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = notes[position].title
        holder.noteShort.text = notes[position].note
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}