package com.haoran.basecore.widget.indicator;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;

import com.haoran.basecore.R;
import com.qmuiteam.qmui.skin.IQMUISkinHandlerView;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.util.QMUIColorHelper;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


/**
 * @author The one
 * @date 2020/6/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class SkinPagerTitleView extends SimplePagerTitleView implements IQMUISkinHandlerView {

    private float mChangePercent = 0.5f;
    private boolean isSelect = false;

    public SkinPagerTitleView(Context context) {
        super(context);
        mSelectedColor = getSelectColor();
        mNormalColor = getNormalColor();
    }

    public int getSelectColor(){
        return QMUISkinHelper.getSkinColor(this, R.attr.app_skin_tab_indicator_select_color);
    }

    public int getNormalColor(){
        return QMUISkinHelper.getSkinColor(this, R.attr.app_skin_tab_indicator_normal_color);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        isSelect = true;
        updateTextColor();
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        isSelect = false;
        updateTextColor();
    }

    private void updateTextColor(){
        setTextColor(isSelect?mSelectedColor:mNormalColor);
    }

    @Override
    public void handle(@NonNull QMUISkinManager manager, int skinIndex, @NonNull Resources.Theme theme, @Nullable SimpleArrayMap<String, Integer> attrs) {
        mSelectedColor = getSelectColor();
        mNormalColor = getNormalColor();
        updateTextColor();
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        setTextColor(QMUIColorHelper.computeColor(mSelectedColor,mNormalColor,leavePercent));
//        if (leavePercent >= mChangePercent) {
//            setTextColor(mNormalColor);
//        } else {
//            setTextColor(mSelectedColor);
//        }
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        setTextColor(QMUIColorHelper.computeColor(mNormalColor,mSelectedColor,enterPercent));
//        if (enterPercent >= mChangePercent) {
//            setTextColor(mSelectedColor);
//        } else {
//            setTextColor(mNormalColor);
//        }
    }

}
