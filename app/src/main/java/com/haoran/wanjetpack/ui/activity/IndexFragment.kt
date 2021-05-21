package com.haoran.wanjetpack.ui.activity

import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.base.fragment.BaseTabIndexFragment
import com.haoran.basecore.data.entity.QMUITabBean
import com.qmuiteam.qmui.arch.QMUIFragment

/**
 * className：IndexFragment
 * packageName：com.haoran.wanjetpack.ui.activity
 * createTime：2021/5/21 17:47
 * author： haoran
 * descrioption：IndexFragment
 **/
class IndexFragment : BaseTabIndexFragment<BaseViewModel>() {
    override fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    ) {

    }
}