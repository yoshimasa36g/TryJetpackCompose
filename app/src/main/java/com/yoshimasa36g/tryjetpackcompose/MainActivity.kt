package com.yoshimasa36g.tryjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NewStory()
            }
        }
    }
}

@Composable
fun NewStory() {
    val image = +imageResource(R.drawable.header)
    Column(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
    ) {
        Container(expanded = true, height = 180.dp) {
            DrawImage(image)
        }

        HeightSpacer(height = 16.dp)

        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewStory()
}
