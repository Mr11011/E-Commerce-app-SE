package com.example.buywzme.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buywzme.data.Category
import com.example.buywzme.viewmodel.CategoryViewModels
import com.google.firebase.firestore.FirebaseFirestore

class BaseCategoryFactory(
    private val firestore: FirebaseFirestore,
    private val category: Category
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModels(firestore, category) as T
    }
}