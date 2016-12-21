package com.zhaozhuan.mobile.tile;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.zhaozhuan.mobile.tile.util.Constant;

/**
 * Created by ricky.ye on 12/19/16.
 */

public class TileApp extends MultiDexApplication {
    private static TileApp app;
    public static TileApp getAPP() {
        return app;
    }

    private static void setApp(TileApp app) {
        TileApp.app = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApp(this);
        //TODO init  crash manager ...
        initLeakCanary();
        initStetho();
    }

    void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    void initStetho() {
        Stetho.initializeWithDefaults(this);
    }
}
