package com.yoshimasa36g.tryjetpackcompose.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.tooling.preview.Preview
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModel
import com.yoshimasa36g.tryjetpackcompose.models.UsersViewModelForPreview
import com.yoshimasa36g.tryjetpackcompose.navigateToDetail

@Composable
fun UsersView(model: UsersViewModel) {
    Column {
        TopAppBar(title = { Text(text = "TryJetpackCompose") })

        FlexColumn(
            crossAxisSize = LayoutSize.Expand,
            modifier = Spacing(16.dp)
        ) {
            inflexible {
                TextField(
                    value = model.keyword,
                    onValueChange = { text -> model.keyword = text })
                HeightSpacer(height = 16.dp)
            }

            expanded(flex = 1f) {
                VerticalScroller {
                    Column {
                        model.filteredUsers.forEach {
                            Ripple(bounded = true) {
                                Clickable(onClick = { navigateToDetail(it) }) {
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
