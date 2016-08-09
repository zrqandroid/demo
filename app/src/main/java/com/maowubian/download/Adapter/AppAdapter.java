package com.maowubian.download.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.maowubian.download.AppInfo;
import com.maowubian.download.DownloadListener;
import com.maowubian.download.R;
import com.maowubian.download.StoreConfigFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuruqiao on 16/8/9.
 */
public class AppAdapter extends BaseAdapter {
    public List<AppInfo> infos;
    private Context mContext;

    public AppAdapter(Context mContext) {
        infos = new ArrayList<>();
        this.mContext = mContext;
        for (int i = 0; i < 10; i++) {
            AppInfo appInfo = new AppInfo("name" + i, "http://dldir1.qq.com/qqfile/qq/QQ8.5/18600/QQ8.5.exe");
            infos.add(appInfo);
        }

    }


    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(mContext, R.layout.item, null);
        TextView process = (TextView) view.findViewById(R.id.process);
        TextView name = (TextView) view.findViewById(R.id.name);
        final Button control = (Button) view.findViewById(R.id.control);
        final AppInfo appInfo = infos.get(i);
        appInfo.listener = new DownloadListener(process, control);
        appInfo.task = getTask(name, appInfo);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byte status = appInfo.task.getStatus();
                if (status== FileDownloadStatus.paused){
                    appInfo.task.reuse();
                    appInfo.task.start();
                    control.setText("暂停");
                }else if (status==FileDownloadStatus.INVALID_STATUS){
                    appInfo.task.start();
                    control.setText("暂停");
                }else if (status==FileDownloadStatus.progress){
                    appInfo.task.pause();
                    control.setText("继续");
                }else{
                    appInfo.task.pause();
                    control.setText("继续");
                }
            }
        });
        return view;
    }

    private BaseDownloadTask getTask(TextView name, AppInfo appInfo) {
        BaseDownloadTask task = FileDownloader.getImpl().create(appInfo.path)
                .setPath(StoreConfigFactory.getStorePath(StoreConfigFactory.DIR_DOWNLOAD) + appInfo.appName + ".exe")
                .setListener(appInfo.listener);
        name.setText(appInfo.appName);
        return task;
    }


}
