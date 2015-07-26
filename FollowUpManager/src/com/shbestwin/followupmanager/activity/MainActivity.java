package com.shbestwin.followupmanager.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.ArchiveInfoFragment;
import com.shbestwin.followupmanager.fragment.FollowUpManagerFragment;
import com.shbestwin.followupmanager.fragment.HealthEducationFragment;
import com.shbestwin.followupmanager.fragment.HealthExaminationFragment;
import com.shbestwin.followupmanager.fragment.MedicationGuideFragment;
import com.shbestwin.followupmanager.fragment.TopBarFragment;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.ArchiveInfoManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;

/**
 * 
 * @ClassName: MainActivity
 * @Description: 主Activity
 *
 */
public class MainActivity extends AbsBaseActivity implements TopBarFragment.OnTabSelectedListener {

	private ImageView switchMenuImageView;
	private LinearLayout buttonLayout;
	private TextView textView01,textView02,textView03,textView04;
	
	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		switchMenuImageView=(ImageView) findViewById(R.id.switchMenuImageView);
		buttonLayout=(LinearLayout) findViewById(R.id.buttonLayout);
		textView01=(TextView) findViewById(R.id.TextView01);
		textView02=(TextView) findViewById(R.id.TextView02);
		textView03=(TextView) findViewById(R.id.TextView03);
		textView04=(TextView) findViewById(R.id.TextView04);
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void initListener() {
		switchMenuImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(buttonLayout.getVisibility()==View.VISIBLE){
					buttonLayout.setVisibility(View.GONE);
					switchMenuImageView.setImageResource(R.drawable.button_menu_close);
				}else {
					List<Accompany> accompanyList=AccompanyManager.getInstance(MainActivity.this).getAccompanyListGroupBy();
					if(accompanyList!=null&&accompanyList.size()>0){
						textView01.setText(accompanyList.size()+"人");
					}
					
					List<Accompany> accompanyListed=AccompanyManager.getInstance(MainActivity.this).getAccompanyListAlready();
					if(accompanyListed!=null&&accompanyListed.size()>0){
						textView03.setText(accompanyListed.size()+"人");
					}
					
					textView02.setText(accompanyList.size()-accompanyListed.size()+"人");
					
					List<Accompany> accompanyListTime=AccompanyManager.getInstance(MainActivity.this).getAccompanyListAlready();
					if(accompanyListTime!=null&&accompanyListTime.size()>0){
						textView04.setText(accompanyListTime.get(0).getNext_time());
					}
					
					buttonLayout.setVisibility(View.VISIBLE);
					switchMenuImageView.setImageResource(R.drawable.button_menu_open);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(int index) {
		changePage(index);
	}

	private void changePage(int index) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.mainlayout, newInstance(index));
		ft.commitAllowingStateLoss();
	}

	private Fragment newInstance(int index) {
		switch (index) {
		case 0:
			return ArchiveInfoFragment.newInstance();
		case 1:
			return HealthExaminationFragment.newInstance();
		case 2:
			return FollowUpManagerFragment.newInstance();
		case 3:
			return HealthEducationFragment.newInstance();
		case 4:
			return MedicationGuideFragment.newInstance();
		}
		return null;
	}
	
	@Override
	public void onBackPressed() {
		this.finish();
		System.exit(0);
	}
	
	public static void  launch(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}
}
