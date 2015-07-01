package com.shbestwin.followupmanager.fragment.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;

/**
 * 
 * @ClassName: MensesHistoryFragment
 * @Description: 月经史
 *
 */
public class MensesHistoryFragment extends BaseArchiveFragment {
	private EditText ccnl,jjnl,yjzq,yjcxsj;
	private RadioButton menstrualQuantityRadioButton0,menstrualQuantityRadioButton1,menstrualQuantityRadioButton2,menstrualQuantityRadioButton3,
	menalgiaRadioButton0,menalgiaRadioButton1,menalgiaRadioButton2;
	private RelativeLayout tjRelativeLayout,yjlRelativeLayout;
	public static MensesHistoryFragment newInstance() {
		MensesHistoryFragment fragment = new MensesHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_menses_history, container, false);
		tjRelativeLayout = (RelativeLayout)rootView.findViewById(R.id.tjRelativeLayout);
		yjlRelativeLayout = (RelativeLayout)rootView.findViewById(R.id.yjlRelativeLayout);
		
		ccnl = (EditText) rootView.findViewById(R.id.ccnl);
		jjnl = (EditText) rootView.findViewById(R.id.jjnl);
		yjzq = (EditText) rootView.findViewById(R.id.yjzq);
		yjcxsj = (EditText) rootView.findViewById(R.id.yjcxsj);
		
