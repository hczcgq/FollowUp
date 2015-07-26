package com.shbestwin.followupmanager.fragment.followup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.followup.FollowUpMentalDisease;
import com.shbestwin.followupmanager.view.widget.IBaseMentalDiseaseBody;

/**
 * 
 * 精神病
 *
 * @version
 */
public class MentalDiseaseFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseMentalDiseaseBody> mentalDiseaseBodyList;

	public static MentalDiseaseFragment newInstance() {
		MentalDiseaseFragment fragment = new MentalDiseaseFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follow_up_mental_disease, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onRenderPage();
	}

	@Override
	public void onRenderPage() {
		super.onRenderPage();
		if (!isRenderedPage) {
			isRenderedPage = true;
			viewStub.inflate();
			// 初始化视图
			initView();
		}
	}

	private void initView() {
		mentalDiseaseBodyList = new ArrayList<IBaseMentalDiseaseBody>();
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody1));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody2));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody3));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody4));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody5));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody6));
		mentalDiseaseBodyList.add((IBaseMentalDiseaseBody) rootView.findViewById(R.id.mentalDiseaseBody7));
		initData();
	}

	@Override
	public void onReset() {
		initData();
	}

	private void initData() {
		FollowUpMentalDisease followUpMentalDisease = MyApplication.getInstance().getFollowUpMentalDisease();
		if(followUpMentalDisease!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpMentalDisease.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseMentalDiseaseBody mentalDiseaseBody : mentalDiseaseBodyList) {
			mentalDiseaseBody.setData(followUpMentalDisease);
			mentalDiseaseBody.setFragment(getChildFragmentManager());
		}
		
		
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}

		FollowUpMentalDisease followUpMentalDisease = MyApplication.getInstance().getFollowUpMentalDisease();
		if (followUpMentalDisease == null) {
			followUpMentalDisease = new FollowUpMentalDisease();

			// 设置体检编号
			followUpMentalDisease.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpMentalDisease.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpMentalDisease.setCreateTime(date);
			followUpMentalDisease.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpMentalDisease.setUpdateTime(date);
		}

		for (IBaseMentalDiseaseBody mentalDiseaseBody : mentalDiseaseBodyList) {
			if (!mentalDiseaseBody.validate()) {
				return;
			}
			mentalDiseaseBody.getData(followUpMentalDisease);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpMentalDisease(followUpMentalDisease);
		ToastUtils.showToast(getActivity(), "保存精神病数据成功！");
		
		AccompanyManager.getInstance(getActivity()).addAccompany(followUpMentalDisease.getGrxx_sfrq(),followUpMentalDisease.getYyqk_xcsfrq(), Accompany.ACNO_MENTALDISEASE);
	}

	@Override
	public void onUpload() {
	}
}
