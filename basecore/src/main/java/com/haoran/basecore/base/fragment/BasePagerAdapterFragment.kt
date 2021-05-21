package com.haoran.basecore.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.LoadMoreModule
import com.haoran.basecommon.ext.dp2px
import com.haoran.basecore.base.viewmodel.BaseListViewModel
import com.haoran.basecore.ext.createListVmObserve
import com.haoran.basecore.ext.init
import com.haoran.basecore.ext.showSuccessPage
import com.haoran.basecore.widget.TheSpaceItemDecoration

/**
 * className：BasePagerAdapterFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 11:50
 * author： haoran
 * descrioption：BasePagerAdapterFragment 给定了默认的Adapter初始化和RecyclerView初始化, 需要自定义下拉刷新控件继承此类
 **/
abstract class BasePagerAdapterFragment
<T, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BasePagerRecyclerViewFragment<T, VM, DB>()
    , OnLoadMoreListener, OnItemClickListener {

    /**
     * 由于这个界面需要添加一个泛型T,所以要改变一下ViewModel的位置
     * @return Int
     */
    override fun getViewModelIndex(): Int = 1

    val mAdapter: BaseQuickAdapter<T, *> by lazy {
        createAdapter()
    }

    abstract fun createAdapter(): BaseQuickAdapter<T, *>

    override fun createObserver() {
        createListVmObserve()
    }

    /**
     * 初始化默认的适配器配置
     * 如有需要，重写此方法进行更改
     */
    override fun initAdapter() {
        mAdapter.run {
            if (this is LoadMoreModule)
                loadMoreModule.setOnLoadMoreListener(this@BasePagerAdapterFragment)
            setOnItemClickListener(this@BasePagerAdapterFragment)
        }
    }

    override fun initRecyclerView() {
        getRecyclerView().init(getLayoutManager(), mAdapter, getItemDecoration())
    }

    /**
     * 给一个默认的间距配置
     * @return RecyclerView.ItemDecoration
     */
    override fun getItemDecoration(): RecyclerView.ItemDecoration {
        return TheSpaceItemDecoration(
            getSpanCount(),
            dp2px(getItemSpace()),
            mAdapter.headerLayoutCount
        )
    }

    /**
     * 第一次加载
     */
    override fun onFirstLoading() {
        // 清空数据，因为这个方法可能会在有数据时再次调用
        mAdapter.setNewInstance(null)
        // 一开始是使用这个方法，但是觉得还是直接清空数据比较好
        //getRecyclerView().scrollToPosition(0)
        super.onFirstLoading()
    }

    /**
     * 第一次加载数据成功
     * @param data List<T>
     */
    override fun onFirstLoadSuccess(data: List<T>) {
        mAdapter.setList(data)
        showSuccessPage()
    }

    /**
     * 刷新成功
     * @param data List<T>
     */
    override fun onRefreshSuccess(data: List<T>) {
        mAdapter.setList(data)
    }

    /**
     * 加载更多成功
     * @param data List<T>
     */
    override fun onLoadMoreSuccess(data: List<T>) {
        mAdapter.addData(data)
    }

    /**
     * 数据加载后，当前的页数<总页数时会触发此方法
     */
    override fun onLoadMoreComplete() {
        mAdapter.loadMoreModule.loadMoreComplete()
    }

    /**
     * 加载更多失败
     * @param errorMsg String?
     */
    override fun onLoadMoreError(errorMsg: String?) {
        mAdapter.loadMoreModule.loadMoreFail()
    }

    /**
     * 当没有数据时会触发此方法
     */
    override fun onLoadMoreEnd() {
        mAdapter.loadMoreModule.loadMoreEnd()
    }

}