		menstrualQuantityRadioButton0 = (RadioButton)rootView.findViewById(R.id.menstrualQuantityRadioButton0);
		menstrualQuantityRadioButton1 = (RadioButton)rootView.findViewById(R.id.menstrualQuantityRadioButton1);
		menstrualQuantityRadioButton2 = (RadioButton)rootView.findViewById(R.id.menstrualQuantityRadioButton2);
		menstrualQuantityRadioButton3 = (RadioButton)rootView.findViewById(R.id.menstrualQuantityRadioButton3);
		menalgiaRadioButton0 = (RadioButton)rootView.findViewById(R.id.menalgiaRadioButton0);
		menalgiaRadioButton1 = (RadioButton)rootView.findViewById(R.id.menalgiaRadioButton1);
		menalgiaRadioButton2 = (RadioButton)rootView.findViewById(R.id.menalgiaRadioButton2);
		
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		for(int i = 0;i < yjlRelativeLayout.getChildCount();i++){
			View item = yjlRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				((RadioButton) item).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if(isChecked){
							if(buttonView.getId() == R.id.menstrualQuantityRadioButton0){
								menstrualQuantityRadioButton1.setChecked(false);
								menstrualQuantityRadioButton2.setChecked(false);
								menstrualQuantityRadioButton3.setChecked(false);
							}
							if(buttonView.getId() == R.id.menstrualQuantityRadioButton1){
								menstrualQuantityRadioButton0.setChecked(false);
								menstrualQuantityRadioButton2.setChecked(false);
								menstrualQuantityRadioButton3.setChecked(false);
							}
							if(buttonView.getId() == R.id.menstrualQuantityRadioButton2){
								menstrualQuantityRadioButton0.setChecked(false);
								menstrualQuantityRadioButton1.setChecked(false);
								menstrualQuantityRadioButton3.setChecked(false);
							}
							if(buttonView.getId() == R.id.menstrualQuantityRadioButton3){
								menstrualQuantityRadioButton0.setChecked(false);
								menstrualQuantityRadioButton1.setChecked(false);
								menstrualQuantityRadioButton2.setChecked(false);
							}
						}
					}
				});
			}
		
		}
		
		for(int i = 0;i < tjRelativeLayout.getChildCount();i++){
			View item = tjRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				((RadioButton) item).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if(isChecked){
							if(buttonView.getId() == R.id.menalgiaRadioButton0){
								menalgiaRadioButton1.setChecked(false);
								menalgiaRadioButton2.setChecked(false);
							}
							if(buttonView.getId() == R.id.menalgiaRadioButton1){
								menalgiaRadioButton0.setChecked(false);
								menalgiaRadioButton2.setChecked(false);
							}
							if(buttonView.getId() == R.id.menalgiaRadioButton2){
								menalgiaRadioButton0.setChecked(false);
								menalgiaRadioButton1.setChecked(false);
							}
						}
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
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String mensesHistories = "";
			mensesHistories = getMensesHistories();
			archiveInfo.setMensesHistories(mensesHistories);
		}
		return archiveInfo;
	}

	private String getMensesHistories() {
		// TODO Auto-generated method stub
		StringBuilder mensesHistories = new StringBuilder();
		mensesHistories.append("ccnl"+ccnl.getText().toString()+"ccnl;");
		mensesHistories.append("jjnl"+jjnl.getText().toString()+"jjnl;");
		mensesHistories.append("yjzq"+yjzq.getText().toString()+"yjzq;");
		mensesHistories.append("yjcxsj"+yjcxsj.getText().toString()+"yjcxsj;");
		if(menstrualQuantityRadioButton0.isChecked()){mensesHistories.append("menstrualQuantityZero;");}
		if(menstrualQuantityRadioButton1.isChecked()){mensesHistories.append("menstrualQuantityOne;");}
		if(menstrualQuantityRadioButton2.isChecked()){mensesHistories.append("menstrualQuantityTwo;");}
		if(menstrualQuantityRadioButton3.isChecked()){mensesHistories.append("menstrualQuantityThree;");}	
		if(menalgiaRadioButton0.isChecked()){mensesHistories.append("menalgiaOne;");}
		if(menalgiaRadioButton1.isChecked()){mensesHistories.append("menalgiaTwo;");}
		if(menalgiaRadioButton2.isChecked()){mensesHistories.append("menalgiaThree;");}
		return mensesHistories.toString();
	}

	@Override
	public void refreshData() {
		if (yjlRelativeLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String mensesHistories = archiveInfo.getMensesHistories();
			if (TextUtils.isEmpty(mensesHistories)) {
				ccnl.setText("");
				jjnl.setText("");
				yjzq.setText("");
				yjcxsj.setText("");
				for(int i = 0;i < yjlRelativeLayout.getChildCount();i++){
					View item = yjlRelativeLayout.getChildAt(i);
					if (item instanceof RadioButton) {
						((RadioButton) item).setChecked(false);
					}
				}
				for(int i = 0;i < tjRelativeLayout.getChildCount();i++){
					View item = tjRelativeLayout.getChildAt(i);
					if (item instanceof RadioButton) {
						((RadioButton) item).setChecked(false);
					}
				}
			} else {			
				setMensesHistories(mensesHistories);			
			}
		}	
	}

	private void setMensesHistories(String mensesHistories) {
		// TODO Auto-generated method stub
		ccnl.setText(mensesHistories.substring(mensesHistories.indexOf("ccnl")+"ccnl".length(), mensesHistories.indexOf("ccnl;")));
		jjnl.setText(mensesHistories.substring(mensesHistories.indexOf("jjnl")+"jjnl".length(), mensesHistories.indexOf("jjnl;")));
		yjzq.setText(mensesHistories.substring(mensesHistories.indexOf("yjzq")+"yjzq".length(), mensesHistories.indexOf("yjzq;")));
		yjcxsj.setText(mensesHistories.substring(mensesHistories.indexOf("yjcxsj")+"yjcxsj".length(), mensesHistories.indexOf("yjcxsj;")));
		if(mensesHistories.contains("menstrualQuantityZero")){
			menstrualQuantityRadioButton0.setChecked(true);
		}else{
			menstrualQuantityRadioButton0.setChecked(false);
		}
		
		if(mensesHistories.contains("menstrualQuantityOne")){
			menstrualQuantityRadioButton1.setChecked(true);
		}else{
			menstrualQuantityRadioButton1.setChecked(false);
		}
		
		if(mensesHistories.contains("menstrualQuantityTwo")){
			menstrualQuantityRadioButton2.setChecked(true);
		}else{
			menstrualQuantityRadioButton2.setChecked(false);
		}
		if(mensesHistories.contains("menstrualQuantityThree")){
			menstrualQuantityRadioButton3.setChecked(true);
		}else{
			menstrualQuantityRadioButton3.setChecked(false);
		}
		if(mensesHistories.contains("menalgiaOne")){
			menalgiaRadioButton0.setChecked(true);
		}else{
			menalgiaRadioButton0.setChecked(false);
		}
		if(mensesHistories.contains("menalgiaTwo")){
			menalgiaRadioButton1.setChecked(true);
		}else{
			menalgiaRadioButton1.setChecked(false);
		}
		if(mensesHistories.contains("menalgiaThree")){
			menalgiaRadioButton2.setChecked(true);
		}else{
			menalgiaRadioButton2.setChecked(false);
		}
	}
}
