package com.shbestwin.followupmanager.common.util;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import android.app.Activity;

/**
 * Activity的堆栈管理
 * 
 * @created 2014年5月8日 下午11:26:41
 * @version 1.0
 */
public class ActivityStackHelper {
	private static LinkedList<WeakReference<Activity>> activityStack = new LinkedList<WeakReference<Activity>>();
	private static ActivityStackHelper instance;

	private ActivityStackHelper() {
	}

	public static ActivityStackHelper getInstance() {
		if (instance == null) {
			synchronized (ActivityStackHelper.class) {
				if (instance == null) {
					instance = new ActivityStackHelper();
				}
			}

		}
		return instance;
	}

	public void addActivity(Activity activity) {
		WeakReference<Activity> weakActivity = new WeakReference<Activity>(activity);
		activityStack.add(weakActivity);
	}

	public void removeActivity(Activity activity) {
		for (WeakReference<Activity> item : activityStack) {
			Activity localActivity = item.get();
			if (null != activity && localActivity != null && localActivity == activity) {
				activityStack.remove(item);
				break;
			}
		}
	}

	public void clear() {
		for (WeakReference<Activity> item : activityStack) {
			Activity activity = item.get();
			if (activity != null) {
				activity.finish();
			}
		}
		activityStack.clear();
	}
}