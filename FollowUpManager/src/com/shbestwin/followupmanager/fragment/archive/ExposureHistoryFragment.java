package com.shbestwin.followupmanager.fragment.archive;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.ExposureHistories;

/**
 * 暴露史
 *
 * @version
 */
public class ExposureHistoryFragment extends BaseArchiveFragment {
	private RadioGroup exposureHistoryRadioGroup;

	private LinearLayout exposureLayout;

	private boolean isExposureHistory = false;

	public static ExposureHistoryFragment newInstance() {
		ExposureHistoryFragment fragment = new ExposureHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_exposure_history, container, false);
		exposureHistoryRadioGroup = (RadioGroup) rootView.findViewById(R.id.exposureHistoryRadioGroup);
		exposureLayout = (LinearLayout) rootView.findViewById(R.id.exposureLayout);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		exposureHistoryRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.exposureHistoryNoRadioButton) {
					renderRadioNo();
				} else {
					renderRadioYes();
				}
			}
		});

		refreshData();
	}

	private void renderRadioNo() {
		isExposureHistory = false;
		setCheckBoxStatus(false);
	}

	private void renderRadioYes() {
		isExposureHistory = true;
		setCheckBoxStatus(true);
	}

	private void setCheckBoxStatus(boolean enable) {
		for (int i = 0; i < exposureLayout.getChildCount(); i++) {
			View item = exposureLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(enable);
				if (!enable) {
					((CheckBox) item).setChecked(false);
				}
			}
		}
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}

		if (isExposureHistory && TextUtils.isEmpty(getCheckBoxText())) {
			ToastUtils.showToast(getActivity(), "请选择暴露史内容！");
			return false;
		}

		return true;
	}

	private String getCheckBoxText() {
		StringBuilder result = new StringBuilder();
		List<ExposureHistories> exposureHistoriesList = new ArrayList<ExposureHistories>();
		for (int i = 0; i < exposureLayout.getChildCount(); i++) {
			ExposureHistories exposureHistories = new ExposureHistories();
			
			View item = exposureLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					exposureHistories.setExposureNum(String.valueOf(i));
					exposureHistories.setExposureName(checkBox.getText().toString());
					exposureHistoriesList.add(exposureHistories);
					//result.append(checkBox.getText()).append(";");
				}

			}
		}
		try {
		//return result.toString();
			return JsonUtil.objectsToJson(exposureHistoriesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String exposureHistories = "";
			if (isExposureHistory) {
				exposureHistories = getCheckBoxText();
			}
			archiveInfo.setExposureHistories(exposureHistories);
		}
		return archiveInfo;
	}

	@Override
	public void refreshData() {
		if (exposureLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String exposureHistories = archiveInfo.getExposureHistories();
			if (TextUtils.isEmpty(exposureHistories)|| "[]".equals(exposureHistories)) {
				exposureHistoryRadioGroup.check(R.id.exposureHistoryNoRadioButton);
				renderRadioNo();
			} else {
				try{
				List<ExposureHistories> exposureHistoriesList = JsonUtil.jsonToObjects(exposureHistories, ExposureHistories.class);
				exposureHistoryRadioGroup.check(R.id.exposureHistoryYesRadioButton);
				renderRadioYes();
				setCheckBoxText(exposureLayout, exposureHistoriesList);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else{
			exposureHistoryRadioGroup.check(R.id.exposureHistoryNoRadioButton);
			renderRadioNo();
		}
			
	}

	private void setCheckBoxText(ViewGroup layout, List<ExposureHistories> exposureHistoriesList) {
		for(int i = 0;i <exposureHistoriesList.size();i++){
			ExposureHistories exposureHistories = exposureHistoriesList.get(i);
			int exposureNum = Integer.parseInt(exposureHistories.getExposureNum());
			String exposureName = exposureHistories.getExposureName();
			View item = layout.getChildAt(exposureNum);
			if(item instanceof CheckBox){
				CheckBox checkBox = (CheckBox) item;
				checkBox.setChecked(true);
			}	
		}
	}
}
