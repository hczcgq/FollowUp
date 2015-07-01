package com.shbestwin.followupmanager.fragment.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;

/**
 * 
 * 家庭信息
 *
 * @version
 */
public class FamilyInfoFragment extends BaseArchiveFragment {
	
	private RelativeLayout incomeRelativeLayout,accountAttributeRelativeLayout;
	private Spinner homestyleSpinner,economySpinner;
	private EditText householderName,familyNum;
	private RadioButton incomeRadioButton0,accountAttributeRadioButton0;
	public static FamilyInfoFragment newInstance() {
		FamilyInfoFragment fragment = new FamilyInfoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_family_info, container, false);
		incomeRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.incomeRelativeLayout);
		accountAttributeRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.accountAttributeRelativeLayout);
		homestyleSpinner = (Spinner) rootView.findViewById(R.id.homestyleSpinner);
		economySpinner = (Spinner) rootView.findViewById(R.id.economySpinner);
		householderName = (EditText) rootView.findViewById(R.id.householderName);
		familyNum = (EditText) rootView.findViewById(R.id.familyNum);
		incomeRadioButton0 = (RadioButton)rootView.findViewById(R.id.incomeRadioButton0);
		accountAttributeRadioButton0 = (RadioButton)rootView.findViewById(R.id.accountAttributeRadioButton0);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		for (int i = 0; i < incomeRelativeLayout.getChildCount(); i++) {
			View item = incomeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				
				item.setOnClickListener(new View.OnClickListener() {				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for (int j = 0; j < incomeRelativeLayout.getChildCount(); j++){
							View item_ = incomeRelativeLayout.getChildAt(j);
							if(item_ instanceof RadioButton){
								((RadioButton) item_).setChecked(false);
							}
						}
						((RadioButton) v).setChecked(true);
					}
				});
			}
		}
		
		for (int i = 0; i < accountAttributeRelativeLayout.getChildCount(); i++) {
			View item = accountAttributeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				
				item.setOnClickListener(new View.OnClickListener() {				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						for (int j = 0; j < accountAttributeRelativeLayout.getChildCount(); j++){
							View item_ = accountAttributeRelativeLayout.getChildAt(j);
							if(item_ instanceof RadioButton){
								((RadioButton) item_).setChecked(false);
							}
						}
						((RadioButton) v).setChecked(true);
					}
				});
			}
		}
		
		refreshData();		
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}
		if(TextUtils.isEmpty(householderName.getText())){
			ToastUtils.showToast(getActivity(), "请填写户主姓名！");
			return false;
		}
		if(TextUtils.isEmpty(familyNum.getText())){
			ToastUtils.showToast(getActivity(), "请填人口数！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String familyInfo = "";
			familyInfo = getFamilyInfo();
			archiveInfo.setFamilyInfo(familyInfo);
		}
		return archiveInfo;
	
	}

	private String getFamilyInfo() {
		StringBuilder familyInfo = new StringBuilder();
		familyInfo.append("homestyle"+homestyleSpinner.getSelectedItem().toString()+"homestyle;");
		familyInfo.append("householderName"+householderName.getText()+"householderName;");
		familyInfo.append("familyNum"+familyNum.getText()+"familyNum;");
		familyInfo.append("economy"+economySpinner.getSelectedItem().toString()+"economy;");
		for (int i = 0; i < incomeRelativeLayout.getChildCount(); i++) {
			View item = incomeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) item;
				if (radioButton.isChecked()) {
					familyInfo.append("income"+radioButton.getText()+"income;");
				}

			}
		}
		for (int i = 0; i < accountAttributeRelativeLayout.getChildCount(); i++) {
			View item = accountAttributeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) item;
				if (radioButton.isChecked()) {
					familyInfo.append("accountAttribute"+radioButton.getText()+"accountAttribute;");
				}

			}
		}
		Log.e("shaocheng"," familyInfo.toString()=" +  familyInfo.toString());
		return familyInfo.toString();
	}
	
	private void setFamilyInfo(String familyInfo) {
		setSpinner(homestyleSpinner,familyInfo.substring(familyInfo.indexOf("homestyle")+9, familyInfo.indexOf("homestyle;")));
		setSpinner(economySpinner,familyInfo.substring(familyInfo.indexOf("economy")+7, familyInfo.indexOf("economy;")));
		householderName.setText(familyInfo.substring(familyInfo.indexOf("householderName")+15, familyInfo.indexOf("householderName;")));
		familyNum.setText(familyInfo.substring(familyInfo.indexOf("familyNum")+9, familyInfo.indexOf("familyNum;")));
		String income = familyInfo.substring(familyInfo.indexOf("income")+6, familyInfo.indexOf("income;"));
		String accountAttribute = familyInfo.substring(familyInfo.indexOf("accountAttribute")+16, familyInfo.indexOf("accountAttribute;"));
		for (int i = 0; i < incomeRelativeLayout.getChildCount(); i++) {
			View item = incomeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) item;
				if (radioButton.getText().equals(income)) {
					radioButton.setChecked(true);
				}else{
					radioButton.setChecked(false);
				}

			}
		}
		for (int i = 0; i < accountAttributeRelativeLayout.getChildCount(); i++) {
			View item = accountAttributeRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) item;
				if (radioButton.getText().equals(accountAttribute)) {
					radioButton.setChecked(true);
				}else{
					radioButton.setChecked(false);
				}

			}
		}
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
	@Override
	public void refreshData() {
		if (incomeRelativeLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String  familyInfo= archiveInfo.getFamilyInfo();
			if (TextUtils.isEmpty(familyInfo)) {
				homestyleSpinner.setSelection(3);//核心家庭 当前我国主要类型
				householderName.setText("");
				familyNum.setText("");
				economySpinner.setSelection(0);
				incomeRadioButton0.setChecked(true);
				accountAttributeRadioButton0.setChecked(true);
			} else {
				setFamilyInfo(familyInfo);				
			}
		}
	}

}
