package com.haoran.basecore.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import com.haoran.base.mvvm.fragment.BaseVmDbFragment
import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.ext.loadSirInit
import com.haoran.basecore.widget.loadsir.core.LoadService

/**
 * className：BaseCoreFragment
 * packageName：com.haoran.basecore.base.fragment
 * createTime：2021/5/21 11:49
 * author： haoran
 * descrioption：BaseCoreFragment
 **/
abstract class BaseCoreFragment<VM : BaseViewModel, DB : ViewDataBinding>: BaseVmDbFragment<VM, DB>() {

    /**
     * 界面状态管理者
     */
    var mLoadSir: LoadService<Any>?=null

    /**
     *  这里拿[getContentView]进行注册
     *  无论子类需不需要TopBar,都拿content层去注册
     * @param rootView View
     */
    override fun onViewCreated(rootView: View) {
        mLoadSir = loadSirInit(getContentView()) {
            onPageReLoad()
        }
        super.onViewCreated(rootView)
    }

    /**
     * 错误、空界面重新
     */
    protected open fun onPageReLoad() {}

    /**
     * 这里把这个方法实现了，子类需要的时候重写，免的每次都要去实现这个方法
     */
    override fun onLazyInit() {}

    override fun initData() {}

}