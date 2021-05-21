package com.haoran.wanjetpack.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haoran.base.mvvm.activity.BaseFragmentActivity
import com.haoran.wanjetpack.R
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment

@DefaultFirstFragment(IndexFragment::class)
class MainActivity : BaseFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}