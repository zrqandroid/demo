package com.maowubian.download;

import android.os.Environment;


import java.io.File;

/**
 * Created by zhuruqiao on 16/8/1.
 */
public enum StoreConfigFactory {

    DIR_HTTP("http/", 1),
    DIR_IMAGE("image/", 20),
    DIR_LOG("log/", 20),
    DIR_DOWNLOAD("Download/", 50);

    public final String name;

    public final int size;

    StoreConfigFactory(String name, int limitSize) {
        this.name = name;
        this.size = limitSize;
    }

    private final static long MB = 1024 * 1024;

    public static String getStorePath(StoreConfigFactory dir) {
        String path = null;
        File file;
        switch (dir) {
            case DIR_DOWNLOAD:
                file = new File(Environment.getExternalStorageDirectory(), StoreConfigFactory.DIR_DOWNLOAD.name);
                path = getPath(file);
                break;
            case DIR_HTTP:
                file = new File(AppContext.mContext.getExternalCacheDir(), StoreConfigFactory.DIR_HTTP.name);
                path = getPath(file);
                break;
            case DIR_IMAGE:
                file = new File(AppContext.mContext.getExternalCacheDir(), StoreConfigFactory.DIR_IMAGE.name);
                path = getPath(file);
                break;
            case DIR_LOG:
                file = new File(AppContext.mContext.getCacheDir(), StoreConfigFactory.DIR_LOG.name);
                path = getPath(file);
                break;

        }

        return path;
    }

    private static String getPath(File file) {
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) {
            }

        }
        return file.getPath() + File.separator;
    }

    public static long defaultStoreSize(StoreConfigFactory dir) {

        switch (dir) {
            case DIR_DOWNLOAD:
                return DIR_DOWNLOAD.size * MB;
            case DIR_HTTP:
                return DIR_HTTP.size * MB;
            case DIR_IMAGE:
                return DIR_IMAGE.size * MB;
            case DIR_LOG:
                return DIR_IMAGE.size * MB;
        }

        return 0;

    }


}
