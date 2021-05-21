package com.haoran.basecore.net

/**
 * className：IResponse
 * packageName：com.haoran.basecore.net
 * createTime：2021/5/21 13:55
 * author： haoran
 * descrioption：IResponse
 **/
interface IResponse<T> {

    fun isSuccess(): Boolean
    fun getResponseData(): T?
    fun getMsg():String?
    fun getPageInfo(): IPageInfo?

}