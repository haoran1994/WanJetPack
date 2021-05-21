package com.haoran.basecore.base.adapter

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter

/**
 * className：TabFragmentAdapter
 * packageName：com.haoran.basecore.base.adapter
 * createTime：2021/5/21 11:47
 * author： haoran
 * descrioption：TabFragmentAdapter
 **/
class TabFragmentAdapter(fm: FragmentManager, private val mFragments : List<QMUIFragment>) :
    QMUIFragmentPagerAdapter(fm) {

    private var mChildCount = 0

    override fun getCount(): Int  = mFragments.size

    override fun getItemPosition(`object`: Any): Int {
        return if (mChildCount == 0) {
            PagerAdapter.POSITION_NONE
        } else super.getItemPosition(`object`)
    }

    override fun createFragment(position: Int): QMUIFragment  = mFragments[position]

    override fun notifyDataSetChanged() {
        mChildCount = count
        super.notifyDataSetChanged()
    }

}