package com.yoshimasa36g.tryjetpackcompose.models

interface UserConvertible {
    fun toUser(): User
}

data class User(
    val name: String,
    val gender: String,
    val email: String,
    val phone: String,
    val thumbnailURL: String,
    val imageURL: String
)
