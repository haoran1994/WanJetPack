package com.haoran.wanjetpack.app.net

import com.haoran.basecore.net.IPageInfo
import java.io.Serializable

/**
 * className：PageResponseExt
 * packageName：com.haoran.wanjetpack.app.net
 * createTime：2021/5/21 15:21
 * author： haoran
 * descrioption：PageResponseExt
 **/
class PagerResponse<T> : IPageInfo, Serializable {
    var curPage = 0
    var pageCount = 0
    var size = 0
    var total = 0
    var datas: T? = null

    override fun getPage(): Int {
        return curPage
    }

    override fun getPageTotalCount(): Int {
        return pageCount
    }

    override fun getTotalCount(): Int {
        return total
    }

    override fun getPageSize(): Int {
        return size
    }
}