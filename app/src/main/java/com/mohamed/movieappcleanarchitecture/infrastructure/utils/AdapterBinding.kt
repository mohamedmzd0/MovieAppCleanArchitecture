package com.mohamed.movieappcleanarchitecture.infrastructure.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.load


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) = imageView.load(url)

@BindingAdapter("showOrHide")
fun showOrHide(view: View, show: Boolean) =  { view.isVisible = show }