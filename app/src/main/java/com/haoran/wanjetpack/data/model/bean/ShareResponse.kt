package com.haoran.wanjetpack.data.model.bean

import com.haoran.wanjetpack.app.net.PagerResponse
import java.io.Serializable

data class ShareResponse(
    var coinInfo: CoinInfoResponse,
    var shareArticles: PagerResponse<List<ArticleResponse>>
) : Serializable