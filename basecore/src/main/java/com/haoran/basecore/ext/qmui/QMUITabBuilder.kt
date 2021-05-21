package com.haoran.basecore.ext.qmui

import android.content.Context
import androidx.viewpager.widget.ViewPager
import com.haoran.basecommon.ext.getDrawable
import com.haoran.basecore.data.entity.QMUITabBean
import com.qmuiteam.qmui.widget.tab.QMUITab
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.qmuiteam.qmui.widget.tab.QMUITabSegment

/**
 * className：QMUITabBuilder
 * packageName：com.haoran.basecore.ext.qmui
 * createTime：2021/5/21 13:50
 * author： haoran
 * descrioption：QMUITabBuilder
 **/
fun QMUITabBuilder.createTab(
    context: Context,
    text: CharSequence,
    normal: Int = NO_SET,
    select: Int = NO_SET
): QMUITab {
    setText(text)
    if (normal != NO_SET)
        setNormalDrawable(getDrawable(context, normal))
    if (normal != NO_SET)
        setSelectedDrawable(getDrawable(context, select))
    return build(context)
}

fun QMUITabBuilder.createTab(context: Context, tab: QMUITabBean): QMUITab {
    return createTab(context, tab.title, tab.normal, tab.select)
}

fun QMUITabSegment.init(viewPager: ViewPager, tabs: List<QMUITabBean>, builder: QMUITabBuilder) {
    for (tab in tabs) {
        addTab(builder.createTab(context, tab))
    }
    setupWithViewPager(viewPager, false)
}

fun MutableList<QMUITabBean>.addTab(title: String, normal: Int = NO_SET, select: Int = NO_SET) {
    add(QMUITabBean(title, normal, select))
}

private val NO_SET: Int = -1