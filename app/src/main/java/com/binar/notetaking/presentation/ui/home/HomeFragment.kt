package com.binar.notetaking.presentation.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.binar.notetaking.R
import com.binar.notetaking.data.local.note.NoteDatabase
import com.binar.notetaking.databinding.FragmentHomeBinding
import com.binar.notetaking.presentation.ui.login.LoginFragment
import com.binar.notetaking.util.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSource = NoteDatabase.getInstance(requireContext()).noteDao
        val application = requireNotNull(this.activity).application

        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        )[HomeViewModel::class.java]

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}