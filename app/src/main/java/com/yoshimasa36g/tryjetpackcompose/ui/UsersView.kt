package com.yoshimasa36g.tryjetpackcompose.ui

import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.onActive
import androidx.compose.unaryPlus
import androidx.ui.core.TextField
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.Scene
import com.yoshimasa36g.tryjetpackcompose.models.User
import com.yoshimasa36g.tryjetpackcompose.navigateTo

@Composable
fun UsersView() {
    +onActive {
        UsersViewModel.fetchUsers()
    }

    MaterialTheme {
        Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
        ) {
            TextField(
                value = UsersViewModel.keyword,
                onValueChange = { text -> UsersViewModel.keyword = text })
            HeightSpacer(height = 16.dp)

            VerticalScroller {
                Column {
                    UsersViewModel.filteredUsers.forEach {
                        Ripple(bounded = true) {
                            Clickable(onClick = { navigateTo(Scene.Detail) }) {
                                UserSummaryView(user = it)
                            }
                        }
                        HeightSpacer(height = 8.dp)
                    }
                }
            }
        }
    }
}

@Model
private object UsersViewModel {
    var keyword: String = ""
    var filteredUsers: List<User> = listOf()

    private var users: List<User> = listOf()

    fun fetchUsers() {
        users = listOf(
            User(
                "Nicholas Cage 1",
                "male",
                "cage@example.com",
                "09000000000",
                "https://www.placecage.com/100/100",
                "https://www.placecage.com/200/200"
            ),
            User(
                "Nicholas Cage 2",
                "male",
                "cage@example.com",
                "09000000000",
                "https://www.placecage.com/100/100",
                "https://www.placecage.com/200/200"
            ),
            User(
                "Nicholas Cage 3",
                "male",
                "cage@example.com",
                "09000000000",
                "https://www.placecage.com/100/100",
                "https://www.placecage.com/200/200"
            )
        )
        filteredUsers = users
    }
}

@Preview
@Composable
fun UsersViewPreview() {
    UsersView()
}
