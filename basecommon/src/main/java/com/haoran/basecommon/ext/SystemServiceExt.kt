package com.haoran.basecommon.ext

import android.app.ActivityManager
import android.content.Context

/**
 * className：SystemServiceExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:38
 * author： haoran
 * descrioption：SystemServiceExt
 **/
/**
 * 获取当前进程的名称，默认进程名称是包名
 */
val Context.currentProcessName: String?
    get() {
        val pid = android.os.Process.myPid()
        val mActivityManager = getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager
        for (appProcess in mActivityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return null
    }