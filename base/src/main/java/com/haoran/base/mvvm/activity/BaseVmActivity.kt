package com.haoran.base.mvvm.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.haoran.base.mvvm.IBaseViewModel
import com.haoran.base.ext.getVmClazz
import com.haoran.base.mvvm.viewmodel.BaseViewModel

/**
 * className：BaseVmActivity
 * packageName：com.haoran.base.mvvm.activity
 * createTime：2021/5/21 11:11
 * author： haoran
 * descrioption：BaseVmActivity
 **/
abstract class BaseVmActivity<VM : BaseViewModel> : BaseQMUIActivity(), IBaseViewModel<VM> {

    val mViewModel: VM by lazy {
        createViewModel()
    }

    /**
     * ViewModel的位置
     */
    override fun getViewModelIndex(): Int = 0

    /**
     * 创建viewModel
     */
    override fun createViewModel(): VM = ViewModelProvider(this).get(
        getVmClazz(
            this,
            getViewModelIndex()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建观察者
        createObserver()
    }

    /**
     * 添加加载观察
     * @param viewModels Array<out BaseViewModel>
     */
    override fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInActivity(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInActivity(this) {
                hideLoading()
            }
        }
    }

}