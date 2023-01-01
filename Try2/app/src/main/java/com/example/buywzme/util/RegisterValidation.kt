package com.example.buywzme.util

sealed class RegisterValidation() {
    object Success : RegisterValidation()
    data class Failed(val message: String) : RegisterValidation()


}

data class RegitserFieldsState(
    val email: RegisterValidation,
    val password: RegisterValidation
)
