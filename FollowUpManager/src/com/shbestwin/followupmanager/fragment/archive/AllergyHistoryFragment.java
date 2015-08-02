package com.shbestwin.followupmanager.fragment.archive;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.AllergyHistories;

/**
 * 
 * 过敏史
 *
 * @version
 */
public class AllergyHistoryFragment extends BaseArchiveFragment {
	private RadioGroup allergyHistoryRadioGroup;
	private RelativeLayout allergyLayout;
	private EditText allergyEditTextOther;
	private CheckBox allergyOther;
	
	private boolean isAllergyHistory = false;
	public static AllergyHistoryFragment newInstance() {
		AllergyHistoryFragment fragment = new AllergyHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_allergy_history, container, false);
		allergyHistoryRadioGroup = (RadioGroup) rootView.findViewById(R.id.allergyHistoryRadioGroup);
		allergyLayout = (RelativeLayout) rootView.findViewById(R.id.allergyLayout);
		allergyEditTextOther = (EditText) rootView.findViewById(R.id.allergyEditTextOther);
		allergyOther = (CheckBox) rootView.findViewById(R.id.allergy4);
		allergyEditTextOther.setEnabled(allergyOther.isChecked());
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		allergyHistoryRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.allergyHistoryNoRadioButton) {
					renderRadioNo();
				} else {
					renderRadioYes();
				}
			}
		});
		
		allergyOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				allergyEditTextOther.setEnabled(isChecked);
				if(!isChecked){
					allergyEditTextOther.setText("");
				}
			
			}
		});
		
		refreshData();
	}

	private void renderRadioNo() {
		isAllergyHistory = false;
		setCheckBoxStatus(false);
	}

	private void renderRadioYes() {
		isAllergyHistory = true;
		setCheckBoxStatus(true);
	}
	
	private void setCheckBoxStatus(boolean enable) {
		for (int i = 0; i < allergyLayout.getChildCount(); i++) {
			View item = allergyLayout.getChildAt(i);
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

		if (isAllergyHistory && TextUtils.isEmpty(getCheckBoxText())) {
			ToastUtils.showToast(getActivity(), "请选择过敏史内容！");
			return false;
		}
		
		if(allergyOther.isChecked() && TextUtils.isEmpty(allergyEditTextOther.getText().toString()) ){
			ToastUtils.showToast(getActivity(), "请输入其他过敏史！");
			return false;
		}
		
		return true;
	}
	

	private String getCheckBoxText() {
		List<AllergyHistories> allergyHistoriesList = new ArrayList<AllergyHistories>();
		for (int i = 0; i < allergyLayout.getChildCount(); i++) {
			View item = allergyLayout.getChildAt(i);
			AllergyHistories allergyHistories = new AllergyHistories();
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					allergyHistories.setAllergyNum(String.valueOf(i));
					if ("其他".equals(checkBox.getText())) {
						allergyHistories.setAllergyName(allergyEditTextOther.getText().toString());
					} else {
						allergyHistories.setAllergyName(checkBox.getText().toString());
					}
					allergyHistoriesList.add(allergyHistories);
				}
				
			}
			
		}
		try {
			return JsonUtil.objectsToJson(allergyHistoriesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		//return result.toString();
	}
	

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String allergyHistories = "";
			if (isAllergyHistory) {
				allergyHistories = getCheckBoxText();
			}
			archiveInfo.setAllergyHistories(allergyHistories);
		}
		return archiveInfo;
	}

	@Override
	public void refreshData() {
		if (allergyLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String allergyHistories = archiveInfo.getAllergyHistories();
			Log.i("cjl", "allergyHistories--"+allergyHistories);
			if (TextUtils.isEmpty(allergyHistories) || allergyHistories.equals("[]")) {
				allergyHistoryRadioGroup.check(R.id.allergyHistoryNoRadioButton);
				renderRadioNo();
				allergyEditTextOther.setText("");
			} else {
				try{
					List<AllergyHistories> allergyHistoriesList = JsonUtil.jsonToObjects(allergyHistories, AllergyHistories.class);
					allergyHistoryRadioGroup.check(R.id.allergyHistoryYesRadioButton);
					renderRadioYes();
					setCheckBoxText(allergyLayout,allergyHistoriesList);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else {
			allergyHistoryRadioGroup.check(R.id.allergyHistoryNoRadioButton);
			renderRadioNo();
			allergyEditTextOther.setText("");
		}
	}
	
	private void setCheckBoxText(ViewGroup layout, List<AllergyHistories> allergyHistories) {
		for(int i = 0;i <allergyHistories.size();i++){
			AllergyHistories aHistories = allergyHistories.get(i);
			int allergyNum = Integer.parseInt(aHistories.getAllergyNum());
			String allergyName = aHistories.getAllergyName();
			View item = layout.getChildAt(allergyNum);
			if(item instanceof CheckBox){
				CheckBox checkBox = (CheckBox) item;
				if(allergyNum == (layout.getChildCount()-2)){
					allergyEditTextOther.setText(allergyName);
				}
				checkBox.setChecked(true);
			}			
		}
	}

}
