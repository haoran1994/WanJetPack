package com.haoran.base.ext.qmui

import androidx.annotation.StringRes
import com.haoran.base.mvvm.fragment.BaseQMUIFragment
import com.qmuiteam.qmui.widget.QMUITopBarLayout



fun QMUITopBarLayout.setTitleWithBackBtn(title:String, fragment: BaseQMUIFragment){
    setTitle(title)
    addLeftBackImageButton().setOnClickListener {
        fragment.finish()
    }
}

fun QMUITopBarLayout.setTitleWithBackBtn(@StringRes resId:Int, fragment:BaseQMUIFragment){
    setTitleWithBackBtn(context.getString(resId),fragment)
}