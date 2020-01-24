package com.yoshimasa36g.tryjetpackcompose.ui

import android.graphics.Bitmap
import androidx.compose.*
import androidx.ui.core.Clip
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Image
import androidx.ui.graphics.ImageConfig
import androidx.ui.graphics.NativeImage
import androidx.ui.graphics.colorspace.ColorSpace
import androidx.ui.graphics.colorspace.ColorSpaces
import androidx.ui.layout.Container
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.squareup.picasso.Picasso
import com.yoshimasa36g.tryjetpackcompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CircleImage(model: CircleImageViewModel, size: Dp) {
    Container(expanded = true, height = size, width = size) {
        Clip(shape = CircleShape) {
            DrawImage(image = model.image)
        }
    }
}

@Model
class CircleImageViewModel(
    val url: String
) {
    var image: Image = +imageResource(R.mipmap.person)

    init {
        MainScope().launch {
            fetchImage(this@CircleImageViewModel)
        }
    }

    fun set(image: Image) {
        this.image = image
    }
}

private suspend fun fetchImage(model: CircleImageViewModel) {
    withContext(Dispatchers.IO) {
        val image = Picasso.get().load(model.url).get()
        MainScope().launch {
            model.set(BitmapImage(image))
        }
    }
}

private class BitmapImage(private val bitmap: Bitmap) : Image {
    override val colorSpace: ColorSpace = ColorSpaces.Srgb
    override val config: ImageConfig = ImageConfig.Argb8888
    override val hasAlpha: Boolean = bitmap.hasAlpha()
    override val height: Int = bitmap.height
    override val nativeImage: NativeImage = bitmap
    override val width: Int = bitmap.width
    override fun prepareToDraw() = bitmap.prepareToDraw()
}

@Preview
@Composable
fun CircleImagePreview() {
    CircleImage(
        CircleImageViewModel("https://www.placecage.com/c/100/100"),
        100.dp)
}
