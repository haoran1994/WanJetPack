package com.haoran.basecore.base.fragment

import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.R
import com.haoran.basecore.databinding.BaseFragmentIndexBinding
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * className：BaseTabIndexFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 13:44
 * author： haoran
 * descrioption：BaseTabIndexFragment
 **/
abstract class BaseTabIndexFragment<VM : BaseViewModel> : BaseTabFragment<VM, BaseFragmentIndexBinding>() {

    override fun showTopBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.base_fragment_index

    override fun getViewPager(): QMUIViewPager = mBinding.mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = mBinding.mTabSegment

    override fun getMagicIndicator(): MagicIndicator? = null

    override fun initTabSegment() {
        super.initTabSegment()
        mBinding.mTabSegment.mode = QMUITabSegment.MODE_FIXED
    }

}