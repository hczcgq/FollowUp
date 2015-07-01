package com.shbestwin.followupmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.SearchFragment;

/**
 * 
 * @ClassName: SearchActivity
 * @Description: 搜索页面
 *
 */
public class SearchActivity extends AbsBaseActivity {
	private ImageView backImageView;


	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_search);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, SearchFragment.newInstance()).commit();
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
	
	public static void  launch(Context context) {
		Intent intent = new Intent(context, SearchActivity.class);
		context.startActivity(intent);
	}

}
