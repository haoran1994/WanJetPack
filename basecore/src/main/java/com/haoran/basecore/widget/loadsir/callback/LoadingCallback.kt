package com.haoran.basecore.widget.loadsir.callback

import android.content.Context
import android.view.View
import com.haoran.basecore.R


class LoadingCallback : Callback() {

    override fun onCreateView(): Int  = R.layout.layout_loading

    override fun onReloadEvent(context: Context?, view: View?): Boolean  = false

}