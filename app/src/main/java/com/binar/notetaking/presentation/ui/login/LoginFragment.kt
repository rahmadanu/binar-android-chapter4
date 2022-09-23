package com.binar.notetaking.presentation.ui.login

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.binar.notetaking.R
import com.binar.notetaking.data.local.user.UserDatabase
import com.binar.notetaking.databinding.FragmentLoginBinding
import com.binar.notetaking.util.LoginRegisterViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

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
        sharedPreferences = requireContext().getSharedPreferences(LOGIN_SHARED_PREF, MODE_PRIVATE)

        configureViewModel()
        checkLogin()

        if (isLoginInfoValid()) {
            navigateToHome()
        }

        binding.tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun configureViewModel() {
        val dataSource = UserDatabase.getInstance(requireContext()).userDao
        val application = requireNotNull(this.activity).application

        val viewModelFactory = LoginRegisterViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[LoginViewModel::class.java]
    }

    private fun checkLogin() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            var userLoggedIn = false

            viewModel.getUser(username)

            viewModel.user.observe(viewLifecycleOwner) { user ->
                userLoggedIn = username == user.username && password == user.password
                if (userLoggedIn) {
                    navigateToHome()
                    Toast.makeText(context, "LOGIN BERHASIL", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "USERNAME ATAU PASSWORD ANDA SALAH", Toast.LENGTH_SHORT).show()
                }
            }
            saveLoginInfo(userLoggedIn)
        }
    }

    private fun saveLoginInfo(loginInfo: Boolean){
        sharedPreferences.edit {
            putBoolean(LOGGED_IN_KEY, loginInfo)
        }
    }

    private fun isLoginInfoValid(): Boolean {
        return sharedPreferences.getBoolean(LOGGED_IN_KEY, false)
    }

    private fun navigateToHome() {
        val option = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, true)
            .build()
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, option)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LOGIN_SHARED_PREF = "login_shared_pref"
        const val LOGGED_IN_KEY = "logged_in"
    }
}