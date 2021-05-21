package com.haoran.basecore.base.viewmodel

import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.net.error.ErrorInfo
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * className：BaseRequestViewModel
 * packageName：com.haoran.basecore.base.viewmodel
 * createTime：2021/5/21 11:50
 * author： haoran
 * descrioption：BaseRequestViewModel
 **/
abstract class BaseRequestViewModel<T> : BaseViewModel() {

    /**
     * 请求返回的数据
     */
    private val response: UnPeekLiveData<T> =
        UnPeekLiveData.Builder<T>().setAllowNullValue(true).create()

    /**
     * 错误原因
     */
    private val error: UnPeekLiveData<String> =
        UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()

    /**
     * 请求无论成功或者失败之后的回调
     */
    private val finally: UnPeekLiveData<Boolean> = UnPeekLiveData()

    /**
     * 向 ui 层提供 ProtectedUnPeekLiveData，从而限制从 Activity/Fragment 篡改来自 "数据层" 的数据，数据层的数据务必通过 "唯一可信源" 来分发
     * from: KunMinX  https://xiaozhuanlan.com/topic/0168753249
     */
    fun getResponseLiveData(): ProtectedUnPeekLiveData<T> = response
    fun getErrorMsgLiveData(): ProtectedUnPeekLiveData<String> = error
    fun getFinallyLiveData(): ProtectedUnPeekLiveData<Boolean> = finally

    /**
     * 请求成功后设置数据调用此方法
     * @param response
     */
    open fun onSuccess(response: T?) {
        this.response.value = response
    }

    /**
     * 请求错误时调用此方法
     * @param errorMsg 错误信息
     * @param errorLiveData 错误接收的LiveData
     */
    open fun onError(errorMsg: String?, errorLiveData: UnPeekLiveData<String>? = error) {
        errorLiveData?.value = errorMsg
    }

    open fun onError(throwable: Throwable, liveData: UnPeekLiveData<String>? = error) {
        onError(ErrorInfo(throwable).errorMsg, liveData)
    }

    fun onFinally() {
        finally.value = true
    }

    abstract fun requestServer()

}