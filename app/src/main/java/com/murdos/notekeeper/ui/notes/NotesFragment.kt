package com.murdos.notekeeper.ui.notes

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murdos.notekeeper.NotesKeeperApplication
import com.murdos.notekeeper.R
import com.murdos.notekeeper.data.AppDataBase
import com.murdos.notekeeper.data.dao.NotesDao
import com.murdos.notekeeper.databinding.FragmentNotesBinding
import com.murdos.notekeeper.models.Note
import com.murdos.notekeeper.ui.notes.adapters.NotesListAdapter
import kotlinx.coroutines.*


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NotesViewModel
    private lateinit var noteListAdapter: NotesListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)
        recyclerView = binding.notesRecycler
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel = ViewModelProvider(
            viewModelStore,
            NotesViewModel.NotesViewModelFactory((requireActivity().application as NotesKeeperApplication).repository)
        ).get(NotesViewModel::class.java)

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            noteListAdapter = NotesListAdapter(it)
            recyclerView.adapter = noteListAdapter
        })
        binding.addNewNoteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_newNoteFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

}