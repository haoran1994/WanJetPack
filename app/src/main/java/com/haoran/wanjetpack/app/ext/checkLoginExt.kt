package com.haoran.wanjetpack.app.ext

import com.haoran.wanjetpack.app.util.CacheUtil.isLogin
import com.qmuiteam.qmui.arch.QMUIFragment
import rxhttp.wrapper.utils.CacheUtil

/**
 * className：checkLoginExt
 * packageName：com.haoran.wanjetpack.app.ext
 * createTime：2021/5/21 15:19
 * author： haoran
 * descrioption：checkLoginExt
 **/
fun QMUIFragment.checkLogin(isLoginAction: () -> Unit = {}) {
    if (CacheUtil.isLogin()) {
        isLoginAction.invoke()
    } else {
        startFragment(LoginRegisterFragment())
//        startActivity(Intent(activity, LoginRegisterActivity::class.java))
    }
}