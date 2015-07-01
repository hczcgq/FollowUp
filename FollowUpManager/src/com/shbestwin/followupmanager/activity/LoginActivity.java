package com.shbestwin.followupmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shbestwin.followupmanager.R;

/**
 * 
 * @ClassName: LoginActivity
 * @Description: 登录页面
 *
 */
public class LoginActivity extends AbsBaseActivity {

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initListener() {

	}

	public static void launch(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}
}
