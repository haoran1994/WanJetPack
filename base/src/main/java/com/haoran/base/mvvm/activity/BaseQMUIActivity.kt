package com.haoran.base.mvvm.activity

import android.os.Bundle
import android.view.View
import com.haoran.base.mvvm.IBaseQMUI
import com.haoran.base.R
import com.haoran.base.ext.createTopBar
import com.haoran.base.ext.createView
import com.haoran.base.ext.qmui.hideLoadingDialog
import com.haoran.base.ext.qmui.showLoadingDialog
import com.haoran.base.ext.qmui.updateStatusBarMode
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.widget.QMUITopBarLayout

/**
 * className：BaseQMUIActivity
 * packageName：com.haoran.base.mvvm.activity
 * createTime：2021/5/21 11:11
 * author： haoran
 * descrioption：BaseQMUIActivity
 **/
abstract class BaseQMUIActivity : QMUIActivity(), IBaseQMUI {

    protected val TAG: String = this.javaClass.simpleName

    /**
     * 内容层
     */
    private val mContent: View by lazy {
        createContentView()
    }

    /**
     * 内容层
     */
    private val mTopBar: QMUITopBarLayout? by lazy {
        createTopBar(this)
    }

    /**
     * 提供一个方法供子类获取TopBar
     */
    override fun getTopBar(): QMUITopBarLayout? = mTopBar

    /**
     * 是否需要TopBar(默认为根Fragment才需要)
     * 子类重写此方法进行修改
     */
    override fun showTopBar(): Boolean = true

    /**
     * 这个方法是为了给BaseVmDbActivity重写绑定视图的
     * 所以仅供此包类使用
     */
    internal open fun createContentView(): View = layoutInflater.inflate(getLayoutId(), null)

    /**
     * 提供一个方法子类获取内容层
     * @return View
     */
    override fun getContentView(): View = mContent

    /**
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     * @remark 根据自己APP的配色，给定一个全局的默认模式。
     *         建议用TopBar的背景颜色做判断。或者在自己的BaseFragment里提供一个全局默认的模式。
     */
    override fun isStatusBarLightMode(): Boolean = true

    /**
     * @return 是否要进行对状态栏的处理
     */
    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.scale_enter, R.anim.slide_still)
        updateStatusBarMode(isStatusBarLightMode())
        createView(this, translucentFull()).let {
            setContentView(it)
            initView(it)
        }
    }

    /**
     * 显示加载框
     * @param msg String? 提示语
     * @remark 这了提供了默认的加载效果，如果需要更改，重写此方法以及[hideLoading]
     */
    override fun showLoading(msg: String?) {
        showLoadingDialog(msg)
    }

    /**
     * 隐藏加载框
     */
    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_still, R.anim.scale_exit)
    }

}