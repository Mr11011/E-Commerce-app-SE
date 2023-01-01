package com.example.buywzme.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.buyweme.R
import com.example.buyweme.R.id.action_introductionFragment_to_accountOptionsFragment
import com.example.buyweme.databinding.FragmentIntroductionBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class IntroductionFragment : Fragment(R.layout.fragment_introduction) {

    private lateinit var binding: FragmentIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =  FragmentIntroductionBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startBtn.setOnClickListener{
            it.findNavController().navigate(action_introductionFragment_to_accountOptionsFragment)

        }





    }


}