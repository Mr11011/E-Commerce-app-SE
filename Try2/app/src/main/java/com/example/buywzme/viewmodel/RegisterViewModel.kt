package com.example.buywzme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.buywzme.data.User
import com.example.buywzme.util.*
import com.example.buywzme.util.Constant.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {
    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    // channel does not take any initial value and that's the difference between mutable list
    private val _validation = Channel<RegitserFieldsState> { }
    val validation = _validation.receiveAsFlow()


    fun createAccountWithEmailAndPassword(user: User, password: String) {
        if (checkValidation(user, password)) {


            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.Email, password)

                .addOnSuccessListener { it ->
                    it.user?.let {

                        saveUserInfo(it.uid, user)
                    }


                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())

                }

        } else {
            val registerFieldsState = RegitserFieldsState(
                validateEmail(user.Email), validatePassword(password)
            )
            runBlocking { _validation.send(registerFieldsState) }

        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.Email)
        val passValidation = validatePassword(password)
        val validRegister =
            emailValidation is RegisterValidation.Success && passValidation is RegisterValidation.Success
        return validRegister

    }

    private fun saveUserInfo(userUId: String, user: User) {
        db.collection(USER_COLLECTION).document(userUId).set(user).addOnSuccessListener {
            _register.value = Resource.Success(user)
        }.addOnFailureListener {
            _register.value = Resource.Error(it.message.toString())

        }

    }

}



