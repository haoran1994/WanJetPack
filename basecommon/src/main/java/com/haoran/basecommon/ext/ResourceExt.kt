package com.haoran.basecommon.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * className：ResourceExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:37
 * author： haoran
 * descrioption：ResourceExt
 **/

fun View.getColor(@ColorRes id:Int):Int{
    return ContextCompat.getColor(context,id)
}

fun View.getDrawable(@DrawableRes id:Int): Drawable?{
    return ContextCompat.getDrawable(context,id)
}

fun View.getString(@StringRes id:Int):String?{
    return context.getString(id)
}

fun getDrawable(context: Context, @DrawableRes id:Int):Drawable?{
    return ContextCompat.getDrawable(context,id)
}

fun getColor(context: Context,@ColorRes id:Int):Int{
    return ContextCompat.getColor(context,id)
}
