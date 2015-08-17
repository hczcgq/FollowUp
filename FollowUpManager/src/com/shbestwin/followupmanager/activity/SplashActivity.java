package com.shbestwin.followupmanager.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.constant.PreferencesConstant;
import com.shbestwin.followupmanager.common.util.FileUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.PreferencesManager;

/**
 * 
 * @ClassName: SplashActivity
 * @Description: 启动页面
 *
 */
public class SplashActivity extends AbsBaseActivity {

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
//		Log.i("hejunbin", "IMEI=" + SystemUtils.getIMEI(this));
//		Log.i("hejunbin", "IMSI=" + SystemUtils.getIMSI(this));
//		Log.i("hejunbin", "MacAddress=" + SystemUtils.getMacAddress(this));
//		Log.i("hejunbin", "DeviceId=" + SystemUtils.getDeviceId(this));
//		//02-11 17:47:35.875: I/hejunbin(32762): AndroidId=b6fb 4685 f4d4 fa04
//
//		Log.i("hejunbin", "AndroidId=" + SystemUtils.getAndroidId(this));

		String serialnum = null;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class, String.class);
			serialnum = (String) (get.invoke(c, "ro.serialno", "unknown"));
		} catch (Exception ignored) {
		}
		Log.i("hejunbin", "serialnum=" +serialnum);
	}

	@Override
	protected void initData() {
		loadResource();

	}

	private void loadResource() {
		if (!FileUtils.checkSDCard()) {
			ToastUtils.showToast(this, "当前没有检测到SDCard，请插上SDCard再使用！");
			finish();
			return;
		}

		new LoadResourceTask().execute();

	}

	private class LoadResourceTask extends AsyncTask<Void, Void, Void> {
		// long startMills = 0;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// startMills = System.currentTimeMillis();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				PreferencesManager preferencesManager = new PreferencesManager(mActivity);

				// 1、将项目中依赖的资源文件解压到sdcard中
				// 资源文件是否解压成功
				boolean resourceUnziped = preferencesManager.getBoolean(PreferencesConstant.KEY_RESOURCE_UNZIPED, false);
				// 资源文件是否存在
				File resourceDir = new File(mApplication.getAppDir() + "resource");
				boolean resourceExisted = resourceDir.exists();
				if (!resourceUnziped || !resourceExisted) {// 文件资源没有被解压成功或者不存在
					String appDir = mApplication.getAppDir();
					FileUtils.createDir(appDir);// 创建目录
					String resourceFileArray[] = getResources().getAssets().list("resource");
					for (int i = 0; i < resourceFileArray.length; i++) {
						resourceFileArray[i] = "resource/" + resourceFileArray[i];
					}

					String destZipFile = appDir + "resource.zip";
					FileUtils.mergeFile(mActivity, resourceFileArray, destZipFile);
					if (FileUtils.unZipFiles(destZipFile, appDir)) {
						new PreferencesManager(mActivity).putBoolean(PreferencesConstant.KEY_RESOURCE_UNZIPED, true);
					}
				}

				// 2、将身份证读卡器依赖文件解压到sdcard中
				boolean wltlibUnziped = preferencesManager.getBoolean(PreferencesConstant.KEY_WLTLIB_UNZIPED, false);
				File wltlibDirFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "wltlib");
				boolean wltlibExisted = wltlibDirFile.exists();
				if (!wltlibUnziped || !wltlibExisted) {
					InputStream wltlibZipStream = getResources().getAssets().open("wltlib.zip");
					String wltlibDir = Environment.getExternalStorageDirectory().getPath() + File.separator;
					if (FileUtils.unzipWithFilesBack(wltlibZipStream, wltlibDir)) {
						new PreferencesManager(mActivity).putBoolean(PreferencesConstant.KEY_WLTLIB_UNZIPED, true);
					}
				}
				
//				// 3、帮助文件解压到sdcard中
//				//帮助文件是否解压成功
//				boolean helpUnziped = preferencesManager.getBoolean(PreferencesConstant.KEY_HELP_UNZIPED, false);
//				// 帮助文件是否存在
//				File helpDir = new File(mApplication.getAppDir() + "help.pdf");
//				boolean helpExisted = helpDir.exists();
//				if (!helpUnziped || !helpExisted) {// 帮助没有被解压成功或者不存在
//					String appDir = mApplication.getAppDir();
//					FileUtils.createDir(appDir);// 创建目录
//					String resourceFileArray[] = getResources().getAssets().list("help");
//					for (int i = 0; i < resourceFileArray.length; i++) {
//						resourceFileArray[i] = "help/" + resourceFileArray[i];
//					}
//
//					String destZipFile = appDir + "help.zip";
//					FileUtils.mergeFile(mActivity, resourceFileArray, destZipFile);
//					if (FileUtils.unZipFiles(destZipFile, appDir)) {
//						new PreferencesManager(mActivity).putBoolean(PreferencesConstant.KEY_RESOURCE_UNZIPED, true);
//					}
//				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// long current = System.currentTimeMillis();
			long delayMills = 0;// current - startMills > 3000 ? 0 : 3000 -
								// current + startMills;
			Handler handler = new Handler(getMainLooper());
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					// 是否登录了，没有登录跳转到登录页面，否则跳转到主页
					LoginActivity.launch(mActivity);
					finish();
				}
			}, delayMills);
		}

	}

	@Override
	protected void initListener() {

	}

}
