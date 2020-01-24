package com.yoshimasa36g.tryjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.models.User
import com.yoshimasa36g.tryjetpackcompose.ui.CircleImage
import com.yoshimasa36g.tryjetpackcompose.ui.UserSummaryView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                when (AppState.scene) {
                    Scene.Home -> NewStory()
                    Scene.Second -> SecondScene()
                }
            }
        }
    }
}

@Composable
fun NewStory() {
    val image = +imageResource(R.drawable.header)

    MaterialTheme {
        Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
        ) {
            Container(expanded = true, height = 180.dp) {
                Clip(shape = RoundedCornerShape(8.dp)) {
                    DrawImage(image)
                }
            }

            CircleImage("https://www.placecage.com/c/100/100", 80.dp)
            HeightSpacer(height = 16.dp)
            UserSummaryView(
                User(
                    "Nicholas Cage",
                    "male",
                    "cage@example.com",
                    "09000000000",
                    "https://www.placecage.com/100/100",
                    "https://www.placecage.com/200/200"
                )
            )

            Button("next", onClick = { navigateTo(Scene.Second) })
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
                onClick = { navigateTo(Scene.Home) })
        }
    }

}

@Preview
@Composable
fun DefaultPreview() {
    NewStory()
}

sealed class Scene {
    object Home: Scene()
    object Second: Scene()
}

@Model
object AppState {
    var scene: Scene = Scene.Home
}

fun navigateTo(scene: Scene) {
    AppState.scene = scene
}
