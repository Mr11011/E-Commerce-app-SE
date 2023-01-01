package com.example.buywzme.fragments.loginRegister


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.buyweme.R
import com.example.buyweme.databinding.FragmentRegisterBinding

import com.example.buywzme.data.User
import com.example.buywzme.util.RegisterValidation
import com.example.buywzme.util.Resource
import com.example.buywzme.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    val viewModel by viewModels<RegisterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.navToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        binding.apply {
            buttonRegisterFrag.setOnClickListener {
                val user = User(
                    edFname.text.toString().trim(),
                    edLName.text.toString().trim(),
                    edEmail.text.toString().trim()

                )
                val password = edPassword.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)

            }

        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {

                when (it) {
                    is Resource.Loading -> {
                        binding.buttonRegisterFrag.startAnimation()

                    }
                    is Resource.Success -> {
                        Log.d("test", it.data.toString())
                        binding.buttonRegisterFrag.revertAnimation()

                    }
                    is Resource.Error -> {
                        Log.e(TAG, it.message.toString())
                        binding.buttonRegisterFrag.revertAnimation()
                    }
                    else -> {}
                }

            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {

                        binding.edEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {

                        binding.edPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }
    }
}

