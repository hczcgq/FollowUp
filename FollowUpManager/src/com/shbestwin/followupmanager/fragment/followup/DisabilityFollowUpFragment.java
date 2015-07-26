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
import com.shbestwin.followupmanager.model.followup.FollowUpDisabledPerson;
import com.shbestwin.followupmanager.view.widget.IBaseDisabilityBody;

/**
 * 
 * 残疾随访
 *
 * @version
 */
public class DisabilityFollowUpFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseDisabilityBody> disabilityBodyList;

	public static DisabilityFollowUpFragment newInstance() {
		DisabilityFollowUpFragment fragment = new DisabilityFollowUpFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follow_up_disability_follow_up, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
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
		disabilityBodyList = new ArrayList<IBaseDisabilityBody>();
		disabilityBodyList.add((IBaseDisabilityBody) rootView.findViewById(R.id.disabilityBody1));
		disabilityBodyList.add((IBaseDisabilityBody) rootView.findViewById(R.id.disabilityBody2));
		disabilityBodyList.add((IBaseDisabilityBody) rootView.findViewById(R.id.disabilityBody3));
		disabilityBodyList.add((IBaseDisabilityBody) rootView.findViewById(R.id.disabilityBody4));
		disabilityBodyList.add((IBaseDisabilityBody) rootView.findViewById(R.id.disabilityBody5));
		initData();
	}

	private void initData() {
		FollowUpDisabledPerson followUpDisabledPerson = MyApplication.getInstance().getFollowUpDisabledPerson();
		if(followUpDisabledPerson!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpDisabledPerson.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseDisabilityBody disabilityBody : disabilityBodyList) {
			disabilityBody.setData(followUpDisabledPerson);
			disabilityBody.setFragment(getChildFragmentManager());
		}
		
		
	}

	@Override
	public void onReset() {
		initData();
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择随访人！");
			return;
		}

		FollowUpDisabledPerson followUpDisabledPerson = MyApplication.getInstance().getFollowUpDisabledPerson();
		if (followUpDisabledPerson == null) {
			followUpDisabledPerson = new FollowUpDisabledPerson();

			// 设置体检编号
			followUpDisabledPerson.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpDisabledPerson.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpDisabledPerson.setCreateTime(date);
			followUpDisabledPerson.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpDisabledPerson.setUpdateTime(date);
		}

		for (IBaseDisabilityBody disabilityBody : disabilityBodyList) {
			if (!disabilityBody.validate()) {
				return;
			}
			disabilityBody.getData(followUpDisabledPerson);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpDisabledPerson(followUpDisabledPerson);
		ToastUtils.showToast(getActivity(), "保存残疾随访数据成功！");
		
		AccompanyManager.getInstance(getActivity()).addAccompany(followUpDisabledPerson.getGrxx_sfrq(),followUpDisabledPerson.getShfszd_xcsfrq(), Accompany.ACNO_DISABILITY);
	}

	@Override
	public void onUpload() {
	}
}
