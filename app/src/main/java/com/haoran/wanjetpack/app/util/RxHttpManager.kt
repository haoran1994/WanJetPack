package com.haoran.wanjetpack.app.util

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.haoran.base.mvvm.BaseApplication
import com.haoran.wanjetpack.app.util.FileDirectoryUtil.getCachePath
import com.zhy.http.okhttp.OkHttpUtils
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.ssl.HttpsUtils
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession


/**
 * className：RxHttpManager
 * packageName：com.haoran.wanjetpack.app.util
 * createTime：2021/5/21 15:26
 * author： haoran
 * descrioption：RxHttpManager
 **/
object RxHttpManager {
    private var cookieJar: PersistentCookieJar? = null
    private var mCacheFile: File? = null
    fun getCookieJar(): PersistentCookieJar? {
        if (null == cookieJar) {
            cookieJar = PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(BaseApplication.app)
            )
        }
        return cookieJar
    }

    fun getHttpClient(builder: HttpBuilder): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        if (builder.isNeedCookie) httpBuilder.cookieJar(getCookieJar())
        httpBuilder.connectTimeout(builder.outTime.toLong(), TimeUnit.SECONDS)
            .readTimeout(builder.readTime.toLong(), TimeUnit.SECONDS)
            .writeTimeout(builder.writeTime.toLong(), TimeUnit.SECONDS)
            .sslSocketFactory(
                HttpsUtils.getSslSocketFactory().sSLSocketFactory,
                HttpsUtils.getSslSocketFactory().trustManager
            ) //添加信任证书
            .hostnameVerifier { hostname: String?, session: SSLSession? -> true } //忽略host验证;
        val client = httpBuilder.build()
        OkHttpUtils.initClient(client)
        initCacheMode(builder)
        return client
    }

    private fun initCacheMode(builder: HttpBuilder) {
        mCacheFile = File(builder.cacheFilePath, builder.cacheFileName)
        RxHttpPlugins.setCache(
            mCacheFile,
            builder.cacheMaxSize,
            builder.cacheMode,
            builder.cacheValidTime
        )
    }

    fun clearCache() {
        if (null != mCacheFile) {
            mCacheFile!!.deleteOnExit()
        }
    }

    class HttpBuilder {
        var isNeedCookie = false
            private set
        var cookieFileName = "RxHttpCookie"
            private set
        var cookieFilePath = getCachePath()
            private set
        var cacheFileName = "RxHttCache"
            private set
        var cacheFilePath = getCachePath()
            private set
        var cacheMaxSize = (1000 * 100).toLong()
            private set
        var cacheMode = CacheMode.ONLY_NETWORK
            private set
        var cacheValidTime: Long = -1
            private set
        var outTime = 10
            private set
        var readTime = 10
            private set
        var writeTime = 10
            private set

        fun setNeedCookie(needCookie: Boolean): HttpBuilder {
            isNeedCookie = needCookie
            return this
        }

        fun setCookieFileName(cookieFileName: String): HttpBuilder {
            this.cookieFileName = cookieFileName
            return this
        }

        fun setCookieFilePath(cookieFilePath: String): HttpBuilder {
            this.cookieFilePath = cookieFilePath
            return this
        }

        fun setCacheFileName(cacheFileName: String): HttpBuilder {
            this.cacheFileName = cacheFileName
            return this
        }

        fun setCacheFilePath(cacheFilePath: String): HttpBuilder {
            this.cacheFilePath = cacheFilePath
            return this
        }

        fun setCacheMaxSize(cacheMaxSize: Long): HttpBuilder {
            this.cacheMaxSize = cacheMaxSize
            return this
        }

        fun setCacheMode(cacheMode: CacheMode): HttpBuilder {
            this.cacheMode = cacheMode
            return this
        }

        fun setCacheValidTime(cacheValidTime: Long): HttpBuilder {
            this.cacheValidTime = cacheValidTime
            return this
        }

        fun setOutTime(outTime: Int): HttpBuilder {
            this.outTime = outTime
            return this
        }

        fun setReadTime(readTime: Int): HttpBuilder {
            this.readTime = readTime
            return this
        }

        fun setWriteTime(writeTime: Int): HttpBuilder {
            this.writeTime = writeTime
            return this
        }
    }
}