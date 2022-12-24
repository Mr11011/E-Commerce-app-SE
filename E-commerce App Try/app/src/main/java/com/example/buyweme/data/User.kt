package com.example.buyweme.data

data class User(
    val firstname: String,
    val LastName: String,
    val password: String,
    val email: String,
    val ImagePath: String?
) {
    constructor() : this("", "", "", "", "")
}
