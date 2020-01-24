package com.yoshimasa36g.tryjetpackcompose.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.models.User

@Composable
fun UserSummaryView(user: User) {
    Container(expanded = true, alignment = Alignment.CenterLeft) {
        Row(crossAxisAlignment = CrossAxisAlignment.Center) {
            CircleImage(url = user.thumbnailURL, size = 60.dp)
            WidthSpacer(width = 16.dp)
            Column(mainAxisAlignment = MainAxisAlignment.Start) {
                Text(text = user.name)
                Text(text = user.email)
            }
        }
    }
}

@Preview
@Composable
fun UserSummaryPreview() {
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
}
