package com.shbestwin.followupmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.SettingFragment;

/**
 * 
 * @ClassName: SettingActivity
 * @Description: 系统设置页面
 *
 */
public class SettingActivity extends AbsBaseActivity {
	private ImageView backImageView;

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_setting);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, SettingFragment.newInstance()).commit();
		}

		backImageView = (ImageView) this.findViewById(R.id.backImageView);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initListener() {
		backImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	public static void launch(Context context) {
		Intent intent = new Intent(context, SettingActivity.class);
		context.startActivity(intent);
	}
}
