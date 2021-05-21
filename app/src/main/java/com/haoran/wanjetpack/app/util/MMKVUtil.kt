package com.haoran.wanjetpack.app.util

import com.tencent.mmkv.MMKV

/**
 * className：MMKVUtil
 * packageName：com.haoran.wanjetpack.app.util
 * createTime：2021/5/21 15:25
 * author： haoran
 * descrioption：MMKVUtil
 **/
object MMKVUtil {

    private fun getMMKV(id: String? = "the_base_mvvm"): MMKV = MMKV.mmkvWithID(id)

    fun putBoolean(key: String, value: Boolean) {
        getMMKV().encode(key, value)
    }

    fun getBoolean(key: String, default: Boolean): Boolean =
        getMMKV().decodeBool(key, default)

    fun getBoolean(key: String): Boolean =
        getBoolean(key, false)

    fun putString(key: String, value: String?) {
        getMMKV().encode(key, value)
    }

    fun getString(key: String, default: String?): String? =
        getMMKV().decodeString(key, default)

    fun getString(key: String): String? =
        getString(key, null)

    fun putInt(key: String, value: Int) {
        getMMKV().encode(key, value)
    }

    fun getInt(key: String, default: Int): Int =
        getMMKV().decodeInt(key, default)
}

