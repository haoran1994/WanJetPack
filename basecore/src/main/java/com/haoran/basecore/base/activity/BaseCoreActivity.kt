package com.haoran.basecore.base.activity

import android.view.View
import androidx.databinding.ViewDataBinding
import com.haoran.base.mvvm.activity.BaseVmDbActivity
import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.ext.loadSirInit
import com.haoran.basecore.widget.loadsir.core.LoadService

/**
 * className：BaseCoreActivity
 * packageName：com.haoran.basecore.base.activity
 * createTime：2021/5/21 11:47
 * author： haoran
 * descrioption：BaseCoreActivity
 **/
abstract class BaseCoreActivity<VM : BaseViewModel, DB : ViewDataBinding>: BaseVmDbActivity<VM, DB>() {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    override fun setContentView(view: View?) {
        super.setContentView(view)
        mLoadSir = loadSirInit(getContentView()) {
            onPageReLoad()
        }
    }

    /**
     * 错误、空界面重新
     */
    protected open fun onPageReLoad() {}

    override fun initData() {}


}