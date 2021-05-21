package com.haoran.basecore.net.error

import android.content.Context
import android.net.ConnectivityManager
import com.haoran.base.mvvm.appContext
import com.haoran.basecore.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * className：handleException
 * packageName：com.haoran.basecore.net.error
 * createTime：2021/5/21 13:56
 * author： haoran
 * descrioption：handleException
 **/
fun <T> handleException(throwable: T): String {
    return appContext.getString(
        when (throwable) {
            is UnknownHostException -> {
                if (isNetworkConnected()) {
                    R.string.notify_no_network
                } else {
                    R.string.network_error
                }
            }
            is SocketTimeoutException, is TimeoutException -> {
                //前者是通过OkHttpClient设置的超时引发的异常，后者是对单个请求调用timeout方法引发的超时异常
                R.string.time_out_please_try_again_later
            }
            is ConnectException -> {
                //前者是通过OkHttpClient设置的超时引发的异常，后者是对单个请求调用timeout方法引发的超时异常
                R.string.esky_service_exception
            }
            else -> R.string.oops
        }
    )
}

fun isNetworkConnected(): Boolean {
    val manager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = manager.activeNetworkInfo
    networkInfo?.let {
        return it.isAvailable
    }
    return false
}