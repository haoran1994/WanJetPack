package com.haoran.basecommon.ext

import android.view.View
import android.view.ViewGroup

/**
 * className：ViewExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:39
 * author： haoran
 * descrioption：ViewExt
 **/
val matchParent: Int = ViewGroup.LayoutParams.MATCH_PARENT
val wrapContent: Int = ViewGroup.LayoutParams.WRAP_CONTENT

val match_match : ViewGroup.LayoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
val match_wrap : ViewGroup.LayoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
val wrap_wrap : ViewGroup.LayoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
val wrap_match : ViewGroup.LayoutParams = ViewGroup.LayoutParams(wrapContent, matchParent)

fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(left, top, right, bottom)
        requestLayout()
    }
}

fun setVisible(visible: Int, vararg views: View?) {
    for (view in views)
        view?.run {
            if (visibility != visible) {
                visibility = visible
            }
        }
}

fun goneViews( vararg views: View?){
    setVisible(View.GONE, *views)
}

fun showViews( vararg views: View?){
    setVisible(View.VISIBLE, *views)
}
