package com.haoran.basecore.ext

import androidx.lifecycle.rxLifeScope
import com.haoran.basecore.base.viewmodel.BaseRequestViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.CoroutineScope

/**
 * className：RequestExt
 * packageName：com.haoran.basecore.ext
 * createTime：2021/5/21 13:54
 * author： haoran
 * descrioption：RequestExt
 **/
/**
 * 请求
 * @param block 请求的主函数体，得到数据后调用onSuccess方法
 * @param loadingMsg 请求时的提示语句，不为空时才开启弹窗提示
 * @param errorLiveData 接收错误的LiveData
 */
fun <T> BaseRequestViewModel<T>.request(
    block: suspend CoroutineScope.() -> Unit,
    loadingMsg: String? = null,
    errorLiveData: UnPeekLiveData<String>? = null
) {
    rxLifeScope.launch({
        block()
    }, {
        if(null != errorLiveData){
            onError(it,errorLiveData)
        }else{
            onError(it)
        }
    }, {
        loadingMsg?.let {
            loadingChange.showDialog.value = loadingMsg
        }
    }, {
        onFinally()
        loadingMsg?.let {
            loadingChange.dismissDialog.value = false
        }
    }
    )
}