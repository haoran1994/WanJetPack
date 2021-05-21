package com.haoran.basecore.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.haoran.basecore.R
import com.haoran.basecore.base.fragment.BaseCoreFragment
import com.haoran.basecore.widget.loadsir.callback.ErrorCallback
import com.haoran.basecore.widget.loadsir.callback.LoadingCallback
import com.haoran.basecore.widget.loadsir.callback.SuccessCallback
import com.haoran.basecore.widget.loadsir.core.LoadService
import com.haoran.basecore.widget.loadsir.core.LoadSir

/**
 * className：LoadSerExt
 * packageName：com.haoran.basecore.ext
 * createTime：2021/5/21 13:52
 * author： haoran
 * descrioption：LoadSerExt
 **/
/**
 * 提供默认的init方法
 */
fun initLoadSir(){
    LoadSir.beginBuilder()
        .addCallback(LoadingCallback())
        .addCallback(ErrorCallback())
        .setDefaultCallback(SuccessCallback::class.java)
        .commit()
}

fun loadSirInit(view: View, callback: () -> Unit): LoadService<Any> {
    return LoadSir.getDefault().register(view) {
        //点击重试时触发的操作
        callback.invoke()
    }
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showError(message: String?, imageRes:Int = R.drawable.status_loading_view_loading_fail) {
    this.setCallBack(ErrorCallback::class.java) { _, view ->
        view.findViewById<TextView>(R.id.stateContentTextView).text = message
        view.findViewById<ImageView>(R.id.stateImageView).setImageResource(imageRes)
    }
    this.showCallback(ErrorCallback::class.java)
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showEmpty(message: String,imageRes:Int = R.drawable.status_search_result_empty) {
    showError(message,imageRes)
}

/**
 * 设置空布局
 */
fun LoadService<*>.showEmpty() {
    showEmpty("暂无此内容")
}

/**
 * 设置加载中
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}

fun BaseCoreFragment<*, *>.showSuccessPage(){
    mLoadSir?.showSuccess()
}

fun BaseCoreFragment<*,*>.showLoadingPage(){
    mLoadSir?.showLoading()
}

fun BaseCoreFragment<*,*>.showErrorPage(message: String?, imageRes:Int = R.drawable.status_loading_view_loading_fail){
    mLoadSir?.showError(message,imageRes)
}

fun BaseCoreFragment<*,*>.showEmptyPage(message: String, imageRes:Int = R.drawable.status_search_result_empty){
    mLoadSir?.showEmpty(message,imageRes)
}

fun BaseCoreFragment<*,*>.showEmptyPage(){
    mLoadSir?.showEmpty()
}