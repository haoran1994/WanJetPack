package com.haoran.base.mvvm

import android.view.View
import com.qmuiteam.qmui.widget.QMUITopBarLayout

/**
 * className：IBaseQMUI
 * packageName：com.haoran.base
 * createTime：2021/5/21 11:13
 * author： haoran
 * descrioption：基类相关
 **/
interface IBaseQMUI {

    /**
     * 获取布局
     */
    fun getLayoutId(): Int

    /**
     * 提供一个方法供子类获取TopBar
     */
    fun getTopBar(): QMUITopBarLayout?

    /**
     * 是否需要TopBar
     */
    fun showTopBar(): Boolean

    /**
     * 获取内容层
     * @return View
     */
    fun getContentView(): View

    /**
     * 布局初始化
     * @param root View
     */
    fun initView(root: View)

    /**
     * 显示加载框
     * @param msg String?
     */
    fun showLoading(msg:String?)

    /**
     * 隐藏加载框
     */
    fun hideLoading()

    /**
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     */
    fun isStatusBarLightMode():Boolean

    /**
     * @return 是否要进行对状态栏的处理
     */
    fun isNeedChangeStatusBarMode():Boolean

}