package com.haoran.wanjetpack.app.widget.banner

import android.view.View
import com.haoran.wanjetpack.R
import com.zhpan.bannerview.BaseBannerAdapter

/**
 * className：HomeBannerAdapter
 * packageName：com.haoran.wanjetpack.app.widget.banner
 * createTime：2021/5/21 15:54
 * author： haoran
 * descrioption：HomeBannerAdapter
 **/
class HomeBannerAdapter : BaseBannerAdapter<BannerResponse, HomeBannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

//    open fun getItem(position: Int):BannerResponse{
//        return
//    }

    override fun createViewHolder(itemView: View, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: HomeBannerViewHolder?,
        data: BannerResponse?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }

}