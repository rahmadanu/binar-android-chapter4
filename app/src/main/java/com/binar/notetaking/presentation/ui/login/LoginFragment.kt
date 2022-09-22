package com.binar.notetaking.presentation.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.notetaking.R
import com.binar.notetaking.data.local.UserDatabase
import com.binar.notetaking.databinding.FragmentLoginBinding
import com.binar.notetaking.util.ViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSource = UserDatabase.getInstance(requireContext()).userDao
        val application = requireNotNull(this.activity).application

        val viewModelFactory = ViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        )[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()

            viewModel.getUser(username)

            viewModel.user.observe(viewLifecycleOwner) { user ->
                Log.d("login", "username: ${user.username}")
                Log.d("login", "password: ${user.password}")
                isLoginInfoCorrect(user.username, user.password)
            }
        }

        binding.tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun isLoginInfoCorrect(usernameData: String?, passwordData: String?) {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (username == usernameData && password == passwordData) {
            Toast.makeText(context, "LOGIN BERHASIL", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "USERNAME ATAU PASSWORD ANDA SALAH", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}