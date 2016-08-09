package com.maowubian.download;

import com.liulishuo.filedownloader.BaseDownloadTask;

/**
 * Created by zhuruqiao on 16/8/9.
 */
public class AppInfo {

    public String appName;
    public String path;

    public AppInfo(String appName, String path) {
        this.appName = appName;
        this.path = path;
    }

    public DownloadListener listener;
    public BaseDownloadTask task;
}
