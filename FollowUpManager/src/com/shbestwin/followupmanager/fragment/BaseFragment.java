package com.shbestwin.followupmanager.fragment;

import android.support.v4.app.Fragment;

import com.shbestwin.followupmanager.common.log.Log;

public class BaseFragment extends Fragment {
	protected static String TAG = "FollowUpManager";
	protected Log mLog = new Log("common");

	public void onRenderPage() {
	}

	/**
	 * 
	 * @Title: onReset
	 * @Description: 重置数据，当点击新增和体检登记都需要调用此方法
	 * @param
	 * @return void
	 * @throws
	 */
	public void onReset() {

	}

	public void onSave() {

	}

	public void onUpload() {

	}

	public void onConclusion() {

	}

	public void onPrint() {

	}

	public void onExaminationRegister() {

	}
}
