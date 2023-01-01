package com.example.buyweme.fragments.Login_register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.buyweme.R
import com.example.buyweme.data.User
import com.example.buyweme.databinding.FragmentRegisterBinding
import com.example.buyweme.util.Resource
import com.example.buyweme.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


private val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            registerbutton.setOnClickListener {
                val user =
                    User(
                        registerTxt1.text.toString().trim(),
                        registerTxt2.text.toString().trim(),
                        registerTxt3.text.toString().trim()
                    )
                val password = registerTxt4.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)


            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.registerbutton.startAnimation()

                    }
                    is Resource.Success -> {
                        Log.d("test", it.message.toString())
                        binding.registerbutton.revertAnimation()

                    }
                    is Resource.Error -> {
                        Log.d(TAG, it.message.toString())
                        binding.registerbutton.revertAnimation()


                    }

                    else -> {}
                }
            }
        }
    }
}