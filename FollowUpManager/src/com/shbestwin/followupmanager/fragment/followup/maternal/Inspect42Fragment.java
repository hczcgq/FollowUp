package com.shbestwin.followupmanager.fragment.followup.maternal;

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
import com.shbestwin.followupmanager.model.followup.FollowUpFortyTwo;
import com.shbestwin.followupmanager.view.widget.IBaseInspect42Body;

/**
 * 
 * @ClassName: Inspect42Fragment
 * @Description: 42天检查
 * 
 */
public class Inspect42Fragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private ArrayList<IBaseInspect42Body> inspect42BodyList;

	public static Inspect42Fragment newInstance() {
		Inspect42Fragment fragment = new Inspect42Fragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_maternal_inspect_42, container, false);
		viewStub = (ViewStub) rootView.findViewById(R.id.viewStub);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
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
		inspect42BodyList = new ArrayList<IBaseInspect42Body>();
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body1));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body2));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body3));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body4));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body5));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body6));
		inspect42BodyList.add((IBaseInspect42Body) rootView.findViewById(R.id.inspect42Body7));
		initData();
	}

	private void initData() {
		FollowUpFortyTwo followUpFortyTwo = MyApplication.getInstance().getFollowUpFortyTwo();
		if(followUpFortyTwo!=null){
			String numberNo=new SimpleDateFormat("yyyyMMdd").format(new Date())+MyApplication.getInstance().getArchiveInfo().getIdcard();
			if(numberNo.equals(followUpFortyTwo.getFollowUpNo())){
				 MyApplication.getInstance().setFollowUpNo(numberNo);
			}
		}
		for (IBaseInspect42Body inspect42Body : inspect42BodyList) {
			inspect42Body.setData(followUpFortyTwo);
			inspect42Body.setFragment(getChildFragmentManager());
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

		FollowUpFortyTwo followUpFortyTwo = MyApplication.getInstance().getFollowUpFortyTwo();
		if (followUpFortyTwo == null) {
			followUpFortyTwo = new FollowUpFortyTwo();

			// 设置体检编号
			followUpFortyTwo.setFollowUpNo(MyApplication.getInstance().getFollowUpNo());
			// 设置身份证号
			followUpFortyTwo.setIdcard(archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			followUpFortyTwo.setCreateTime(date);
			followUpFortyTwo.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			followUpFortyTwo.setUpdateTime(date);
		}

		for (IBaseInspect42Body inspect42Body : inspect42BodyList) {
			if (!inspect42Body.validate()) {
				return;
			}
			inspect42Body.getData(followUpFortyTwo);
		}

		// 保存数据
		FollowUpManager.getInstance(getActivity()).saveOrUpdateFollowUpFortyTwo(followUpFortyTwo);
		ToastUtils.showToast(getActivity(), "保存孕产访视42天检查数据成功！");
		AccompanyManager.getInstance(getActivity()).addAccompany(followUpFortyTwo.getGrxx_sfrq(),followUpFortyTwo.getZz_xcsfrq(), Accompany.ACNO_INSPECT42);
	}

	@Override
	public void onUpload() {
	}

}
