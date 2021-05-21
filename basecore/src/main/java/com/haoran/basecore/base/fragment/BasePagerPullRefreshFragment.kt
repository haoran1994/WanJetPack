package com.haoran.basecore.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.haoran.basecore.R
import com.haoran.basecore.base.viewmodel.BaseListViewModel
import com.haoran.basecore.widget.pullrefresh.PullRefreshLayout
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUICenterGravityRefreshOffsetCalculator
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout
import kotlinx.android.synthetic.main.base_pull_fresh_fragment.*

/**
 * className：BasePagerPullRefreshFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 13:41
 * author： haoran
 * descrioption：给定了默认的下拉刷新控件 PullRefreshLayout
 **/
abstract class BasePagerPullRefreshFragment<T, VM : BaseListViewModel<T>, DB : ViewDataBinding> :
    BasePagerAdapterFragment<T, VM, DB>(),
    QMUIPullRefreshLayout.OnPullListener {

    override fun getLayoutId(): Int = R.layout.base_pull_fresh_fragment

    override fun getRecyclerView(): RecyclerView = recyclerView

    protected open fun getRefreshLayout(): PullRefreshLayout = refresh_layout

    override fun initPullRefreshView() {
        getRefreshLayout().run {
            setDragRate(0.5f)
            setRefreshOffsetCalculator(QMUICenterGravityRefreshOffsetCalculator())
            setOnPullListener(this@BasePagerPullRefreshFragment)
            isEnabled = false
        }
    }

    override fun onMoveTarget(offset: Int) {

    }

    override fun onMoveRefreshView(offset: Int) {

    }

    override fun onFirstLoadSuccess(data: List<T>) {
        super.onFirstLoadSuccess(data)
        setPullLayoutEnabled(true)
    }

    override fun onRefreshSuccess(data: List<T>) {
        super.onRefreshSuccess(data)
        setPullLayoutEnabled(true)
    }

    override fun onRefreshError(errorMsg: String?) {
        super.onRefreshError(errorMsg)
        setPullLayoutEnabled(true)
    }

    protected open fun setPullLayoutEnabled(enabled: Boolean) {
        getRefreshLayout().run {
            isEnabled = enabled
            finishRefresh()
        }
    }

}