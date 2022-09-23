package com.binar.notetaking.presentation.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.binar.notetaking.data.local.user.UserDatabase
import com.binar.notetaking.data.local.user.UserEntity
import com.binar.notetaking.databinding.FragmentRegisterBinding
import com.binar.notetaking.util.LoginRegisterViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSource = UserDatabase.getInstance(requireContext()).userDao
        val application = requireNotNull(this.activity).application

        val viewModelFactory = LoginRegisterViewModelFactory(dataSource, application)
        val registerViewModel = ViewModelProvider(
            this, viewModelFactory
        )[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val user = UserEntity(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )

            Log.d("register", user.username.toString())
            Log.d("register", user.password.toString())
            registerViewModel.registerUser(user)
            Toast.makeText(context, "Register SUCCESS", Toast.LENGTH_SHORT).show()
        }


/*        binding.lifecycleOwner = this ---requires databindingutil
        binding.registerViewModel = registerViewModel*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}