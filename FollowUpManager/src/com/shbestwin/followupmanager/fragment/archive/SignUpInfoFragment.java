package com.shbestwin.followupmanager.fragment.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 签约情况
 *
 * @version
 */
public class SignUpInfoFragment extends BaseArchiveFragment {
	private RadioGroup signUpRadioGroup;

	private EditText signUpDateTextView;

	private boolean isSignUp = false;

	public static SignUpInfoFragment newInstance() {
		SignUpInfoFragment fragment = new SignUpInfoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_sign_up_info, container, false);
		signUpRadioGroup = (RadioGroup) rootView.findViewById(R.id.signUpRadioGroup);
		signUpDateTextView = (EditText) rootView.findViewById(R.id.signUpDateTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		signUpRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.signUpNoRadioButton) {
					signUpDateTextView.setEnabled(false);
					signUpDateTextView.setText("");
					isSignUp = false;
				} else {
					signUpDateTextView.setEnabled(true);
					isSignUp = true;
				}
			}
		});

		signUpDateTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						signUpDateTextView.setText(formatDate);
						datePickerDialog.hide();
					}
				});
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

		String signUpDate = signUpDateTextView.getText().toString();
		if (isSignUp && TextUtils.isEmpty(signUpDate)) {
			ToastUtils.showToast(getActivity(), "请选择签约日期！");
			return false;
		}

		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String signUpDate = signUpDateTextView.getText().toString();
			archiveInfo.setSignUpDate(signUpDate);
		}
		return archiveInfo;
	}

	@Override
	public void refreshData() {
		if (signUpRadioGroup == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String signUpDate = archiveInfo.getSignUpDate();
			if (TextUtils.isEmpty(signUpDate)) {
				isSignUp = false;
				signUpRadioGroup.check(R.id.signUpNoRadioButton);
				signUpDateTextView.setEnabled(false);
				signUpDateTextView.setText("");
			} else {
				isSignUp = true;
				signUpRadioGroup.check(R.id.signUpYesRadioButton);
				signUpDateTextView.setEnabled(true);
				signUpDateTextView.setText(signUpDate);
			}
		}
	}
}
