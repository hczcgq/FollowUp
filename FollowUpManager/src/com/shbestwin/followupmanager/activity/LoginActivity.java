package com.shbestwin.followupmanager.activity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.dao.DaoMaster;
import com.shbestwin.followupmanager.dao.DaoMaster.DevOpenHelper;
import com.shbestwin.followupmanager.http.HttpHelper;
import com.shbestwin.followupmanager.model.MessageItem;

/**
 * 
 * @ClassName: LoginActivity
 * @Description: 登录页面
 * 
 */
public class LoginActivity extends AbsBaseActivity {

	private LinearLayout ll_server;
	
	private EditText et_username, et_password,et_server;

	private Button localLoginButton;

	private CheckBox ck_remember;

	private SharedPreferences preferences;
	
	private String username;
	
	private String password;
	
	private String server_name;
	
	private boolean isFirst;

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		
		getImsi();

	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_server = (EditText) findViewById(R.id.et_server);
		// remoteLoginButton = (Button) findViewById(R.id.remoteLoginButton);
		localLoginButton = (Button) findViewById(R.id.localLoginButton);
		ck_remember = (CheckBox) findViewById(R.id.ck_remember);

		ll_server=(LinearLayout) findViewById(R.id.ll_server);
	}

	@Override
	protected void initData() {
		preferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
		isFirst = preferences.getBoolean("isfirst", false);
		if(isFirst){
			ll_server.setVisibility(View.GONE);
		}else {
			ll_server.setVisibility(View.VISIBLE);
		}
		username = preferences.getString("Username", "");
		password = preferences.getString("Password", "");
		server_name=preferences.getString("Server_Url", "http://183.247.164.200/ylms");
		boolean rememberUser = preferences.getBoolean("RememberUser", false);
		if (rememberUser) {
			et_username.setText(username);
			et_password.setText(password);
		}
		et_server.setText(server_name);
		ck_remember.setChecked(rememberUser);
	}

	@Override
	protected void initListener() {
		localLoginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loginLocal();
			}
		});
	}

	protected void loginLocal() {
		username = et_username.getText().toString();
		password = et_password.getText().toString();
		server_name=et_server.getText().toString();
		if (isEmpty(username)) {
			ToastUtils.showToast(this, "用户名不能为空！");
			return;
		}

		if (isEmpty(password)) {
			ToastUtils.showToast(this, "密码不能为空！");
			return;
		}
		
		if (isEmpty(server_name)) {
			ToastUtils.showToast(this, "服务器地址不能为空！");
			return;
		}
		
		System.out.println("server_name:"+matchIp(server_name));
		
		if(!matchIp(server_name)){
			ToastUtils.showToast(this, "服务器地址格式正确！");
			return;
		}
		
		
		new GetDataTask().execute();

	}
	
	private String getImsi() {
		 WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
         WifiInfo info = wifi.getConnectionInfo();  
         System.out.println(info.getMacAddress());
         return info.getMacAddress();  
    }

	private boolean matchIp(String ip){
		Pattern pattern=Pattern.compile("^((https|http|ftp|rtsp|mms)?://)" 
			     + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" 
			     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" 
			     + "|" 
			     + "([0-9a-z_!~*'()-]+\\.)*" 
			     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." 
			     + "[a-z]{2,6})" 
			     + "(:[0-9]{1,4})?" 
			     + "((/?)|" 
			     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(ip); //以验证127.400.600.2为例
		return matcher.matches();
	}
	
	private class GetDataTask extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            if (isNetworkAvailable(LoginActivity.this)) {
                HashMap<String, String> hashParams = new HashMap<String, String>();
                hashParams.put("loginName", username);
                hashParams.put("loginPassword ", password);
                hashParams.put("khdCode ", getImsi());
                result = HttpHelper.GetHttpClient(LoginActivity.this, server_name
                        + "/inter/khdLogin", hashParams);
            } else {
            	String histLog=username+"-"+password;
            	String per_histLog=preferences.getString("History_Login", "");
            	if(per_histLog.contains(histLog)){
            		 MessageItem item=new MessageItem("true","登陆成功");
                     result=new Gson().toJson(item);
            	}else {
            		 MessageItem item=new MessageItem("false","登陆失败，请检查用户名和密码！");
                     result=new Gson().toJson(item);
				}
            }
            System.out.println("resutl="+result);
            return result;
        }

        @SuppressWarnings("static-access")
		@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (result != null||result=="") {
                MessageItem item = new Gson().fromJson(result,
                		MessageItem.class);
                if (item.getSuccess().equals("true")) {
                	
                	String busername = preferences.getString("Username", "");
                	String bpassword = preferences.getString("Password", "");
            		if(!username.equals(busername)&&!password.equals(bpassword)){
            			DevOpenHelper helper = new DaoMaster.DevOpenHelper(LoginActivity.this, null);
            			SQLiteDatabase db = helper.getWritableDatabase();
            			DaoMaster daoMaster = new DaoMaster(db);
            			daoMaster.dropAllTables(db, true);
            			daoMaster.createAllTables(db, true);
            		}
                	
                	String histLog=username+"-"+password+";";
                	String per_histLog=preferences.getString("History_Login", "");
                	if(!per_histLog.contains(histLog)){
                		per_histLog+=histLog;
                	}
                	SharedPreferences.Editor editor = preferences.edit();
    				editor.putString("Username", username);
    				editor.putString("Password", password);
    				editor.putString("Server_Url", server_name);
    				editor.putBoolean("RememberUser", ck_remember.isChecked());
    				editor.putBoolean("isfirst", true);
    				editor.putString("History_Login", per_histLog);
    				editor.commit();
    				MainActivity.launch(mActivity);
    				finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, item.getMsg());
                }
            }else {
            	ToastUtils.showToast(LoginActivity.this, "登陆失败，请检查用户名,密码或服务器地址是否正确！");
			}
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "温馨提示",
                    "正在登陆", false, true);
            progressDialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
        }
    }
	
	public boolean isNetworkAvailable(Context context) {
		ConnectivityManager cManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        if (info.isConnected()) {
            return true;
        }
        return false;
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
	
}
