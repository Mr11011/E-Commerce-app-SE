package com.example.buywzme.data

data class User(
    val FirstName: String,
    val LastName: String,
    val Email: String,
    val ImagePath: String = ""
) {
    constructor() : this("", "", "", "")


}
