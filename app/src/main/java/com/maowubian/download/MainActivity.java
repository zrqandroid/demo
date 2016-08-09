package com.maowubian.download;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.maowubian.download.Adapter.AppAdapter;
import com.maowubian.download.databinding.HomeBinding;


public class MainActivity extends AppCompatActivity {
    public String path = "http://dldir1.qq.com/qqfile/qq/QQ8.5/18600/QQ8.5.exe";
    private HomeBinding binding;
    private BaseDownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.appList.setAdapter(new AppAdapter(this));

    }




}
