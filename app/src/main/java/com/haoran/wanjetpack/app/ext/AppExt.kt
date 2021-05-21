package com.haoran.wanjetpack.app.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.chad.library.adapter.base.BaseQuickAdapter
import com.haoran.base.ext.qmui.showFailTipsDialog

/**
 * className：AppExt
 * packageName：com.haoran.wanjetpack.app.ext
 * createTime：2021/5/21 15:18
 * author： haoran
 * descrioption：AppExt
 **/
/**
 * 加入qq聊天群
 * https://qun.qq.com/join.html
 */
fun Context.joinQQGroup(key: String) {
    val intent = Intent()
    intent.data =
        Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D$key")
    // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        startActivity(intent)
    } catch (e: Exception) {
        // 未安装手Q或安装的版本不支持
        showFailTipsDialog("未安装手机QQ或安装的版本不支持")
    }
}

//设置适配器的列表动画
fun BaseQuickAdapter<*, *>.setAdapterAnimation(mode: Int?) {
    mode?.let {
        //等于0，关闭列表动画 否则开启
        if (it == 0) {
            animationEnable = false
        } else {
            animationEnable = true
            setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[it - 1])
        }
    }

}

/**
 * 是否为官方链接自动通过
 */
fun isShareAutoPass(url:String):Boolean{
    val filters = arrayOf("https://blog.csdn.net/", "https://juejin.cn/","https://www.jianshu.com/")
    filters.forEach {
        if(url.contains(it)){
            return true
        }
    }
    return false
}