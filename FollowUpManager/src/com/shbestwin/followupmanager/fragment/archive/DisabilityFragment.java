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
import android.widget.Spinner;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.Disability;
import com.shbestwin.followupmanager.model.archive.Disability.DisabilityLevel;
import com.shbestwin.followupmanager.model.archive.Disability.DisabilityType;
import com.shbestwin.followupmanager.model.archive.FamliyHistories;

/**
 * 
 * 残疾情况
 *
 * @version
 */
public class DisabilityFragment extends BaseArchiveFragment {
	private RadioGroup disabilityRadioGroup;
	private EditText disabilityNoEditText,disabilityTypeEditText;
	private Spinner disabilityLevelSpinner;
	private RelativeLayout disabilityRelativeLayout;
	private CheckBox otherDisability;
	private boolean isDisabilityHistory = false;

	public static DisabilityFragment newInstance() {
		DisabilityFragment fragment = new DisabilityFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_disability, container, false);
		disabilityRadioGroup = (RadioGroup) rootView.findViewById(R.id.disabilityRadioGroup);
		disabilityNoEditText = (EditText) rootView.findViewById(R.id.disabilityNoEditText);
		disabilityTypeEditText = (EditText) rootView.findViewById(R.id.disabilityTypeEditText);
		disabilityLevelSpinner = (Spinner) rootView.findViewById(R.id.disabilityLevelSpinner);
		disabilityRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.disabilityRelativeLayout);
		otherDisability = (CheckBox) rootView.findViewById(R.id.disabilityType6);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		disabilityRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.disabilityRadioButtonNo) {
					renderRadioNo();
				} else {
					renderRadioYes();
				}
			}
		});
		otherDisability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				disabilityTypeEditText.setEnabled(isChecked);
				if(!isChecked){
					disabilityTypeEditText.setText("");
				}
			}
		});
		
		refreshData();
	}

	private void renderRadioNo() {
		isDisabilityHistory = false;
		setCheckBoxStatus(false);
	}

	private void renderRadioYes() {
		isDisabilityHistory = true;
		setCheckBoxStatus(true);
	}
	
	private void setCheckBoxStatus(boolean enabled) {
		disabilityNoEditText.setEnabled(enabled);
		disabilityLevelSpinner.setEnabled(enabled);
		if(!enabled){
			disabilityNoEditText.setText("");
			disabilityLevelSpinner.setSelection(0);
			disabilityTypeEditText.setText("");
		}
		for (int i = 0; i < disabilityRelativeLayout.getChildCount(); i++) {
			View item = disabilityRelativeLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(enabled);
				if (!enabled) {
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
		if (isDisabilityHistory && TextUtils.isEmpty(disabilityNoEditText.getText())){
			ToastUtils.showToast(getActivity(), "请输入残疾证号！");
			return false;
		}
		if(otherDisability.isChecked() && TextUtils.isEmpty(disabilityTypeEditText.getText())){
			ToastUtils.showToast(getActivity(), "请输入其他残疾情况！");
			return false;
		}
		if(isDisabilityHistory && !getCheckBoxStatus()){
			ToastUtils.showToast(getActivity(), "请选择残疾情况！");
			return false;
		}
		return true;
	}

	private boolean getCheckBoxStatus() {
		// TODO Auto-generated method stub
		for (int i = 0; i < disabilityRelativeLayout.getChildCount(); i++) {
			View item = disabilityRelativeLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String disabilityies = "";
			if (isDisabilityHistory) {
				disabilityies = getCheckBoxText();
			}
			archiveInfo.setDisabilityies(disabilityies);
		}
		return archiveInfo;
	}

	private String getCheckBoxText() {
		Disability disability = new Disability();
		DisabilityLevel disabilityLevel = disability.new DisabilityLevel();
		DisabilityType disabilityType = disability.new DisabilityType();
		disability.setDisabilityNo(disabilityNoEditText.getText().toString());
		
		disabilityLevel.setLevelNum(String.valueOf(disabilityLevelSpinner.getSelectedItemPosition()));
		disabilityLevel.setLevelName(disabilityLevelSpinner.getSelectedItem().toString());
		
		for (int i = 0; i < disabilityRelativeLayout.getChildCount(); i++) {
			View item = disabilityRelativeLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				disabilityType.setTypeNum(String.valueOf(i));
				CheckBox checkBox = (CheckBox) item;
				if (checkBox.isChecked()) {
					if ("其他".equals(checkBox.getText())) {
						disabilityType.setTypeName(disabilityTypeEditText.getText().toString());
					} else {
						disabilityType.setTypeName(checkBox.getText().toString());
					}
				}

			}
		}
		disability.setDisabilityLevel(disabilityLevel);
		disability.setDisabilityType(disabilityType);
		try {
			return new JsonUtil().objectToJson(disability);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		if(disabilityRelativeLayout == null){
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String disabilityies = archiveInfo.getDisabilityies();
			Log.i("cjl", ":"+disabilityies);
			if (TextUtils.isEmpty(disabilityies) || "[]".equals(disabilityies)) {
				disabilityRadioGroup.check(R.id.disabilityRadioButtonNo);
			} else {
				disabilityRadioGroup.check(R.id.disabilityRadioButtonYes);
				setDisabilityNoEditText(disabilityies);
				setCheckBoxText(disabilityies);
			}
		}
	}

	private void setCheckBoxText(String disabilityies) {
		String disabilityLevel = disabilityies.substring(disabilityies.indexOf("disabilityLevel")+15, disabilityies.indexOf("disabilityLevel;"));
		setSpinner(disabilityLevelSpinner,disabilityLevel);
		String disability = disabilityies.substring(disabilityies.indexOf("disabilityLevel;")+16, disabilityies.length());
		String[] checkedItems = disability.split(";");
		int checkedSize = checkedItems.length;
		int currentIndex = 0;
		String currentText = checkedItems[currentIndex];
		for (int i = 0; i < disabilityRelativeLayout.getChildCount(); i++) {
			View item = disabilityRelativeLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) item;
				if("其他".equals(checkBox.getText()) && currentText.contains("#")) {
					checkBox.setChecked(true);
					disabilityTypeEditText.setText(disability.substring(disability.indexOf("#")+1, disability.indexOf("#;")));
					currentText = "";
				}
				else if (currentText.equals(checkBox.getText())) {
					checkBox.setChecked(true);
					if (++currentIndex < checkedSize) {
						currentText = checkedItems[currentIndex];
					} else {
						currentText = "";
					}
				} else {
					checkBox.setChecked(false);
				}

			}
		}
		
	}

	private void setDisabilityNoEditText(String disabilityies) {
		disabilityNoEditText.setText(disabilityies.substring(disabilityies.indexOf("disabilityNo")+12, disabilityies.indexOf("disabilityNo;")));	
	}
	
	private void setSpinner(Spinner spinner, String text) {
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			String item = spinner.getAdapter().getItem(i).toString();
			if (item.equals(text)) {
				spinner.setSelection(i);
				break;
			}
		}
	}
}
