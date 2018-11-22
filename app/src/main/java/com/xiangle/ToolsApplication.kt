package com.xiangle

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Created by wangxiangle on 2018/11/21 11:35
 * E-Mail Address： wang_x_le@163.com
 */
class ToolsApplication:Application(){


    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}