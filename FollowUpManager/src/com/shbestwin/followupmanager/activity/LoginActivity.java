package com.shbestwin.followupmanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;

/**
 * 
 * @ClassName: LoginActivity
 * @Description: 登录页面
 * 
 */
public class LoginActivity extends AbsBaseActivity {

	private EditText et_username, et_password;
	private Button remoteLoginButton, localLoginButton;
	private CheckBox ck_remember;
	private SharedPreferences preferences;

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		remoteLoginButton = (Button) findViewById(R.id.remoteLoginButton);
		localLoginButton = (Button) findViewById(R.id.localLoginButton);
		ck_remember = (CheckBox) findViewById(R.id.ck_remember);

	}

	@Override
	protected void initData() {
		preferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
		String username = preferences.getString("Username", "");
		String password = preferences.getString("Password", "");
		boolean rememberUser = preferences.getBoolean("RememberUser", false);
		System.out.println(username+"-----" + rememberUser);
		if(rememberUser){
			et_username.setText(username);
			et_password.setText(password);
		}
		ck_remember.setChecked(rememberUser);
	}

	@Override
	protected void initListener() {

		remoteLoginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loginRemote();
			}
		});

		localLoginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loginLocal();
			}
		});
	}

	protected void loginLocal() {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		if (isEmpty(username)) {
			ToastUtils.showToast(this, "用户名不能为空！");
			return;
		}

		if (isEmpty(password)) {
			ToastUtils.showToast(this, "密码不能为空！");
			return;
		}
		if (username.equals("admin") && password.equals("admin")) {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("Username", username);
			editor.putString("Password", password);
			editor.putBoolean("RememberUser", ck_remember.isChecked());
			editor.commit();
			MainActivity.launch(mActivity);
			finish();

		}
	}

	protected void loginRemote() {

	}

	public static void launch(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"USER_INFO", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("Username", "");
		editor.putString("Password", "");
		editor.putBoolean("RememberUser", false);
		editor.commit();
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	private boolean isEmpty(String input) {
		if (input == null || "".equals(input) || input.equals("null"))
			return true;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
}
