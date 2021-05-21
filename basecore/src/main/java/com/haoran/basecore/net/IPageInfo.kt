package com.haoran.basecore.net

/**
 * className：IPageInfo
 * packageName：com.haoran.basecore.net
 * createTime：2021/5/21 13:55
 * author： haoran
 * descrioption：IPageInfo
 **/
interface IPageInfo {

    // 页数
    fun getPage():Int

    // 总页数
    fun getPageTotalCount():Int

    // 总条数
    fun getTotalCount():Int

    // 每页数量
    fun getPageSize():Int

}