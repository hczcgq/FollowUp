package com.shbestwin.followupmanager.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.shbestwin.followupmanager.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.RemoteViews;

public class UpdateThread extends Thread {

    private String ROOT_External = Environment.getExternalStorageDirectory()
            + "/followupmanager/apk/";

    private String url;

    private Context context;

    private int notification_id = 19172439;

    private NotificationManager nm;

    private Notification notification;

    private int count, lastCount = 0;

    private int divide = 1;

    public UpdateThread() {

    }

    public UpdateThread(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    @SuppressWarnings({ "deprecation", "static-access" })
    @Override
    public void run() {
//        if (!NetworkUtil.networkIsAvailable(context)) {
//            ToastUtil.showToastShort(context,
//                    context.getString(R.string.not_network));
//            return;
//        }

        nm = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);

        notification = new Notification(R.drawable.ic_launcher, "   "
                + "更新程序下载中！",
                System.currentTimeMillis());
        notification.contentView = new RemoteViews(context.getPackageName(),
                R.layout.view_setting_version_down);
        notification.contentView.setProgressBar(R.id.down_pb, 100, 0, false);
        Intent notificationIntent = new Intent(context, UpdateThread.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        notification.contentIntent = contentIntent;
        showNotification();
        downFile(url, ROOT_External + "SplashActivity.apk", context);
        
        
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.fromFile(new File(ROOT_External, "SplashActivity.apk")),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        context.startActivity(intent);
        
    }

    @SuppressWarnings("resource")
    public void downFile(String url, String path, Context context) {
        try {
            URL myURL = new URL(url);
            URLConnection conn = myURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int fileSize = conn.getContentLength();// 根据响应获取文件大小
            if (fileSize <= 0)
                throw new RuntimeException("无法获知文件大小 ");
            if (is == null)
                throw new RuntimeException("stream is null");
            createDirFile(ROOT_External);
            File file=new File(path);
            if(file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(path);
            // 把数据存入路径+文件名
            byte buf[] = new byte[1024];
            int downLoadFileSize = 0;

            do {
                // 循环读取
                int numread = is.read(buf);
                if (numread == -1) {
                    break;
                }
                fos.write(buf, 0, numread);
                downLoadFileSize += numread;
                count = 100 * downLoadFileSize / fileSize;
                if (count - lastCount > divide) {
                    lastCount = count;
                    notification.contentView.setProgressBar(R.id.down_pb, 100,
                            count, false);
                    showNotification();
                }
            } while (true);

           
            is.close();

            nm.cancel(notification_id);
        } catch (Exception ex) {
            nm.cancel(notification_id);
        }
    }

    public void showNotification() {
        nm.notify(notification_id, notification);
    }
    
    public void createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
