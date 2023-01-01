package com.example.buywzme.util

import android.util.Patterns

// we create this in file to use it in LoginFragment so we use the same function again

fun validateEmail(email: String): RegisterValidation {
    if (email.isEmpty())
        return RegisterValidation.Failed("Email cannot be empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format")
    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation {
    if (password.isEmpty())
        return RegisterValidation.Failed("Password is Empty")
    if (password.length > 6)
        return RegisterValidation.Failed("Password should be 6 characters")

    return RegisterValidation.Success

}