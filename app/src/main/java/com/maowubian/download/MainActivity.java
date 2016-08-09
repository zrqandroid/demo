package com.maowubian.download;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.maowubian.download.databinding.HomeBinding;


public class MainActivity extends AppCompatActivity {
    public String path = "http://dldir1.qq.com/qqfile/qq/QQ8.5/18600/QQ8.5.exe";
    private HomeBinding binding;
    private BaseDownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        task = FileDownloader.getImpl().create(path)
                .setPath(StoreConfigFactory.getStorePath(StoreConfigFactory.DIR_DOWNLOAD) + "没错就是老子被下载了.exe")
                .setListener(new DownloadListener());

        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task.getStatus()== FileDownloadStatus.paused){
                    task.reuse();
                    task.start();
                }else {
                    task.start();

                }
            }
        });
        binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                task.pause();
            }
        });
    }


    public class DownloadListener extends FileDownloadListener {

        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            binding.process.setText("pending");
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            binding.process.setText(soFarBytes * 100.0 / totalBytes + "");

        }

        @Override
        protected void completed(BaseDownloadTask task) {
            binding.process.setText("completed");
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            binding.process.setText("paused");
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            binding.process.setText("error:" + e.getMessage());
        }

        @Override
        protected void warn(BaseDownloadTask task) {
            binding.process.setText("warn");
        }
    }
}
