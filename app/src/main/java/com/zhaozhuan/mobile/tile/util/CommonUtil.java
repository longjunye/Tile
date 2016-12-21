package com.zhaozhuan.mobile.tile.util;

import android.os.Environment;

import com.zhaozhuan.mobile.tile.TileApp;

import java.io.File;

/**
 * Created by ricky.ye on 12/20/16.
 */

public class CommonUtil {
    public static File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if(!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            cachePath = TileApp.getAPP().getApplicationContext().getCacheDir().getPath();
        } else {
            cachePath = TileApp.getAPP().getApplicationContext().getExternalCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * @param code
     * @return
     */
    public static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case 404:
                message = "token无效";
                break;
            case 402:
                message = "数据库连接错误";
                break;
            case 403:
                message = "无记录";
                break;
            case 400:
                message = "参数为空";
                break;
            default:
                message = "未知错误";
                break;

        }
        return message;
    }
}
