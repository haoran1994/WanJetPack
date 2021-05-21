package com.haoran.basecore.net.error

import io.reactivex.functions.Consumer

/**
 * className：OnError
 * packageName：com.haoran.basecore.net.error
 * createTime：2021/5/21 13:57
 * author： haoran
 * descrioption：OnError
 **/
open interface OnError : Consumer<Throwable?> {
    @Throws(Exception::class)
    override fun accept(throwable: Throwable?) {
        onError(ErrorInfo(throwable!!))
    }

    @Throws(Exception::class)
    fun onError(error: ErrorInfo?)
}