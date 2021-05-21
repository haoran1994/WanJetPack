package com.haoran.wanjetpack.app.widget.banner

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.haoran.wanjetpack.R

/**
 * className：HomeBannerViewHolder
 * packageName：com.haoran.wanjetpack.app.widget.banner
 * createTime：2021/5/21 15:54
 * author： haoran
 * descrioption：HomeBannerViewHolder
 **/
class HomeBannerViewHolder(val view: View) : BaseViewHolder<BannerResponse>(view) {
    override fun bindData(data: BannerResponse?, position: Int, pageSize: Int) {
        val img = itemView.findViewById<ImageView>(R.id.banner_image)
        val title = itemView.findViewById<AppCompatTextView>(R.id.banner_title)
        data?.let {
            title.text = it.title
            Glide.with(view)
                .load(it.imagePath)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(img)
        }
    }

}