package com.xiangle.buglytools

import android.content.Context
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta

/**
 * Created by wangxiangle on 2018/11/21 13:38
 * E-Mail Address： wang_x_le@163.com
 */
object BuglyUpdate {


    fun init(context: Context, appid: String) {
        Bugly.init(context, appid, isDebug())
    }


    fun checkUpdate(){
        Beta.checkUpgrade()
    }
    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

}