package com.shbestwin.followupmanager.fragment.examination.quick;

import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;

public abstract class BaseQuickExaminationFragment extends BaseFragment {
	public abstract String getPrintData(String examinationNo);

	public abstract void getSaveData(ExaminationInfo examinationInfo);
	
	public abstract void setSaveData(ExaminationInfo examinationInfo);
}
