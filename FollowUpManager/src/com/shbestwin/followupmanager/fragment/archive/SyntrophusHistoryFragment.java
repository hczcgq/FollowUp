package com.shbestwin.followupmanager.fragment.archive;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint.Join;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.ExposureHistories;
import com.shbestwin.followupmanager.model.archive.SyntrophusHistories;

/**
 * 
 * 遗传病史
 *
 * @version
 */
public class SyntrophusHistoryFragment extends BaseArchiveFragment {
	private RadioGroup heredopathiaRadioGroup;

	private EditText heredopathiaNameTextView;

	private boolean isSyntrophus = false;

	public static SyntrophusHistoryFragment newInstance() {
		SyntrophusHistoryFragment fragment = new SyntrophusHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_syntrophus_history, container, false);
		heredopathiaRadioGroup = (RadioGroup) rootView.findViewById(R.id.heredopathiaRadioGroup);
		heredopathiaNameTextView = (EditText) rootView.findViewById(R.id.heredopathiaNameTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		heredopathiaRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.heredopathiaRadioButton0) {
					heredopathiaNameTextView.setEnabled(false);
					heredopathiaNameTextView.setText("");
					isSyntrophus = false;
				} else {
					heredopathiaNameTextView.setEnabled(true);
					isSyntrophus = true;
				}
			}
		});
		refreshData();

	}

	@Override
	public void onRenderPage() {
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}

		String heredopathiaName = heredopathiaNameTextView.getText().toString();
		if (isSyntrophus && TextUtils.isEmpty(heredopathiaName)) {
			ToastUtils.showToast(getActivity(), "请输入疾病名称！");
			return false;
		}

		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String heredopathiaName = getHeredopathiaName();
			//heredopathiaName = heredopathiaNameTextView.getText().toString();
			archiveInfo.setSyntrophusHistories(heredopathiaName);
		}
		return archiveInfo;
	}

	private String getHeredopathiaName() {
		// TODO Auto-generated method stub
		List<SyntrophusHistories> syntrophusHistoriesList = new ArrayList<SyntrophusHistories>();
		SyntrophusHistories syntrophusHistories = new SyntrophusHistories();
		if(isSyntrophus){
			syntrophusHistories.setPastNum("1");
			syntrophusHistories.setPastName(heredopathiaNameTextView.getText().toString());
		}else{
			syntrophusHistories.setPastNum("0");
			syntrophusHistories.setPastName("");
		}
		syntrophusHistoriesList.add(syntrophusHistories);
		try {
			return JsonUtil.objectsToJson(syntrophusHistoriesList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		if (heredopathiaRadioGroup == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String syntrophusHistories = archiveInfo.getSyntrophusHistories();
			Log.i("cjl", "遗传病--"+syntrophusHistories);
			if (TextUtils.isEmpty(syntrophusHistories) || "[]".equals(syntrophusHistories)) {
				isSyntrophus = false;
				heredopathiaRadioGroup.check(R.id.heredopathiaRadioButton0);
				heredopathiaNameTextView.setEnabled(false);
				heredopathiaNameTextView.setText("");
			} else {
				isSyntrophus = true;
				try {
					List<SyntrophusHistories> syntrophusHistoriesList = JsonUtil.jsonToObjects(syntrophusHistories, SyntrophusHistories.class);
					SyntrophusHistories mySyntrophusHistories = syntrophusHistoriesList.get(0);
					heredopathiaRadioGroup.check(R.id.heredopathiaRadioButton1);
					heredopathiaNameTextView.setEnabled(true);
					heredopathiaNameTextView.setText(mySyntrophusHistories.getPastName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
