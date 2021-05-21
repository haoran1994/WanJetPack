package com.haoran.base.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * className：BaseViewModel
 * packageName：com.haoran.base.mvvm.viewmodel
 * createTime：2021/5/21 11:15
 * author： haoran
 * descrioption：BaseViewModel
 **/
open class BaseViewModel : ViewModel() {

    protected val TAG: String = this.javaClass.simpleName

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { UnPeekLiveData<String>() }

        //隐藏
        val dismissDialog by lazy { UnPeekLiveData<Boolean>() }
    }

}