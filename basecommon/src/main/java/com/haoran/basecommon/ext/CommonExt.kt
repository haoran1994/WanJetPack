package com.haoran.basecommon.ext

/**
 * className：CommonExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:33
 * author： haoran
 * descrioption：CommonExt
 **/
/**
 * 判断是否为空 并传入相关操作
 */
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit = {}) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}