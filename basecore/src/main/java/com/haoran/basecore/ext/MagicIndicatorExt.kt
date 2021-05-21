package com.haoran.basecore.ext

import android.content.Context
import android.util.TypedValue
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager.widget.ViewPager
import com.haoran.basecommon.ext.dp2px
import com.haoran.basecommon.ext.getColor
import com.haoran.basecommon.ext.goneViews
import com.haoran.basecore.R
import com.haoran.basecore.data.entity.QMUITabBean
import com.haoran.basecore.widget.indicator.SkinLinePagerIndicator
import com.haoran.basecore.widget.indicator.SkinPagerTitleView
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder
import com.qmuiteam.qmui.skin.defaultAttr.QMUISkinSimpleDefaultAttrProvider
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView

/**
 * className：MagicIndicatorExt
 * packageName：com.haoran.basecore.ext
 * createTime：2021/5/21 13:53
 * author： haoran
 * descrioption：MagicIndicatorExt
 **/

fun getPagerTitleView(
    context: Context,
    index: Int,
    tabs: List<QMUITabBean>,
    viewPager: ViewPager
): IPagerTitleView {
    val badgePagerTitleView = BadgePagerTitleView(context)
    val simplePagerTitleView = SkinPagerTitleView(context)
    simplePagerTitleView.run {
        text = tabs[index].title
        setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            QMUIResHelper.getAttrDimen(context, R.attr.app_skin_tab_indicator_text_size).toFloat()
        )
        setOnClickListener { viewPager.currentItem = index }
    }

    val badgeButton = QMUIRoundButton(context, null, R.attr.qmui_tab_sign_count_view)
    val skinProvider = QMUISkinSimpleDefaultAttrProvider()
    skinProvider.setDefaultSkinAttr(
        QMUISkinValueBuilder.BACKGROUND, R.attr.qmui_skin_support_tab_sign_count_view_bg_color
    )
    skinProvider.setDefaultSkinAttr(
        QMUISkinValueBuilder.TEXT_COLOR, R.attr.qmui_skin_support_tab_sign_count_view_text_color
    )
    badgeButton.setTag(R.id.qmui_skin_default_attr_provider, skinProvider)
    goneViews(badgeButton)

    badgePagerTitleView.run {
        innerPagerTitleView = simplePagerTitleView
        badgeView = badgeButton
        isAutoCancelBadge = false
    }
    return badgePagerTitleView
}

fun getLinePagerIndicator(context: Context): IPagerIndicator {
    val indicator = SkinLinePagerIndicator(context)
    indicator.run {
        mode = LinePagerIndicator.MODE_EXACTLY
        lineHeight = dp2px(3).toFloat()
        lineWidth = dp2px(15).toFloat()
        roundRadius = dp2px(2).toFloat()
        startInterpolator = AccelerateInterpolator()
        endInterpolator = DecelerateInterpolator(2.0f)
    }
    return indicator
}

fun getWrapPagerIndicator(context: Context, fillColor: Int): WrapPagerIndicator {
    val indicator = WrapPagerIndicator(context)
    indicator.fillColor = getColor(context, fillColor)
    return indicator
}