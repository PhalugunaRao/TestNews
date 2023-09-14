package com.example.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).apply(
        RequestOptions().placeholder(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE),
    ).into(imageView)
}
