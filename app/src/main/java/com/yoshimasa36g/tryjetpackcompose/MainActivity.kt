package com.yoshimasa36g.tryjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.onActive
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.models.UsersFromWeb
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModelForPreview
import com.yoshimasa36g.tryjetpackcompose.ui.UsersView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val usersViewModel = UsersFromWeb()
        usersViewModel.fetchUsers()

        setContent {
            MaterialTheme {
                Column {
                    TopAppBar(title = { Text(text = "TryJetpackCompose") })
                    when (AppState.scene) {
                        Scene.Users -> UsersView(usersViewModel)
                        Scene.Detail -> SecondScene()
                    }
                }
            }
        }
    }
}

@Composable
fun SecondScene() {
    MaterialTheme {
        Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
        ) {
            Text(text = "Scene 2")
            Button("back",
                onClick = { navigateTo(Scene.Users) })
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
}

fun navigateTo(scene: Scene) {
    AppState.scene = scene
}
