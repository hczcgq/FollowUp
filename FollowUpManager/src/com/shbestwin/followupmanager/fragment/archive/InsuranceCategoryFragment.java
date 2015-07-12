package com.shbestwin.followupmanager.fragment.archive;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;

/**
 * 保险类别
 *
 * @version
 */
public class InsuranceCategoryFragment extends BaseArchiveFragment {

	private CheckBox categoryName;
	private EditText insuranceNo;
	private RelativeLayout insuranceLayout;
	private boolean insuranceCategoryIsChecked;
	public static InsuranceCategoryFragment newInstance() {
		InsuranceCategoryFragment fragment = new InsuranceCategoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_insurance_category, container, false);
		insuranceLayout  = (RelativeLayout) rootView;
		categoryName = (CheckBox) rootView.findViewById(R.id.insuranceCategory0);
		insuranceNo = (EditText) rootView.findViewById(R.id.insuranceCategoryEditText);
		insuranceCategoryIsChecked = categoryName.isChecked();
		insuranceNo.setEnabled(insuranceCategoryIsChecked);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		categoryName.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				insuranceCategoryIsChecked = isChecked;
				insuranceNo.setEnabled(isChecked);
				if(!isChecked){
					insuranceNo.setText("");
				}
			}
		});
		refreshData();
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}
		
		if(insuranceCategoryIsChecked && TextUtils.isEmpty(insuranceNo.getText().toString())){
			ToastUtils.showToast(getActivity(), "请输入医保卡号！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			archiveInfo.setInsuranceNo(insuranceNo.getText().toString());
			archiveInfo.setCategoryName(getCategoryName());
		}
		return archiveInfo;
	}

	private String getCategoryName() {
		// TODO Auto-generated method stub
		StringBuilder insuranceType = new StringBuilder();
		for(int i = 0;i < insuranceLayout.getChildCount();i++){
			View item = insuranceLayout.getChildAt(i);
			if(item instanceof CheckBox){

				CheckBox itemCheckBox = (CheckBox) item;
				if (itemCheckBox.isChecked()) {
					/*if ("其他".equals(itemCheckBox.getText())) {
						insuranceType.append("#" + negativeEventEditText.getText().toString() + "#");
					} else {*/
						insuranceType.append(itemCheckBox.getText());
					//}
					insuranceType.append(";");
				}			
			}			
		}
		return insuranceType.toString();
	}

	@Override
	public void refreshData() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			resetData();
		} else {
			renderData(archiveInfo);
		}
	}

	private void renderData(ArchiveInfo archiveInfo) {
		insuranceNo.setText(archiveInfo.getInsuranceNo());
		setCategoryName(archiveInfo.getCategoryName());
	}

	private void setCategoryName(String insuranceType) {

		
		ArrayList<CheckBox> itemCheckBoxs = new ArrayList<CheckBox>();
		for (int i = 0; i < insuranceLayout.getChildCount(); i++) {
			View item = insuranceLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				itemCheckBoxs.add((CheckBox) item);
			}
		}
		
		String selectedItems[] = null;
		int selectedCount = -1;
		if (!TextUtils.isEmpty(insuranceType)) {
			selectedItems = insuranceType.split(";");
		}
		
		String selectedItemText = "";
		int selectedItemIndex = -1;
		if(!ArrayUtils.isEmpty(selectedItems)) {
			selectedItemText = selectedItems[0];
			selectedItemIndex = 0;
			selectedCount = selectedItems.length;
		}
		
		for(CheckBox itemCheckBox : itemCheckBoxs) {
			String itemText = itemCheckBox.getText().toString();
			if (TextUtils.isEmpty(selectedItemText)) {
				itemCheckBox.setChecked(false);
			} else {
				 if (selectedItemText.equals(itemText)) {
					itemCheckBox.setChecked(true);
					if (++selectedItemIndex < selectedCount) {
						selectedItemText = selectedItems[selectedItemIndex];
					} else {
						selectedItemText = "";
					}
				} else {
					itemCheckBox.setChecked(false);
				}
			}
		}
	
	}

	private void resetData() {
		insuranceNo.setText("");
		resetInsuranceCategory();
		
	}

	private String resetInsuranceCategory() {
		StringBuilder insuranceType = new StringBuilder();
		for (int i = 0; i < insuranceLayout.getChildCount(); i++) {
			View item = insuranceLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				CheckBox itemCheckBox = (CheckBox) item;
				if (itemCheckBox.isChecked()) {
					itemCheckBox.setChecked(false);
				}
			}
		}
		return insuranceType.toString();
		
	}
}
