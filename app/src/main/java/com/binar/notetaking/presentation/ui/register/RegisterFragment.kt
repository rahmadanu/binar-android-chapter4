package com.binar.notetaking.presentation.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.binar.notetaking.data.local.user.UserEntity
import com.binar.notetaking.databinding.FragmentRegisterBinding
import com.binar.notetaking.di.ServiceLocator
import com.binar.notetaking.util.viewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModelFactory {
        RegisterViewModel(ServiceLocator.provideServiceLocator(requireContext()))
    }

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

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

/*        binding.lifecycleOwner = this ---requires databindingutil
        binding.registerViewModel = registerViewModel*/
    }

    private fun registerUser() {
        val user = UserEntity(
            username = binding.etUsername.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
        viewModel.registerUser(user)
        Toast.makeText(context, "Register SUCCESS", Toast.LENGTH_SHORT).show()
        //validation if empty et
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}