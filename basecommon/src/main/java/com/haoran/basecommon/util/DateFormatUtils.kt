package com.haoran.basecommon.util

import android.content.Context
import android.content.res.Resources

import android.text.TextUtils
import android.text.format.DateFormat
import android.text.format.Time
import com.haoran.basecommon.R


/**
 * className：DateFormatUtils
 * packageName：com.haoran.basecommon.util
 * createTime：2021/5/21 10:40
 * author： haoran
 * descrioption：DateFormatUtils
 **/
/**
 * 日期时间工具类
 * 来自Flyme
 */
object DateFormatUtils {
    /**
     * 普通列表：
     * 当天："时间"
     * 本周："周几"
     * 本年：“月/日”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_NORMAL = 0

    /**
     * 短信详情列表：
     * 当天："时间"
     * 本周："周几 时间"
     * 本年：“月/日 时间”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_MMS = 1

    /**
     * 邮件详情列表
     * 本年：“周几 月/日 时间”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_EMAIL = 2

    /**
     * 录音机 备忘录列表
     * 当天：“时间”
     * 本年：“月/日 时间”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_RECORDER = 3

    /**
     * 录音机 通话录音列表
     * 当天：“时间”
     * 本年：“月/日”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_RECORDER_PHONE = 4

    /**
     * 通话记录 列表需求
     * 本年：“月/日 时间”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_CALL_LOGS = 5

    /**
     * 个人足迹 朋友需求
     * 当天：（时差在1小时内）mm分钟前
     * （时差在1小时外）mm小时前
     * 昨天：“昨天”
     * 本年：“月/日”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_PERSONAL_FOOTPRINT = 6

    /**
     * 版本日期 应用中心需求
     * 本年：“月/日”
     * 往年：“年/月/日”
     */
    const val FORMAT_TYPE_APP_VERSIONS = 7

    /**
     * 日历 桌面小工具
     * 本年：“月/日”
     * 往年：“年/月”
     */
    const val FORMAT_TYPE_CALENDAR_APPWIDGET = 8

    /**
     * 联系人生日
     * “年/月/日”
     */
    const val FORMAT_TYPE_CONTACTS_BIRTHDAY_YMD = 9

    /**
     * 联系人生日
     * “月/日”
     */
    const val FORMAT_TYPE_CONTACTS_BIRTHDAY_MD = 10

    /**
     * 通话记录 列表需求
     * 本年：“月/日; 时间”
     * 往年：“年/月/日;时间”
     */
    const val FORMAT_TYPE_CALL_LOGS_NEW = 11
    private const val MILLISECONDS_OF_HOUR = 60 * 60 * 1000

    @Deprecated(
        """use
	              {@link #formatTimeStampString(Context, long, int, boolean)}"""
    )
    fun formatTimeStampString(
        context: Context, `when`: Long,
        fullFormat: Boolean
    ): String? {
        return formatTimeStampString(
            context, `when`, FORMAT_TYPE_NORMAL,
            fullFormat
        )
    }

    /**
     * @param type
     * 日期类型
     *
     *  * [.FORMAT_TYPE_NORMAL]
     *  * [.FORMAT_TYPE_MMS]
     *  * [.FORMAT_TYPE_EMAIL]
     *  * [.FORMAT_TYPE_RECORDER]
     *
     */
    @Deprecated(
        """use {@link #formatTimeStampString(Context, long, int)}
	  """
    )
    fun formatTimeStampString(
        context: Context, `when`: Long,
        type: Int, hasTime: Boolean
    ): String? {
        return formatTimeStampString(context, `when`, type)
    }

    private var NowTimeLast: Time? = null
    private var ThenTimeLast: Time? = null
    private var NowMillisLast: Long = 0
    private var FormatTypeLast = -1
    private var FormatResultLast: String? = null

