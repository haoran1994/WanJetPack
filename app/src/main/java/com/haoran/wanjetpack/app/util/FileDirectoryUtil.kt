package com.haoran.wanjetpack.app.util

import android.os.Environment
import java.io.File

/**
 * className：FileDirectoryUtil
 * packageName：com.haoran.wanjetpack.app.util
 * createTime：2021/5/21 15:24
 * author： haoran
 * descrioption：FileDirectoryUtil
 **/
object FileDirectoryUtil {
    /**
     * 根目录
     */
    private var INDEX = "TheBase"

    /**
     * 下载目录
     */
    private var DOWNLOAD = "Download"

    /**
     * 图片目录
     */
    private var PICTURE = "Picture"

    /**
     * 视频目录
     */
    private var VIDEO = "Video"

    /**
     * 缓存目录
     */
    private var CACHE = "Cache"

    /**
     * 压缩目录
     */
    private const val IMAGE_COMPRESS = "ImageCompress"

    /**
     * 更新包下载地址
     */
    private var UPDATE_APK_FILE_NAME = "APK"

    /**
     * 省区县json
     */
    var PROVINCE_JSON = "province.json"

    /**
     * 补丁
     */
    private var PATCH = "Patch"

    /**
     * 补丁名称
     */
    var PATCH_NAME = "patch"
    private var mBuilder: Builder? = null
    fun getBuilder(): Builder? {
        if (null == mBuilder) {
            mBuilder = Builder()
        }
        return mBuilder
    }

    /**
     * 获取根目录
     * @return
     */
    fun getIndexPath(): String {
        return checkFileExists(
            Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator.toString() + INDEX
        )
    }

    /**
     * 获取下载目录
     * @return
     */
    fun getDownloadPath(): String {
        return checkFileExists(getIndexPath() + File.separator.toString() + DOWNLOAD)
    }

    /**
     * 获取更新包路径
     * @return
     */
    fun getUpdateAPKDownloadPath(): String {
        return getDownloadPath() + File.separator.toString() + UPDATE_APK_FILE_NAME
    }

    /**
     * 获取补丁文件路径
     * @return
     */
    fun getPatchFilePath(): String {
        return getPatchPath() + File.separator.toString() + PATCH_NAME
    }

    /**
     * 获取补丁路径
     * @return
     */
    fun getPatchPath(): String {
        return getPath(PATCH)
    }

    /**
     * 获取图片目录
     * @return
     */
    fun getPicturePath(): String {
        return getPath(PICTURE)
    }

    /**
     * 获取视频目录
     * @return
     */
    fun getVideoPath(): String {
        return getPath(VIDEO)
    }

    /**
     * 获取缓存目录
     * @return
     */
    fun getCachePath(): String {
        return getPath(CACHE)
    }

    /**
     * 获取缓存子文件目录
     * @return
     */
    fun getCacheChildFilePath(name: String): String {
        return checkFileExists(getCachePath() + File.separator.toString() + name)
    }

    /**
     * 获取省市区的json文件地址
     * @return
     */
    fun getProvinceJsonPath(): String {
        return getCacheChildFilePath(PROVINCE_JSON)
    }

    /**
     * 图片压缩目录
     * @return
     */
    fun getImageCompressPath(): String {
        return getCacheChildFilePath(IMAGE_COMPRESS)
    }

    private fun getPath(path: String): String {
        return checkFileExists(getIndexPath() + File.separator.toString() + path)
    }

    private fun checkFileExists(path: String): String {
        val file = File(path)
        if (!file.exists()) {
            file.mkdir()
        }
        return path
    }

    class Builder {
        /**
         * 根目录
         */
        var index = "The one"

        /**
         * 下载目录
         */
        var download = "Download"

        /**
         * 图片目录
         */
        var picture = "Picture"

        /**
         * 视频目录
         */
        var video = "Video"

        /**
         * 缓存目录
         */
        var cache = "Cache"

        /**
         * 补丁目录
         */
        var patch = "Patch"

        /**
         * 更新包下载路径名称
         */
        var updateApkFileName = "Apk"

        fun setIndex(mIndex: String): Builder {
            index = mIndex
            return this
        }

        fun setDownload(mDownload: String): Builder {
            download = mDownload
            return this
        }

        fun setPicture(mPicture: String): Builder {
            picture = mPicture
            return this
        }

        fun setVideo(mVideo: String): Builder {
            video = mVideo
            return this
        }

        fun setCache(mCache: String): Builder {
            cache = mCache
            return this
        }

        fun setUpdateApkFileName(mUpdateApkName: String): Builder {
            updateApkFileName = mUpdateApkName
            return this
        }

        fun build() {
            INDEX = index
            DOWNLOAD = download
            PICTURE = picture
            VIDEO = video
            CACHE = cache
            PATCH = patch
            UPDATE_APK_FILE_NAME = updateApkFileName
        }
    }
}