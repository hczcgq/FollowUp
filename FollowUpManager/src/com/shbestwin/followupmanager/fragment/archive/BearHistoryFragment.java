package com.shbestwin.followupmanager.fragment.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.BearHistories;

/**
 * 
 * @ClassName: BearHistoryFragment
 * @Description: 生育史
 *
 */
public class BearHistoryFragment extends BaseArchiveFragment {

	private EditText childrenNumEditText,fetusNumEditText,birthNumEditText,streamNumEditText,odinopoeiaNumEditText;
	private RadioButton contraceptiveRadioButton0;
	private RelativeLayout bearHistoryRelativeLayout;
	public static BearHistoryFragment newInstance() {
		BearHistoryFragment fragment = new BearHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_bear_history, container, false);
		bearHistoryRelativeLayout = (RelativeLayout)rootView.findViewById(R.id.bearHistoryRelativeLayout);
		
		childrenNumEditText = (EditText)rootView.findViewById(R.id.childrenNumEditText);
		fetusNumEditText = (EditText)rootView.findViewById(R.id.fetusNumEditText);
		birthNumEditText = (EditText)rootView.findViewById(R.id.birthNumEditText);
		streamNumEditText = (EditText)rootView.findViewById(R.id.streamNumEditText);
		odinopoeiaNumEditText = (EditText)rootView.findViewById(R.id.odinopoeiaNumEditText);
		contraceptiveRadioButton0 = (RadioButton)rootView.findViewById(R.id.contraceptiveRadioButton0);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		contraceptiveRadioButton0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					for (int i = 0; i < bearHistoryRelativeLayout.getChildCount(); i++) {
						View item = bearHistoryRelativeLayout.getChildAt(i);
						if (item instanceof RadioButton) {
							if(!(item.getId() == R.id.contraceptiveRadioButton0)){
								((RadioButton) item).setChecked(false);
							}
						}
					}
				}
			}
		});
		
		for (int i = 0; i < bearHistoryRelativeLayout.getChildCount(); i++) {
			View item = bearHistoryRelativeLayout.getChildAt(i);
			if (item instanceof RadioButton) {
				if(!(item.getId() == R.id.contraceptiveRadioButton0)){
					((RadioButton) item).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							// TODO Auto-generated method stub
							if(isChecked){
								contraceptiveRadioButton0.setChecked(false);
							}
						}
					});
				}
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
		if(TextUtils.isEmpty(childrenNumEditText.getText())){
			ToastUtils.showToast(getActivity(), "请填写现有子女人数！");
			return false;
		}
		if(TextUtils.isEmpty(fetusNumEditText.getText())){
			ToastUtils.showToast(getActivity(), "请填写胎次！");
			return false;
		}
		if(TextUtils.isEmpty(birthNumEditText.getText())){
			ToastUtils.showToast(getActivity(), "请填写产次！");
			return false;
		}
		if(TextUtils.isEmpty(streamNumEditText.getText())){
			ToastUtils.showToast(getActivity(), "请填写产次！");
			return false;
		}
		if(TextUtils.isEmpty(odinopoeiaNumEditText.getText())){
			ToastUtils.showToast(getActivity(), "请填写引产！");
			return false;
		}
		return true;	
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String bearHistories = "";
			bearHistories = getBearHistories();
			archiveInfo.setBearHistories(bearHistories);
		}
		return archiveInfo;
	}

	private String getBearHistories() {
		BearHistories bearHistories = new BearHistories();
		bearHistories.setChildrenNum(childrenNumEditText.getText().toString());
		bearHistories.setFetusNum(fetusNumEditText.getText().toString());
		bearHistories.setBirthNum(birthNumEditText.getText().toString());
		bearHistories.setStreamNum(streamNumEditText.getText().toString());
		bearHistories.setOdinopoeiaNum(odinopoeiaNumEditText.getText().toString());
		for(int i = 0;i < bearHistoryRelativeLayout.getChildCount();i++){
			View item = bearHistoryRelativeLayout.getChildAt(i);
			if(item instanceof RadioButton){
				if(((RadioButton)item).isChecked()){
					bearHistories.setContraceptive(((RadioButton)item).getText().toString());
				}
			}
		}
		
		try {
			Log.i("cjl", "get--"+new JsonUtil().objectToJson(bearHistories));
			return new JsonUtil().objectToJson(bearHistories);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		if (bearHistoryRelativeLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String bearHistories = archiveInfo.getBearHistories();
			if(bearHistories != null){
				Log.i("cjl","yoyo"+bearHistories);
			}
			if (TextUtils.isEmpty(bearHistories) ||"[]".equals(bearHistories) ) {
				childrenNumEditText.setText("");
				fetusNumEditText.setText("");
				birthNumEditText.setText("");
				streamNumEditText.setText("");
				odinopoeiaNumEditText.setText("");
				contraceptiveRadioButton0.setChecked(true);
			} else {
				contraceptiveRadioButton0.setChecked(false);				
				setBearHistories(bearHistories);			
			}
		}
		
	}

	private void setBearHistories(String bearHistories) {
		
		BearHistories mbearHistories = new BearHistories();
		try {
			mbearHistories = (BearHistories) new JsonUtil().jsonToObject(bearHistories, BearHistories.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		childrenNumEditText.setText(mbearHistories.getChildrenNum());
		fetusNumEditText.setText(mbearHistories.getFetusNum());
		birthNumEditText.setText(mbearHistories.getBirthNum());
		streamNumEditText.setText(mbearHistories.getStreamNum());
		odinopoeiaNumEditText.setText(mbearHistories.getOdinopoeiaNum());
		
		for(int i = 0;i < bearHistoryRelativeLayout.getChildCount();i++){
			View item = bearHistoryRelativeLayout.getChildAt(i);
			if(item instanceof RadioButton){
				if(((RadioButton)item).getText().toString().equals(mbearHistories.getContraceptive())){
					((RadioButton)item).setChecked(true);
				}else{
					((RadioButton)item).setChecked(false);
				}
			}
		}
	}
}
