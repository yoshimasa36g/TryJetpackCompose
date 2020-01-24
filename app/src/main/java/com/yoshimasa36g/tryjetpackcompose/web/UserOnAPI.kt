package com.yoshimasa36g.tryjetpackcompose.web

import com.yoshimasa36g.tryjetpackcompose.models.User
import com.yoshimasa36g.tryjetpackcompose.models.UserConvertible

data class UserOnAPI(
    val gender: String,
    val name: UserName,
    val email: String,
    val phone: String,
    val picture: UserPicture
): UserConvertible {
    override fun toUser(): User {
        return User(
            name.fullName,
            gender,
            email,
            phone,
            picture.thumbnail,
            picture.medium
        )
    }
}

data class UserName(
    val title: String,
    val first: String,
    val last: String
) {
    val fullName: String
        get() = "$title $first $last"
}

data class UserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
