package com.haoran.basecore.base.adapter

import android.util.SparseArray
import androidx.annotation.NonNull
import androidx.core.util.forEach
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.haoran.base.ext.addParams
import com.haoran.basecore.BR
/**
 * className：TheBaseQuickAdapter
 * packageName：com.haoran.basecore.base.adapter
 * createTime：2021/5/21 11:48
 * author： haoran
 * descrioption：TheBaseQuickAdapter
 **/
abstract class TheBaseQuickAdapter<T, BD : ViewDataBinding>(layout: Int) :
    BaseQuickAdapter<T, BaseDataBindingHolder<BD>>(
        layout
    ), LoadMoreModule {

    private val bindingParams: SparseArray<Any> = SparseArray()

    protected fun addBindingParams(
        @NonNull variableId: Int,
        @NonNull any: Any
    ) {
        bindingParams.addParams(variableId,any)
    }

    override fun convert(holder: BaseDataBindingHolder<BD>, item: T) {
        holder.dataBinding?.run {
            this.setVariable(BR.item, item)
            bindingParams.forEach { key, any ->
                setVariable(key, any)
            }
        }
    }

}