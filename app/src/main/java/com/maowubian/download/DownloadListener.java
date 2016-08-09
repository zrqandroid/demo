package com.maowubian.download;

import android.widget.Button;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

/**
 * Created by zhuruqiao on 16/8/9.
 */
public class DownloadListener extends FileDownloadListener{

    public TextView tvProcess;
    public Button control;

    public DownloadListener(TextView tvProcess,Button control){
        this.tvProcess=tvProcess;
        this.control=control;
    }

    @Override
    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        tvProcess.setText("pending");
    }

    @Override
    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        tvProcess.setText(soFarBytes*100.0/totalBytes+"");
    }

    @Override
    protected void completed(BaseDownloadTask task) {
        tvProcess.setText("completed");
    }

    @Override
    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        tvProcess.setText("paused");
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        tvProcess.setText("error:"+e.getMessage());
    }

    @Override
    protected void warn(BaseDownloadTask task) {
        tvProcess.setText("warn");
    }
}
