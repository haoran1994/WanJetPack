package com.haoran.base.mvvm

import androidx.annotation.NonNull

/**
 * className：IBaseDataBinding
 * packageName：com.haoran.base
 * createTime：2021/5/21 11:13
 * author： haoran
 * descrioption：IBaseDataBinding
 **/
interface IBaseDataBinding {

    /**
     * 视图绑定里ViewModel的ID
     * @return Int
     */
    fun getBindingVmId():Int

    /**
     * 视图绑定里Click的ID
     * @return Int
     */
    fun getBindingClickId(): Int

    /**
     * 视图绑定里的Click - 需实现TheClick
     * @return TheClick?
     */
    fun getBindingClick(): IClick?

    /**
     * 添加绑定
     * @param variableId Int 绑定的ID
     * @param any Any 绑定的内容
     */
    fun addBindingParams(@NonNull variableId: Int, @NonNull any: Any)

}
