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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.FamliyHistories;

/**
 * 
 * 家族史
 * 
 * @version
 */

public class FamilyHistoryFragment extends BaseArchiveFragment {

	private RelativeLayout familyHistoryByFather, familyHistoryByMother,
			familyHistoryByBrother, familyHistoryByChild;
	private CheckBox familyHistoryByFather0, familyHistoryByMother0,
			familyHistoryByBrother0, familyHistoryByChild0;
	private CheckBox familyHistoryByFather11, familyHistoryByMother11,
			familyHistoryByBrother11, familyHistoryByChild11;// 其他
	private EditText familyHistoryByFatherEditText,
			familyHistoryByMotherEditText, familyHistoryByBrotherEditText,
			familyHistoryByChildEditText;
	private LinearLayout familyHistoryLayout;
	private boolean isFatherHistory = false, isMotherHistory = false,
			isBrotherHistory = false, isChildHistory = false;

	public static FamilyHistoryFragment newInstance() {
		FamilyHistoryFragment fragment = new FamilyHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_archive_family_history, container, false);
		familyHistoryLayout = (LinearLayout) rootView
				.findViewById(R.id.familyHistoryLayout);

		familyHistoryByFather = (RelativeLayout) rootView
				.findViewById(R.id.familyHistoryByFather);
		familyHistoryByMother = (RelativeLayout) rootView
				.findViewById(R.id.familyHistoryByMother);
		familyHistoryByBrother = (RelativeLayout) rootView
				.findViewById(R.id.familyHistoryByBrother);
		familyHistoryByChild = (RelativeLayout) rootView
				.findViewById(R.id.familyHistoryByChild);

