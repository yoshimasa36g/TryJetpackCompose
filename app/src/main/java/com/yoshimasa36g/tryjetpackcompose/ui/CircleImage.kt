package com.yoshimasa36g.tryjetpackcompose.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.Surface
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.core.px
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.*
import androidx.ui.graphics.colorspace.ColorSpace
import androidx.ui.graphics.colorspace.ColorSpaces
import androidx.ui.layout.Container
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.yoshimasa36g.tryjetpackcompose.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

@Composable
fun CircleImage(url: String, size: Dp) {
    fetchImage(url)

    Container(expanded = true, height = size, width = size) {
        Clip(shape = CircleShape) {
            DrawImage(image = CircleImageState.image)
        }
    }
}

@Model
object CircleImageState {
    var image: Image = +imageResource(R.mipmap.person)
}

private fun fetchImage(url: String) {
    Picasso.get().load(url).into(DownloadTarget)
}

object DownloadTarget : Target {
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        val source = bitmap ?: return
        MainScope().launch {
            CircleImageState.image = BitmapImage(source)
        }
    }
}

class BitmapImage(private val bitmap: Bitmap) : Image {
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
fun DefaultPreview() {
    CircleImage("https://www.placecage.com/c/100/100", 200.dp)
}
