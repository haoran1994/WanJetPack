package com.haoran.wanjetpack.app.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * className：ImageViewExt
 * packageName：com.haoran.wanjetpack.app.ext
 * createTime：2021/5/21 15:20
 * author： haoran
 * descrioption：ImageViewExt
 **/
fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}