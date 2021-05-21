package com.haoran.basecore

import android.app.Application
import com.haoran.base.mvvm.BaseApplication
import com.haoran.basecommon.ext.LogInit
import com.haoran.basecore.ext.initLoadSir

/**
 * className：CoreApplication
 * packageName：com.haoran.basecore
 * createTime：2021/5/21 13:49
 * author： haoran
 * descrioption：CoreApplication
 **/
abstract class CoreApplication : BaseApplication() {

    override fun init(application: Application) {
        super.init(application)
        LogInit(DEBUG)

        initLoadSir()

        // TODO 以下内容还在测试...
//        val processName = currentProcessName
//        if (processName == packageName) {
//            // 主进程初始化
//            onMainProcessInit()
//        } else {
//
//            // 其他进程初始化
//            processName?.let { onOtherProcessInit(it) }
//        }
    }

    open fun onMainProcessInit() {

    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    open fun onOtherProcessInit(processName: String) {}

}