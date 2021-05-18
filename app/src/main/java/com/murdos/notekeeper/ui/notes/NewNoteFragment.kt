package com.murdos.notekeeper.ui.notes

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.murdos.notekeeper.NotesKeeperApplication
import com.murdos.notekeeper.R
import com.murdos.notekeeper.databinding.FragmentNewNoteBinding
import com.murdos.notekeeper.models.Note
import kotlinx.coroutines.*


class NewNoteFragment : Fragment() {
    private lateinit var binding: FragmentNewNoteBinding
    private lateinit var viewModel: NewNoteViewModel
    private lateinit var job: Job
    private lateinit var scope: CoroutineScope
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_note, container, false)
        viewModel = ViewModelProvider(
            viewModelStore,
            NewNoteViewModel.NewNoteViewModelFactory((requireActivity().application as NotesKeeperApplication).repository)
        ).get(NewNoteViewModel::class.java)
        job = Job()
        scope = CoroutineScope(Dispatchers.Main)
        binding.toolbar.inflateMenu(R.menu.new_note_menu)
        binding.toolbar.title = "New note"
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_done -> {
                    val note = Note(
                        title = binding.noteTitle.text.toString(),
                        note = binding.noteText.text.toString(),
                        id = null
                    )
                    Log.d("newNote", "inside the click action")
                   scope.launch {
                       withContext(Dispatchers.IO){
                           viewModel.insert(note)
                       }
                   }

                    true
                }
                else -> false
            }
        }
        return binding.root
    }


}