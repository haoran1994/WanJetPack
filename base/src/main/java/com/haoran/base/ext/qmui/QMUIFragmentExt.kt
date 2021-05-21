package com.haoran.base.ext.qmui

import android.content.Context
import androidx.fragment.app.Fragment
import com.haoran.base.mvvm.fragment.BaseQMUIFragment
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog



fun Fragment.showMsgTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_NOTHING, delay, callback)
}

fun Fragment.showInfoTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_INFO, delay, callback)
}

fun BaseQMUIFragment.showSuccessTipsExitDialog(msg: CharSequence?) {
    context?.showSuccessTipsDialog(msg) {
        finish()
    }
}

fun Fragment.showSuccessTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, delay, callback)
}

fun Fragment.showFailTipsDialog(msg: CharSequence?, delay: Long = 1000, callback: (() -> Unit?)? = null) {
    context?.showTipsDelayedDismiss(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL, delay, callback)
}
