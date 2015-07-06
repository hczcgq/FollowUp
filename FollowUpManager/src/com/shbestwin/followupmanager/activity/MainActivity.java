package com.shbestwin.followupmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.ArchiveInfoFragment;
import com.shbestwin.followupmanager.fragment.FollowUpManagerFragment;
import com.shbestwin.followupmanager.fragment.HealthEducationFragment;
import com.shbestwin.followupmanager.fragment.HealthExaminationFragment;
import com.shbestwin.followupmanager.fragment.MedicationGuideFragment;
import com.shbestwin.followupmanager.fragment.TopBarFragment;

/**
 * 
 * @ClassName: MainActivity
 * @Description: 主Activity
 *
 */
public class MainActivity extends AbsBaseActivity implements TopBarFragment.OnTabSelectedListener {

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
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
