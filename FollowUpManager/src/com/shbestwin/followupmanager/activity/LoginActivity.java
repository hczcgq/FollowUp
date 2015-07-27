package com.shbestwin.followupmanager.activity;

import java.io.File;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.http.HttpHelper;

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
		// remoteLoginButton = (Button) findViewById(R.id.remoteLoginButton);
		localLoginButton = (Button) findViewById(R.id.localLoginButton);
		ck_remember = (CheckBox) findViewById(R.id.ck_remember);

//		new GetDataTask().execute();
	}

	@Override
	protected void initData() {
		preferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
		String username = preferences.getString("Username", "1111");
		String password = preferences.getString("Password", "111");
		boolean rememberUser = preferences.getBoolean("RememberUser", false);
		if (rememberUser) {
			et_username.setText(username);
			et_password.setText(password);
		}
		ck_remember.setChecked(rememberUser);
	}

	@Override
	protected void initListener() {

		// remoteLoginButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// loginRemote();
		// }
		// });

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

	private class GetDataTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			File file=getApplication().getDatabasePath("follow_up_manager.db");
			String result = null;
			//上传
//			HashMap<String, String> hashParams = new HashMap<String, String>();
//			hashParams.put("username", "t_user");
//			hashParams.put("password ", "t_password");
//			hashParams.put("datafile ", file.getAbsolutePath());
//			try {
//				result=HttpHelper.uploadHttpClient(LoginActivity.this, "http://180.153.53.247:82/ylms/inter/upDataFile", hashParams, file.getAbsolutePath());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			
			//下载
			HashMap<String, String> hashParams = new HashMap<String, String>();
			hashParams.put("username", "t_user");
			hashParams.put("password ", "t_password");
			result=HttpHelper.DowmloadHttpClient(LoginActivity.this, "http://180.153.53.247:82/ylms/inter/downFile",hashParams,file);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				System.out.println("请求结果：" + result);
			}
		}
	}

	

	public static boolean isNetworkAvailable(Context context) {
		boolean value = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			value = true;
		}
		return value;
	}
}
