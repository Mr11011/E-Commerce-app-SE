package com.example.buywzme.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.buyweme.R
import com.example.buyweme.databinding.FragmentAccountOptionsBinding
import com.example.buyweme.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountOptionsFragment : Fragment(R.layout.fragment_account_options) {
    private lateinit var binding: FragmentAccountOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding=FragmentAccountOptionsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLoginAccOption.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionsFragment_to_loginFragment)

        }
        binding.buttonRegisterAccOption.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionsFragment_to_registerFragment)

        }
    }
}