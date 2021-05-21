package com.haoran.basecommon.ext

import java.util.*

/**
 * className：LangExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:36
 * author： haoran
 * descrioption：LangExt
 **/
/**
 * 规范化价格字符串显示的工具类
 * @return 保留两位小数的价格字符串
 */
fun Double.regularizePrice():String{
    return String.format(Locale.CHINESE,"%.2f",this)
}

/**
 * 规范化价格字符串显示的工具类
 * @return 保留两位小数的价格字符串
 */
fun Float.regularizePrice():String{
    return String.format(Locale.CHINESE,"%.2f",this)
}