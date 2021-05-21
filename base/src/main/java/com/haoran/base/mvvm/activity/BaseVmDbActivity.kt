package com.haoran.base.mvvm.activity

import android.util.SparseArray
import android.view.View
import androidx.annotation.NonNull
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.haoran.base.mvvm.IBaseDataBinding
import com.haoran.base.mvvm.IClick
import com.haoran.base.ext.addParams
import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.base.BR

/**
 * className：BaseVmDbActivity
 * packageName：com.haoran.base.mvvm.activity
 * createTime：2021/5/21 11:12
 * author： haoran
 * descrioption：BaseVmDbActivity
 **/
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>(),
    IBaseDataBinding {

    lateinit var mBinding: DB

    private val mBindingParams: SparseArray<Any> = SparseArray()

    /**
     * @return Int  视图里绑定的ViewModel ID
     * @remark 如果使用默认值，则都要命名为 vm ,如果不一致,重写此方法返回
     */
    override fun getBindingVmId(): Int = BR.vm

    /**
     *
     * @return Int 视图里绑定的点击事件 ID
     */
    override fun getBindingClickId(): Int = BR.click

    /**
     * 视图里绑定的点击事件 - 需实现[IClick]
     * @return Int
     */
    override fun getBindingClick(): IClick? = null

    /**
     * 添加绑定
     * @param variableId Int 绑定的ID
     * @param any Any 绑定的内容
     * @remark kotlin方法默认都是final类型，子类不能重写此方法。
     */
    override fun addBindingParams(
        @NonNull variableId: Int,
        @NonNull any: Any
    ) {
        mBindingParams.addParams(variableId, any)
    }

    override fun createContentView(): View {
        mBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
        mBinding.run {
            lifecycleOwner = this@BaseVmDbActivity
            setVariable(getBindingVmId(), mViewModel)
            getBindingClick()?.let {
                setVariable(getBindingClickId(), it)
            }
            mBindingParams.forEach { key, any ->
                setVariable(key, any)
            }
        }
        return mBinding.root
    }

}