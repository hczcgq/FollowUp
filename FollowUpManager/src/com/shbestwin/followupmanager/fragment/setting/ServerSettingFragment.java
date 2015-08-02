package com.shbestwin.followupmanager.fragment.setting;

import java.io.File;

import org.apache.http.util.TextUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;

public class ServerSettingFragment extends BaseFragment {
	private TextView CurrentServerText;
	private EditText NewServerText;
	private Button OnSetButton;

	public static ServerSettingFragment newInstance() {
		ServerSettingFragment fragment = new ServerSettingFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting_server, container, false);
		OnSetButton = (Button) rootView.findViewById(R.id.OnSetButton);
		CurrentServerText = (TextView) rootView.findViewById(R.id.CurrentServerText);
		NewServerText = (EditText) rootView.findViewById(R.id.NewServerText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		final SharedPreferences preferences = getActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
		String ServerUrl=preferences.getString("Server_Url", MyApplication.serverUrl);
		if(!TextUtils.isEmpty(ServerUrl)){
			CurrentServerText.setText("当前服务器地址："+ServerUrl);
		}
		
		OnSetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String newServer=NewServerText.getText().toString();
				if(TextUtils.isEmpty(newServer)){
					ToastUtils.showToast(getActivity(), "您配置的服务器地址为空");
					return;
				}
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("Server_Url", newServer);
				editor.commit();
				CurrentServerText.setText("当前服务器地址："+newServer);
			}
		});
	}
}