		familyHistoryByFather0 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByFather0);// 无
		familyHistoryByMother0 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByMother0);
		familyHistoryByBrother0 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByBrother0);
		familyHistoryByChild0 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByChild0);

		familyHistoryByFather11 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByFather11);// 其他
		familyHistoryByMother11 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByMother11);
		familyHistoryByBrother11 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByBrother11);
		familyHistoryByChild11 = (CheckBox) rootView
				.findViewById(R.id.familyHistoryByChild11);

		familyHistoryByFatherEditText = (EditText) rootView
				.findViewById(R.id.familyHistoryByFatherEditText);// 其他editText
		familyHistoryByMotherEditText = (EditText) rootView
				.findViewById(R.id.familyHistoryByMotherEditText);
		familyHistoryByBrotherEditText = (EditText) rootView
				.findViewById(R.id.familyHistoryByBrotherEditText);
		familyHistoryByChildEditText = (EditText) rootView
				.findViewById(R.id.familyHistoryByChildEditText);

		familyHistoryByFatherEditText.setEnabled(familyHistoryByFather11
				.isChecked());
		familyHistoryByMotherEditText.setEnabled(familyHistoryByMother11
				.isChecked());
		familyHistoryByBrotherEditText.setEnabled(familyHistoryByBrother11
				.isChecked());
		familyHistoryByChildEditText.setEnabled(familyHistoryByChild11
				.isChecked());
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		familyHistoryByFather0
				.setOnCheckedChangeListener(new MyCheckedChangeListener());
		familyHistoryByMother0
				.setOnCheckedChangeListener(new MyCheckedChangeListener());
		familyHistoryByBrother0
				.setOnCheckedChangeListener(new MyCheckedChangeListener());
		familyHistoryByChild0
				.setOnCheckedChangeListener(new MyCheckedChangeListener());

		familyHistoryByFather11
				.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		familyHistoryByMother11
				.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		familyHistoryByBrother11
				.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		familyHistoryByChild11
				.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());

		refreshData();
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}

		if (isFatherHistory
				&& TextUtils.isEmpty(getCheckBoxText(familyHistoryByFather))) {
			ToastUtils.showToast(getActivity(), "请选择父亲家族史！");
			return false;
		}
		if (isMotherHistory
				&& TextUtils.isEmpty(getCheckBoxText(familyHistoryByMother))) {
			ToastUtils.showToast(getActivity(), "请选择母亲家族史！");
			return false;
		}
		if (isBrotherHistory
				&& TextUtils.isEmpty(getCheckBoxText(familyHistoryByBrother))) {
			ToastUtils.showToast(getActivity(), "请选择兄弟姐妹家族史！");
			return false;
		}
		if (isChildHistory
				&& TextUtils.isEmpty(getCheckBoxText(familyHistoryByChild))) {
			ToastUtils.showToast(getActivity(), "请选择子女家族史！");
			return false;
		}

		if (familyHistoryByFather11.isChecked()
				&& TextUtils.isEmpty(familyHistoryByFatherEditText.getText()
						.toString())) {
			ToastUtils.showToast(getActivity(), "请输入父亲其他疾病！");
			return false;
		}
		if (familyHistoryByMother11.isChecked()
				&& TextUtils.isEmpty(familyHistoryByMotherEditText.getText()
						.toString())) {
			ToastUtils.showToast(getActivity(), "请输入母亲其他疾病！");
			return false;
		}
		if (familyHistoryByBrother11.isChecked()
				&& TextUtils.isEmpty(familyHistoryByBrotherEditText.getText()
						.toString())) {
			ToastUtils.showToast(getActivity(), "请输入兄弟姐妹其他疾病！");
			return false;
		}
		if (familyHistoryByChild11.isChecked()
				&& TextUtils.isEmpty(familyHistoryByChildEditText.getText()
						.toString())) {
			ToastUtils.showToast(getActivity(), "请输入子女其他疾病！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("father", new JsonParser().parse(getCheckBoxText(familyHistoryByFather)).getAsJsonArray());
			jsonObject.add("mother", new JsonParser().parse(getCheckBoxText(familyHistoryByMother)).getAsJsonArray());
			jsonObject.add("brother", new JsonParser().parse(getCheckBoxText(familyHistoryByBrother)).getAsJsonArray());
			jsonObject.add("child", new JsonParser().parse(getCheckBoxText(familyHistoryByChild)).getAsJsonArray());
			Log.e("shaocheng", "jsonObject=" + jsonObject.toString());
			
			archiveInfo.setFamliyHistories(jsonObject.toString());
		}
		return archiveInfo;
	}

	private String getCheckBoxText(View layout) {
		try {
			// TODO Auto-generated method stub
			List<FamliyHistories> famliyHistoriesList = new ArrayList<FamliyHistories>();
			RelativeLayout myLayout = (RelativeLayout) layout;
			EditText historyEditTextOther = (EditText) myLayout.getChildAt(12);
			for (int i = 0; i < myLayout.getChildCount(); i++) {
				FamliyHistories famliyHistories = new FamliyHistories();
				View item = myLayout.getChildAt(i);
				if (item instanceof CheckBox) {
					CheckBox itemCheckBox = (CheckBox) item;
					famliyHistories.setDiseaseNum(String.valueOf(i));
					if (itemCheckBox.isChecked()) {
						if ("其他".equals(itemCheckBox.getText())) {
							famliyHistories.setDiseaseName(historyEditTextOther
									.getText().toString());
						} else {
							famliyHistories.setDiseaseName(itemCheckBox
									.getText().toString());
						}
						famliyHistoriesList.add(famliyHistories);
					}
				}
			}
			Log.i("shaocheng",
					"famliyHistoriesList="
							+ JsonUtil.objectsToJson(famliyHistoriesList));
			return JsonUtil.objectsToJson(famliyHistoriesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		Log.e("shaocheng","refreshData---------");
		if (familyHistoryLayout == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String familyHistories = archiveInfo.getFamliyHistories();
			Log.e("shaocheng","familyHistories=" + familyHistories);
			if(TextUtils.isEmpty(familyHistories) || "[]".equals(familyHistories)){
					isFatherHistory = false;
					setCheckBoxStatus(familyHistoryByFather, true);
					isMotherHistory = false;
					setCheckBoxStatus(familyHistoryByMother, true);
					isBrotherHistory = false;
					setCheckBoxStatus(familyHistoryByBrother, true);
					isChildHistory = false;
					setCheckBoxStatus(familyHistoryByChild, true);
			}else{
				try {
					JsonParser jsonParser = new JsonParser();
					JsonElement jsonEl = jsonParser.parse(familyHistories);
					JsonObject jsonObject = jsonEl.getAsJsonObject();
					Log.e("shaocheng","jsonObject=" + jsonObject);
					//JsonObject jsonObject = new JsonParser().parse(familyHistories).getAsJsonObject();
					JsonArray fatherJsonArray = jsonObject.getAsJsonArray("father");
					JsonArray motherJsonArray = jsonObject.getAsJsonArray("mother");
					JsonArray brotherJsonArray = jsonObject.getAsJsonArray("brother");
					JsonArray childJsonArray = jsonObject.getAsJsonArray("child");
					if(TextUtils.isEmpty(fatherJsonArray.toString())  || "[]".equals(fatherJsonArray.toString())){
						isFatherHistory = false;
						setCheckBoxStatus(familyHistoryByFather, true);
					}else{
						
						List<FamliyHistories> fatherList = JsonUtil.jsonToObjects(fatherJsonArray.toString(), FamliyHistories.class);
						Log.e("shaocheng","fatherJsonArray=" + fatherJsonArray.toString());
						Log.e("shaocheng","fatherList=" + fatherList);
						setCheckBoxText(familyHistoryByFather, fatherList);
					}
					if(TextUtils.isEmpty(motherJsonArray.toString())  || "[]".equals(motherJsonArray.toString())){
						isMotherHistory = false;
						setCheckBoxStatus(familyHistoryByMother, true);
					}else{
						List<FamliyHistories> motherList = JsonUtil.jsonToObjects(motherJsonArray.toString(), FamliyHistories.class);
						setCheckBoxText(familyHistoryByMother, motherList);
					}
					if(TextUtils.isEmpty(brotherJsonArray.toString())  || "[]".equals(brotherJsonArray.toString())){
						isBrotherHistory = false;
						setCheckBoxStatus(familyHistoryByBrother, true);
					}else{
						List<FamliyHistories> brotherList = JsonUtil.jsonToObjects(brotherJsonArray.toString(), FamliyHistories.class);
						setCheckBoxText(familyHistoryByBrother, brotherList);
					}
					if(TextUtils.isEmpty(childJsonArray.toString())  || "[]".equals(childJsonArray.toString())){
						isChildHistory = false;
						setCheckBoxStatus(familyHistoryByChild, true);
					}else{
						List<FamliyHistories> childList = JsonUtil.jsonToObjects(childJsonArray.toString(), FamliyHistories.class);
						setCheckBoxText(familyHistoryByChild, childList);
					}
				} catch (Exception e) {
					Log.e("shaocheng","Exception=" + e.getStackTrace());
					e.printStackTrace();
					
				}
			}
		}
	}

	private void setCheckBoxText(View layout,List<FamliyHistories> famliyList) {
		RelativeLayout myLayout = (RelativeLayout) layout;
	/*	String history = "";
		switch (myLayout.getId()) {
		case R.id.familyHistoryByFather:*/
			for(int i = 0;i < famliyList.size();i++){
				int diseaseNum = Integer.valueOf(famliyList.get(i).getDiseaseNum());
				String diseaseName = famliyList.get(i).getDiseaseName();
				if((View)(myLayout).getChildAt(diseaseNum) instanceof CheckBox){
					CheckBox checkBox = (CheckBox) (View)(myLayout).getChildAt(diseaseNum) ;
					if(diseaseNum == 0){
						setCheckBoxStatus(familyHistoryByFather, true);
						return;
					}else{
						setCheckBoxStatus(familyHistoryByFather, false);
						if(diseaseNum == (myLayout.getChildCount()-2)){
							familyHistoryByFatherEditText.setText(diseaseName);
						}
						checkBox.setChecked(true);
					}
				}
			}

	}

	/**
	 * Description 监听CheckBox其他
	 * 
	 */
	private class OtherCheckedChangeListenerEditText implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.familyHistoryByFather11:
				familyHistoryByFatherEditText.setEnabled(isChecked);
				if (!isChecked) {
					familyHistoryByFatherEditText.setText("");
				}
				break;
			case R.id.familyHistoryByMother11:
				familyHistoryByMotherEditText.setEnabled(isChecked);
				if (!isChecked) {
					familyHistoryByMotherEditText.setText("");
				}
				break;
			case R.id.familyHistoryByBrother11:
				familyHistoryByBrotherEditText.setEnabled(isChecked);
				if (!isChecked) {
					familyHistoryByBrotherEditText.setText("");
				}
				break;
			case R.id.familyHistoryByChild11:
				familyHistoryByChildEditText.setEnabled(isChecked);
				if (!isChecked) {
					familyHistoryByChildEditText.setText("");
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Description 监听CheckBox无
	 * 
	 */
	private class MyCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.familyHistoryByFather0:
				isFatherHistory = !isChecked;
				setCheckBoxStatus(familyHistoryByFather, isChecked);
				break;
			case R.id.familyHistoryByMother0:
				isMotherHistory = !isChecked;
				setCheckBoxStatus(familyHistoryByMother, isChecked);
				break;
			case R.id.familyHistoryByBrother0:
				isBrotherHistory = !isChecked;
				setCheckBoxStatus(familyHistoryByBrother, isChecked);
				break;
			case R.id.familyHistoryByChild0:
				isChildHistory = !isChecked;
				setCheckBoxStatus(familyHistoryByChild, isChecked);
				break;
			default:
				break;
			}

		}
	}

	private void setCheckBoxStatus(RelativeLayout familyHistory,
			boolean isChecked) {
		for (int i = 1; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}

	}
}
