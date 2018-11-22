package com.xiangle.buglytools

import android.content.Context
import android.text.TextUtils
import com.tencent.bugly.crashreport.CrashReport
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.Exception

/**
 * Created by wangxiangle on 2018/11/21 11:27
 * E-Mail Address： wang_x_le@163.com
 */

object BuglyTools {

    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    fun sampleInit(context: Context, appid: String) {
        init(context, appid, null)
    }

    fun init(context: Context, appid: String) {
        //获取当前包名
        var packageName = context.packageName
        //获取当前进程名
        var processName = getProcessName(android.os.Process.myPid())
        //设置是否未上报进程
        var starategy = CrashReport.UserStrategy(context)
//        starategy.appChannel = "default" //设置渠道号
//        starategy.appVersion = "1.1"//设置版本
//        starategy.appPackageName = packageName// 设置包名
//        starategy.appReportDelay = 20000//延迟20秒 初始化
//        CrashReport.setUserSceneTag(context,1111) //设置APp场景标签
//        CrashReport.setIsDevelopmentDevice(context, true)//设置开发设备
        starategy.isUploadProcess = processName == null || processName == packageName
        //初始化bugly
        init(context, appid, starategy)
    }

    fun init(context: Context, appid: String, starategy: CrashReport.UserStrategy?) {
        if (starategy == null) {
            CrashReport.initCrashReport(context, appid, isDebug())
        } else {
            CrashReport.initCrashReport(context, appid, isDebug(), starategy)
        }
    }

    //获取当前进程名
    private fun getProcessName(myPid: Int): String? {
        var reader: BufferedReader? = null

        try {
            reader = BufferedReader(FileReader("/proc/$myPid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim()
            }
            return processName
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        return null
    }
}