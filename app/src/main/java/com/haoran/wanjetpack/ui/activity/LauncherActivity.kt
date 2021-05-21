package com.haoran.wanjetpack.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.haoran.base.mvvm.viewmodel.BaseViewModel
import com.haoran.basecore.base.activity.BaseCoreActivity
import com.haoran.wanjetpack.R
import com.haoran.wanjetpack.app.util.CacheUtil
import com.haoran.wanjetpack.app.util.RxHttpManager
import com.haoran.wanjetpack.app.widget.TypeTextView
import com.haoran.wanjetpack.databinding.ActivityLauncherBinding
import com.qmuiteam.qmui.arch.QMUILatestVisit

class LauncherActivity : BaseCoreActivity<BaseViewModel,ActivityLauncherBinding>(), TypeTextView.OnTypeViewListener {
    override fun getLayoutId(): Int  = R.layout.activity_launcher
    override fun showTopBar(): Boolean = false
    private val mTypes: Array<String> by lazy {
        resources.getStringArray(R.array.launcher)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        super.onCreate(savedInstanceState)
    }
    override fun initView(root: View) {
        // 再次安装后请求时要清除，不然会读取到
        if(CacheUtil.isFirst()){
            RxHttpManager.clearCache()
            CacheUtil.isEnterApp()
        }
        val tips = mTypes[(mTypes.indices).random()]
        mBinding.tvType.run {
            if (CacheUtil.isOpenLauncherText()) {
                setOnTypeViewListener(this@LauncherActivity)
                start(tips, 220)
            } else {
                text = tips
                startToMain(3000)
            }
        }    }

    private fun startToMain(delayTime: Long) {
        getContentView().postDelayed({
            var intent = QMUILatestVisit.intentOfLatestVisit(this)
            if (intent == null) {
                intent = Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, delayTime)
    }

    override fun createObserver() {
        TODO("Not yet implemented")
    }

    override fun onTypeStart() {
        TODO("Not yet implemented")
    }

    override fun onTypeOver() {
        startToMain(800)
    }
}