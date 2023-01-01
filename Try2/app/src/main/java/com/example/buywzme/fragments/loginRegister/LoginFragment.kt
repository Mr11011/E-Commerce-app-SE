package com.example.buywzme.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.buyweme.R
import com.example.buyweme.databinding.FragmentLoginBinding
import com.example.buywzme.activivties.ShoppingActivity
import com.example.buywzme.dialog.setupBottomSheetDialog
import com.example.buywzme.util.Resource
import com.example.buywzme.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }





        binding.apply {
            buttonLoginFrag.setOnClickListener {
                val email = emailEditTextLogin.text.toString().trim()
                val password = passwordEditTextLogin.text.toString()
                viewModel.login(email, password)


            }

        }


        binding.forgotPassword.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect {
                when (it) {
                    is Resource.Loading -> {




                    }

                    is Resource.Success -> {
                        Snackbar.make(requireView(),"Reset Link Was Sent To Your Email",Snackbar.LENGTH_LONG).show()



                    }
                    is Resource.Error -> {

                        Snackbar.make(requireView(),"Error is ${it.message}",Snackbar.LENGTH_LONG).show()


                    }

                    else -> Unit
                }


            }
        }




        lifecycleScope.launchWhenStarted {
            viewModel.login.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.buttonLoginFrag.startAnimation()


                    }

                    is Resource.Success -> {
                        binding.buttonLoginFrag.revertAnimation()
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                        }


                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        binding.buttonLoginFrag.revertAnimation()
                    }

                    else -> Unit
                }

            }

        }

    }


}
