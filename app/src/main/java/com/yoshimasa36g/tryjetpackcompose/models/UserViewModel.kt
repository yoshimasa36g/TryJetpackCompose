package com.yoshimasa36g.tryjetpackcompose.models

import androidx.compose.Model
import com.yoshimasa36g.tryjetpackcompose.web.UserOnAPI
import com.yoshimasa36g.tryjetpackcompose.web.UsersDownloader
import timber.log.Timber

interface UsersViewModel {
    var keyword: String
    var filteredUsers: List<User>
    fun fetchUsers()
}

@Model
class UsersFromWeb : UsersViewModel {
    override var keyword: String = ""
        set(value) {
            field = value
            if (value.isEmpty()) {
                filteredUsers = users
                return
            }
            filteredUsers = users.filter { it.name.contains(value) }
        }

    override var filteredUsers: List<User> = listOf()

    private var users: List<User> = listOf()

    private val api = UsersDownloader()

    override fun fetchUsers() {
        api.fetch(
            onSuccess = { store(it) },
            onFailure = { Timber.d(it.localizedMessage ?: "") })
    }

    private fun store(usersOnAPI: List<UserOnAPI>) {
        users = usersOnAPI.map(UserOnAPI::toUser)
        filteredUsers = users
    }
}

class UsersViewModelForPreview : UsersViewModel {
    override var keyword: String = ""

    override var filteredUsers: List<User> = (0..20).map { x ->
        User(
            "Nicholas Cage $x",
            "male",
            "cage@example.com",
            "09000000000",
            "https://www.placecage.com/100/100",
            "https://www.placecage.com/200/200"
        )
    }

    override fun fetchUsers() {}
}
