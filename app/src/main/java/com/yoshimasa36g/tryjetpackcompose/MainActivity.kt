package com.yoshimasa36g.tryjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.models.User
import com.yoshimasa36g.tryjetpackcompose.models.UsersFromWeb
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModelForPreview
import com.yoshimasa36g.tryjetpackcompose.ui.UserDetailView
import com.yoshimasa36g.tryjetpackcompose.ui.UsersView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val usersViewModel = UsersFromWeb()
        usersViewModel.fetchUsers()

        setContent {
            MaterialTheme {
                Column {
                    when (AppState.scene) {
                        Scene.Users -> UsersView(usersViewModel)
                        Scene.Detail -> UserDetailView(user = AppState.selectedUser!!)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    UsersView(UsersViewModelForPreview())
}

sealed class Scene {
    object Users : Scene()
    object Detail : Scene()
}

@Model
object AppState {
    var scene: Scene = Scene.Users
    var selectedUser: User? = null
}

fun navigateToUsers() {
    AppState.scene = Scene.Users
    AppState.selectedUser = null
}

fun navigateToDetail(user: User) {
    AppState.selectedUser = user
    AppState.scene = Scene.Detail
}

