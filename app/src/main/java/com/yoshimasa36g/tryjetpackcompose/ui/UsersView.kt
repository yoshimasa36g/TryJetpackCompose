package com.yoshimasa36g.tryjetpackcompose.ui

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.unaryPlus
import androidx.ui.core.TextField
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.Scene
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModel
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModelForPreview
import com.yoshimasa36g.tryjetpackcompose.navigateTo

@Composable
fun UsersView(model: UsersViewModel) {
    MaterialTheme {
        FlexColumn(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
        ) {
            flexible(flex = 0.2f) {
                TextField(
                    value = model.keyword,
                    onValueChange = { text -> model.keyword = text })
                HeightSpacer(height = 16.dp)
            }

            flexible(flex = 0.8f) {
                VerticalScroller {
                    Column {
                        model.filteredUsers.forEach {
                            Ripple(bounded = true) {
                                Clickable(onClick = { navigateTo(Scene.Detail) }) {
                                    UserSummaryView(user = it)
                                }
                            }
                            Divider(color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UsersViewPreview() {
    UsersView(UsersViewModelForPreview())
}
