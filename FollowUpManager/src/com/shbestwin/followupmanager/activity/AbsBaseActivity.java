package com.shbestwin.followupmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.common.log.Log;
import com.shbestwin.followupmanager.common.util.ActivityStackHelper;

public abstract class AbsBaseActivity extends FragmentActivity {
	protected static String TAG = "FollowUpManager";
	protected Log mLog = new Log("common");

	protected Activity mActivity;// 上下文参数
	protected MyApplication mApplication;// 全局应用

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		ActivityStackHelper.getInstance().addActivity(this);
		mApplication = (MyApplication) getApplication();
		this.setContentView(savedInstanceState);
		this.initView(savedInstanceState);
		this.initData();
		this.initListener();
	}

	/**
	 * 设置视图
	 */
	protected abstract void setContentView(Bundle savedInstanceState);

	/**
	 * 初始化视图
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 初始化事件
	 */
	protected abstract void initListener();

	@Override
	protected void onDestroy() {
		ActivityStackHelper.getInstance().removeActivity(this);
		super.onDestroy();
	}
}
