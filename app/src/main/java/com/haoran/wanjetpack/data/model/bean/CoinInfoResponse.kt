package com.haoran.wanjetpack.data.model.bean

import java.io.Serializable

/**
 * 分享人信息
 */
data class CoinInfoResponse(
    var coinCount: Int,
    var rank: String,
    var userId: Int,
    var username: String
) : Serializable
