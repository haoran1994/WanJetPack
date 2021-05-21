package com.haoran.basecore.base.fragment

import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.R
import com.haoran.basecore.databinding.BaseTitleTabLayoutBinding
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * className：BaseTitleTabFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 13:45
 * author： haoran
 * descrioption：BaseTitleTabFragment
 **/
abstract class BaseTitleTabFragment<VM : BaseViewModel> : BaseTabFragment<VM, BaseTitleTabLayoutBinding>() {

    override fun getLayoutId(): Int = R.layout.base_title_tab_layout

    override fun getViewPager(): QMUIViewPager = mBinding.viewPager

    override fun getTabSegment(): QMUITabSegment? = null

    override fun getMagicIndicator(): MagicIndicator? = mBinding.indicator

}