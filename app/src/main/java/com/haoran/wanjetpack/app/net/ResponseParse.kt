package com.haoran.wanjetpack.app.net

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.io.IOException
import java.lang.reflect.Type
import kotlin.jvm.Throws

/**
 * className：ResponseParse
 * packageName：com.haoran.wanjetpack.app.net
 * createTime：2021/5/21 15:22
 * author： haoran
 * descrioption：ResponseParse
 **/
@Parser(name = "Response")
open class ResponseParse<T> : AbstractParser<T?> {

    protected constructor() : super()

    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T? {
        val type: Type = ParameterizedTypeImpl[Response::class.java, mType]
        val data: Response<T> = response.convert(type)
        var t = data.data
        if (t == null && mType === String::class.java) {
            @Suppress("UNCHECKED_CAST")
            t = data.errorMsg as T
        }
        if (data.errorCode != 0) {
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t
    }

}