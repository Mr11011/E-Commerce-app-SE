package com.example.buywzme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buywzme.data.Product
import com.example.buywzme.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore


) : ViewModel() {
    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProducts: StateFlow<Resource<List<Product>>> = _specialProducts

    private val _bestDealsProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val bestDealsProducts : StateFlow<Resource<List<Product>>> = _bestDealsProducts

    private val _bestProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val bestProducts: StateFlow<Resource<List<Product>>> = _bestProducts


    init {
        fetchSpecialProduct()
        fetchBestDealsProduct()
        fetchBestProduct()
    }

    private fun fetchBestProduct() {
        viewModelScope.launch {
            _bestProducts.emit(Resource.Loading())
        }
        firestore.collection("Products").get()
            .addOnSuccessListener { result->

                val bestProductListProducts = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Success(bestProductListProducts))
                }

            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Error(it.message.toString()))
                }

            }

    }

    private fun fetchBestDealsProduct() {
        viewModelScope.launch {
            _bestDealsProducts.emit(Resource.Loading())
        }
        firestore.collection("Products").whereEqualTo("category","Best Deals").get()
            .addOnSuccessListener { result->

                val bestDealsListProducts = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _bestDealsProducts.emit(Resource.Success(bestDealsListProducts))
                }

            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestDealsProducts.emit(Resource.Error(it.message.toString()))
                }

            }
    }

    fun fetchSpecialProduct() {
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }

        firestore.collection("Products").get()
                // we can also use .whereEqualTo("category", "Special Products")
            .addOnSuccessListener { result ->
                val specialProdcutList = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Success(specialProdcutList))
                }


            }.addOnFailureListener {
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Error(it.message.toString()))
                }

            }

    }
}