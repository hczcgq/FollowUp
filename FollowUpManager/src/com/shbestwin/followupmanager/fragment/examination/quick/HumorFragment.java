package com.shbestwin.followupmanager.fragment.examination.quick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;

/**
 * 
 * 体液
 *
 * @version
 */
public class HumorFragment extends BaseQuickExaminationFragment {

	public static HumorFragment newInstance() {
		HumorFragment fragment = new HumorFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_quick_examination_humor, container, false);
		return rootView;
	}

	@Override
	public String getPrintData(String examinationNo) {
//		String printStr = ExaminationManager.getInstance(getActivity()).getPrintTemplate(R.raw.print_, examinationNo);
		// 替换相关数据
		return null;
	}

	@Override
	public void getSaveData(ExaminationInfo examinationInfo) {
	}

    @Override
    public void setSaveData(ExaminationInfo examinationInfo) {
        // TODO Auto-generated method stub
        
    }
}
