package com.shbestwin.followupmanager.fragment.examination;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;
import com.shbestwin.followupmanager.view.widget.IBaseGeneralExaminationBody;

/**
 * 
 * 普通体检
 *
 * @version
 */
public class GeneralExaminationFragment extends BaseFragment {
	private View rootView;
	private boolean isRenderedPage = false;
	private ViewStub viewStub;

	private EditText zrysEditText, tjrqEditText;// 责任医师

	private ArrayList<IBaseGeneralExaminationBody> generalExaminationBodyList;

	public static GeneralExaminationFragment newInstance() {
		GeneralExaminationFragment fragment = new GeneralExaminationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_examination_general_examination, container, false);
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
		zrysEditText = (EditText) rootView.findViewById(R.id.zrysEditText);
		tjrqEditText = (EditText) rootView.findViewById(R.id.tjrqEditText);

		// 使用当前日期
		tjrqEditText.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

		tjrqEditText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						tjrqEditText.setText(formatDate);
						datePickerDialog.hide();
					}
				});
			}
		});

		generalExaminationBodyList = new ArrayList<IBaseGeneralExaminationBody>();
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody1));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody2));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody3));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody4));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody5));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody6));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody7));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody8));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody9));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody10));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody11));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody12));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody13));
		generalExaminationBodyList.add((IBaseGeneralExaminationBody) rootView.findViewById(R.id.generalExaminationBody14));

		GeneralExamination generalExamination = MyApplication.getInstance().getGeneralExamination();
		if (generalExamination != null) {
			zrysEditText.setText(generalExamination.getZrys());
			tjrqEditText.setText(generalExamination.getTjrq());
			for (IBaseGeneralExaminationBody generalExaminationBody : generalExaminationBodyList) {
				generalExaminationBody.setData(generalExamination);
			}
		}
	}

	@Override
	public void onSave() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先到档案信息中选择体检人！");
			return;
		}
		
		GeneralExamination generalExamination = MyApplication.getInstance().getGeneralExamination();
		if (generalExamination == null) {
			generalExamination = new GeneralExamination();
			// 身份证号，标示用户的唯一ID
			generalExamination.setIdcard(archiveInfo.getIdcard());
			// 设置体检编号
			generalExamination.setExaminationNo(new SimpleDateFormat("yyyyMMdd").format(new Date())+archiveInfo.getIdcard());

			String date = System.currentTimeMillis() + "";
			generalExamination.setCreateTime(date);
			generalExamination.setUpdateTime(date);
		} else {
			String date = System.currentTimeMillis() + "";
			generalExamination.setUpdateTime(date);
		}

		generalExamination.setZrys(zrysEditText.getText().toString());
		generalExamination.setTjrq(tjrqEditText.getText().toString());

		for (IBaseGeneralExaminationBody generalExaminationBody : generalExaminationBodyList) {

			generalExaminationBody.getData(generalExamination);
		}

		// 保存数据
		ExaminationManager.getInstance(getActivity()).saveOrUpdateGeneralExamination(generalExamination);
		ToastUtils.showToast(getActivity(), "保存普通体检成功！");
	}
}
