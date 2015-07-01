package com.shbestwin.followupmanager.fragment.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;

public class VersionFragment extends BaseFragment {
	private TextView tipsTextView;
	private Button updateVersionButton;

	public static VersionFragment newInstance() {
		VersionFragment fragment = new VersionFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting_version, container, false);
		tipsTextView = (TextView) rootView.findViewById(R.id.tipsTextView);
		updateVersionButton = (Button) rootView.findViewById(R.id.updateVersionButton);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// TODO 检查是本地登录还是网络登录
		String tips = "当前版本号：" +SystemUtils.getVersionName(getActivity()) + "\n当前是本地登录，无法判断是否需要更新。";
		tipsTextView.setText(tips);
		updateVersionButton.setEnabled(false);
		// 如果为本地登录，则不允许更新版本
		updateVersionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
