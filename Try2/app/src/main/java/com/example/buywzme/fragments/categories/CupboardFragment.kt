package com.example.buywzme.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.buywzme.data.Category
import com.example.buywzme.util.Resource
import com.example.buywzme.viewmodel.CategoryViewModels
import com.example.buywzme.viewmodel.factory.BaseCategoryFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
@AndroidEntryPoint
class CupboardFragment: BaseCategoryFragment() {

    @Inject
    lateinit var firestore: FirebaseFirestore
    val viewModel by viewModels<CategoryViewModels>{
        BaseCategoryFactory(firestore, Category.Cupboard)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.offerProducts.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success->{
                        offerAdapter.differ.submitList(it.data)
                    }
                    is Resource.Error ->{
                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }

                    else ->Unit
                }
            }

        }


        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success->{
                        bestProductAdapter.differ.submitList(it.data)
                    }
                    is Resource.Error ->{
                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }

                    else ->Unit
                }
            }

        }

    }
}