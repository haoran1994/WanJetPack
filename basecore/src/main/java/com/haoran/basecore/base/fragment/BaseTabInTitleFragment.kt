package com.haoran.basecore.base.fragment

import android.view.View
import android.widget.RelativeLayout
import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecommon.ext.goneViews
import com.haoran.basecommon.ext.showViews
import com.haoran.basecore.R
import com.haoran.basecore.databinding.BaseTabInTitleLayoutBinding
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * className：BaseTabInTitleFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 13:45
 * author： haoran
 * descrioption：BaseTabInTitleFragment
 **/
abstract class BaseTabInTitleFragment<VM : BaseViewModel> :
    BaseTabFragment<VM, BaseTabInTitleLayoutBinding>() {

    private val mMagicIndicator: MagicIndicator by lazy {
        MagicIndicator(context).apply {
            layoutParams = RelativeLayout.LayoutParams(wrapContent, matchParent).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.base_tab_in_title_layout

    override fun showTopBar(): Boolean = true

    override fun initView(rootView: View) {
        super.initView(rootView)
        initTopBar()
        goneViews(getTopBar())
    }

    protected open fun initTopBar() {
        getTopBar()?.run {
            setCenterView(mMagicIndicator)
            goneViews(this)
        }
    }

    override fun startInit() {
        showViews(getTopBar())
        super.startInit()
    }

    override fun getViewPager(): QMUIViewPager = mBinding.mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = null

    override fun getMagicIndicator(): MagicIndicator? = mMagicIndicator
}