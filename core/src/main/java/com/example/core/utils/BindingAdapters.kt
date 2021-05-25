package com.example.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.R
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent


/**
 * Created on : 26/05/21 | 01.01
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@OptIn(KoinApiExtension::class)
object BindingAdapters : KoinComponent {

    @BindingAdapter("glideSrc")
    @JvmStatic
    fun setGlideImage(view: ImageView, imageUrl: String?) {
        imageUrl?.let{
            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2".plus(it))
                .fitCenter()
                .apply(RequestOptions().override(300, 300))
                .into(view)
        }
    }
}

