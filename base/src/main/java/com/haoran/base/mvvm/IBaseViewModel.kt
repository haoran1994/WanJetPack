package com.haoran.base.mvvm

import com.haoran.base.mvvm.viewmodel.BaseViewModel

/**
 * className：IBaseViewModel
 * packageName：com.haoran.base
 * createTime：2021/5/21 11:13
 * author： haoran
 * descrioption：IBaseViewModel
 **/
interface IBaseViewModel<VM:BaseViewModel> {

    /**
     * ViewMode在泛型中的的位置
     */
    fun getViewModelIndex(): Int

    /**
     * 创建ViewModel
     * @return VM
     */
    fun createViewModel(): VM

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 创建观察者
     */
    fun createObserver()

    /**
     * BaseViewModel添加Loading观察
     * @param viewModels Array<out BaseViewModel>
     */
    fun addLoadingObserve(vararg viewModels: BaseViewModel)

}
