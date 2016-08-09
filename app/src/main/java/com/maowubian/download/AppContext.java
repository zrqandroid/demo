package com.maowubian.download;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by zhuruqiao on 16/8/9.
 */
public class AppContext extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        FileDownloader.init(getApplicationContext());
    }
}
