package com.haoran.wanjetpack.app.net

/**
 * className：Response
 * packageName：com.haoran.wanjetpack.app.net
 * createTime：2021/5/21 15:21
 * author： haoran
 * descrioption：Response
 **/
data class Response<T>(
    val errorCode: Int,
    val errorMsg: String?,
    val data: T?
)