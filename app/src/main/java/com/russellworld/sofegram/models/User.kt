package com.russellworld.sofegram.models

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    val status: String = "",
    var photoUrl: String = "",
    val phone: String = ""
)