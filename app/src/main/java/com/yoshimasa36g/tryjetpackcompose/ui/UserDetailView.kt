package com.yoshimasa36g.tryjetpackcompose.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.graphics.Image
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.R
import com.yoshimasa36g.tryjetpackcompose.models.User
import com.yoshimasa36g.tryjetpackcompose.navigateToUsers

@Composable
fun UserDetailView(user: User) {
    val phone = +imageResource(R.drawable.phone)
    val email = +imageResource(R.drawable.email)
    val back = +imageResource(R.drawable.back)
    MaterialTheme {
        TopAppBar(
            title = { Text(text = user.name) },
            navigationIcon = {
                Clickable(onClick = { navigateToUsers() }) {
                    Icon(image = back, size = 24.dp)
                }
            })

        Container(expanded = true) {
            Column(crossAxisAlignment = CrossAxisAlignment.Center) {
                HeightSpacer(height = 150.dp)
                CircleImage(
                    model = CircleImageViewModel(user.imageURL),
                    size = 200.dp
                )
                HeightSpacer(height = 8.dp)
                Text(
                    user.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                HeightSpacer(height = 8.dp)
                Row {
                    Icon(image = email)
                    WidthSpacer(width = 8.dp)
                    Text(user.email)
                }
                HeightSpacer(height = 8.dp)
                Row {
                    Icon(image = phone)
                    WidthSpacer(width = 8.dp)
                    Text(user.phone)
                }
            }
        }
    }
}

@Composable
private fun Icon(image: Image, size: Dp = 18.dp) {
    Container(expanded = true, width = size, height = size) {
        Clip(shape = RectangleShape) {
            DrawImage(image = image)
        }
    }
}

@Preview
@Composable
fun UserDetailViewPreview() {
    UserDetailView(
        User(
            "Nicholas Cage",
            "male",
            "cage@example.com",
            "09000000000",
            "https://www.placecage.com/100/100",
            "https://www.placecage.com/200/200"
        )
    )
}
