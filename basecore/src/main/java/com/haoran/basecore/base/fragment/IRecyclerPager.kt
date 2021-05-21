package com.haoran.basecore.base.fragment

import androidx.recyclerview.widget.RecyclerView
import com.haoran.basecore.data.enum.LayoutManagerType

/**
 * className：IRecyclerPager
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 13:46
 * author： haoran
 * descrioption：IRecyclerPager
 **/
interface IRecyclerPager<T> {

    fun getRecyclerView(): RecyclerView

    /**
     * 初始化适配器
     */
    fun initAdapter()

    /**
     * 初始化RecyclerView
     */
    fun initRecyclerView()

    /**
     * 初始化下拉刷新
     */
    fun initPullRefreshView()

    /**
     * 第一次显示时的加载情况
     */
    fun onFirstLoading()

    /**
     * 第一次加载数据完成
     */
    fun onFirstLoadSuccess(data:List<T>)

    /**
     * 第一次加载数据失败
     */
    fun onFirstLoadError(errorMsg:String?)

    /**
     * 刷新时的操作
     */
    fun onRefresh()

    /**
     * 刷新失败
     */
    fun onRefreshError(errorMsg:String?)

    /**
     * 刷新完成
     */
    fun onRefreshSuccess(data:List<T>)

    /**
     * 空数据
     */
    fun onEmptyData()

    /**
     * 回到界面自动刷新，刷新前可能是空白页，也可能存在数据
     */
    fun onAutoRefresh()

    /**
     * 加载更多
     */
    fun onLoadMore()

    /**
     * 加载更多失败
     */
    fun onLoadMoreSuccess(data:List<T>)

    /**
     * 加载更多失败
     */
    fun onLoadMoreError(errorMsg:String?)

    /**
     * 加载更多完成，还有数据
     */
    fun onLoadMoreComplete()

    /**
     * 没有更多了
     */
    fun onLoadMoreEnd()

    /**
     * 获取LayoutManager
     */
    fun getLayoutManager(): RecyclerView.LayoutManager

    /**
     * 获取LayoutManager
     */
    fun getLayoutManagerType(): LayoutManagerType

    /**
     * 分割线
     */
    fun getItemDecoration(): RecyclerView.ItemDecoration

    /**
     * 获取列数
     */
    fun getSpanCount(): Int

    /**
     * 获取间距大小
     */
    fun getItemSpace(): Int

}