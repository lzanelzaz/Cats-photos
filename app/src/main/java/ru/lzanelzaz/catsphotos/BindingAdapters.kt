package ru.lzanelzaz.catsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import ru.lzanelzaz.catsphotos.ui.ApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.signal_lost)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}