    /**
     * @param context
     * @param when
     * 毫秒时间数
     * @param type
     * the type of date time [.FORMAT_TYPE_NORMAL] <br></br>
     * [.FORMAT_TYPE_MMS] <br></br>
     * [.FORMAT_TYPE_EMAIL] <br></br>
     * [.FORMAT_TYPE_RECORDER] <br></br>
     * [.FORMAT_TYPE_RECORDER_PHONE] <br></br>
     * [.FORMAT_TYPE_CALL_LOGS] <br></br>
     * @return 日期字符串
     */
    fun formatTimeStampString(
        context: Context, `when`: Long,
        type: Int
    ): String? {
        val then = Time()
        then.set(`when`)
        var now: Time? = null
        val nowmillis = System.currentTimeMillis()
        val is24: Boolean = DateFormat.is24HourFormat(context)
        val sameType = type == FormatTypeLast
        FormatTypeLast = type
        var sameNowDay = false
        sameNowDay = if (NowTimeLast == null) {
            false
        } else {
            if (nowmillis >= NowMillisLast
                && nowmillis < NowMillisLast + 24 * 60 * 60 * 1000
            ) {
                true
            } else {
                false
            }
        }
        if (!sameNowDay) {
            now = Time()
            now.set(nowmillis)
            NowTimeLast = now
            NowMillisLast = (nowmillis
                    - (now.hour * 60 * 60 * 1000 + now.minute * 60 * 1000 + now.second * 1000))
        } else {
            now = NowTimeLast
        }
        var sameWhenDay = false
        if (ThenTimeLast != null) {
            sameWhenDay = ThenTimeLast!!.year === then.year && ThenTimeLast!!.yearDay === then.yearDay
        }
        var sameWhenMonth = false
        if (ThenTimeLast != null) {
            sameWhenMonth = ThenTimeLast!!.year === then.year && ThenTimeLast!!.month === then.month
        }
        ThenTimeLast = then
        val weekStart: Int = now!!.yearDay - now.weekDay
        val isBeforeYear: Boolean = then.year <= now.year
        val isThisYear = (now.year === then.year
                && then.yearDay <= now.yearDay)
        val isToday = isThisYear && then.yearDay === now.yearDay
        val isYesterday = isThisYear && then.yearDay === now.yearDay - 1
        val isThisWeek = isThisYear && then.yearDay >= weekStart && then.yearDay < now.yearDay
        val resources: Resources = context.getResources()
        var currentTime = ""
        var currentDay: String? = ""
        when (type) {
            FORMAT_TYPE_NORMAL -> return if (isToday) {
                if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute)
                    )
                } else {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute_12)
                    )
                }
            } else if (isThisWeek) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_week)
                )
                FormatResultLast
            } else if (isThisYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_month_day)
                )
                FormatResultLast
            } else if (isBeforeYear) { // past
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            }
            FORMAT_TYPE_MMS -> return if (isToday) {
                if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute)
                    )
                } else {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute_12)
                    )
                }
            } else if (isThisWeek) {
                if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_week_hour_minute)
                    )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_week_hour_minute_12)
                        )
                }
            } else if (isThisYear) {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute_12)
                        )
                }
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute_12)
                        )
                }
            }
            FORMAT_TYPE_EMAIL -> return if (isThisYear) {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_week_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_week_month_day_hour_minute_12)
                        )
                }
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                then
                    .format(
                        resources
                            .getString(R.string.mc_pattern_year_month_day_hour_minute)
                    )
            }
            FORMAT_TYPE_RECORDER -> return if (isToday) {
                if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute)
                    )
                } else {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute_12)
                    )
                }
            } else if (isThisYear) {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute_12)
                        )
                }
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute_12)
                        )
                }
            }
            FORMAT_TYPE_RECORDER_PHONE -> return if (isToday) {
                if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute)
                    )
                } else {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute_12)
                    )
                }
            } else if (isThisYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_month_day)
                )
                FormatResultLast
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            }
            FORMAT_TYPE_CALL_LOGS -> return if (isThisYear) {
                if (is24) {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute)
                        )
                } else {
                    then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_month_day_hour_minute_12)
                        )
                }
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                if (is24) {
                    FormatResultLast = then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute)
                        )
                } else {
                    FormatResultLast = then
                        .format(
                            resources
                                .getString(R.string.mc_pattern_year_month_day_hour_minute_12)
                        )
                }
                FormatResultLast
            }
            FORMAT_TYPE_PERSONAL_FOOTPRINT -> return if (isToday) {
                val offsetMilliSecounds = nowmillis - `when`
                val returnValue: Int
                if (offsetMilliSecounds >= MILLISECONDS_OF_HOUR) {
                    returnValue = offsetMilliSecounds.toInt() / 60 / 60 / 1000
                    if (returnValue == 1) {
                        resources
                            .getString(R.string.mc_pattern_a_hour_before)
                    } else {
                        resources.getString(
                            R.string.mc_pattern_hour_before
                        ).replace(",", returnValue.toString())
                    }
                } else {
                    returnValue = offsetMilliSecounds.toInt() / 60 / 1000
                    if (returnValue <= 1) {
                        resources
                            .getString(R.string.mc_pattern_a_minute_before)
                    } else {
                        resources.getString(
                            R.string.mc_pattern_minute_before
                        ).replace(",", returnValue.toString())
                    }
                }
            } else if (isYesterday) {
                resources.getString(R.string.mc_pattern_yesterday)
            } else if (isThisYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_month_day)
                )
                FormatResultLast
            } else if (isBeforeYear) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            } else {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                FormatResultLast
            }
            FORMAT_TYPE_APP_VERSIONS -> {
                if (sameWhenDay && sameType && !TextUtils.isEmpty(FormatResultLast)) {
                    return FormatResultLast
                }
                return if (isThisYear) {
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_month_day)
                    )
                    FormatResultLast
                } else {
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_year_month_day)
                    )
                    FormatResultLast
                }
            }
            FORMAT_TYPE_CALENDAR_APPWIDGET -> return if (now.year === then.year) {
                if (sameWhenDay && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_month_day)
                )
                FormatResultLast
            } else {
                if (sameWhenMonth && sameType
                    && !TextUtils.isEmpty(FormatResultLast)
                ) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month)
                )
                FormatResultLast
            }
            FORMAT_TYPE_CONTACTS_BIRTHDAY_YMD -> {
                if (sameWhenDay && sameType && !TextUtils.isEmpty(FormatResultLast)) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_year_month_day)
                )
                return FormatResultLast
            }
            FORMAT_TYPE_CONTACTS_BIRTHDAY_MD -> {
                if (sameWhenDay && sameType && !TextUtils.isEmpty(FormatResultLast)) {
                    return FormatResultLast
                }
                FormatResultLast = then.format(
                    resources
                        .getString(R.string.mc_pattern_month_day)
                )
                return FormatResultLast
            }
            FORMAT_TYPE_CALL_LOGS_NEW -> {
                currentTime = if (is24) {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute)
                    )
                } else {
                    then.format(
                        resources
                            .getString(R.string.mc_pattern_hour_minute_12)
                    )
                }
                if (isToday) {
                    currentDay = resources.getString(R.string.mc_pattern_today)
                } else if (isThisWeek) {
                    if (sameWhenDay && sameType
                        && !TextUtils.isEmpty(FormatResultLast)
                    ) {
                        currentDay = FormatResultLast
                    }
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_week)
                    )
                    currentDay = FormatResultLast
                } else if (isThisYear) {
                    if (sameWhenDay && sameType
                        && !TextUtils.isEmpty(FormatResultLast)
                    ) {
                        currentDay = FormatResultLast
                    }
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_month_day)
                    )
                    currentDay = FormatResultLast
                } else if (isBeforeYear) {
                    if (sameWhenDay && sameType
                        && !TextUtils.isEmpty(FormatResultLast)
                    ) {
                        currentDay = FormatResultLast
                    }
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_year_month_day)
                    )
                    currentDay = FormatResultLast
                } else {
                    if (sameWhenDay && sameType
                        && !TextUtils.isEmpty(FormatResultLast)
                    ) {
                        currentDay = FormatResultLast
                    }
                    FormatResultLast = then.format(
                        resources
                            .getString(R.string.mc_pattern_year_month_day)
                    )
                    currentDay = FormatResultLast
                }
                return "$currentDay;$currentTime"
            }
        }
        return null
    }
}