package com.russellworld.sofegram.models

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    val status: String = "",
    val photoUrl: String = "",
    val phone: String = ""
)