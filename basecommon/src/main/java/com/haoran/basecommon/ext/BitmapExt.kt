package com.haoran.basecommon.ext

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import kotlin.math.roundToInt

/**
 * className：BitmapExt
 * packageName：com.haoran.basecommon.ext
 * createTime：2021/5/21 10:31
 * author： haoran
 * descrioption：BitmapExt
 **/
/**
 * 模糊效果
 * @param context
 * @param radius
 * @param scale
 */
fun Bitmap.blur(context: Context, radius:Float, scale:Float):Bitmap{
    val width = (width * scale).roundToInt()
    val height = (height * scale).roundToInt()
    return Bitmap.createScaledBitmap(this,width,height,false).apply {
        val renderScript = RenderScript.create(context)
        val input = Allocation.createFromBitmap(renderScript,this)
        val output = Allocation.createTyped(renderScript,input.type)
        ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript)).apply {
            setInput(input)
            setRadius(radius)
            forEach(output)
        }
        output.copyTo(this)
        renderScript.destroy()
    }
}