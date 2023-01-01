package com.example.buyweme.data

data class User(
    var firstname: String,
    val LastName: String,
    val email: String,
    val ImagePath: String?
) {
    constructor() : this("", "", "", "")
    constructor(trim: String, trim1: String, trim2: String) : this()

}
