package com.shbestwin.followupmanager.fragment.report;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class HypertensionReportFragment1 extends BaseFragment {
	private Spinner genderSpinner, ethnicSpinner, marriageSpinner, workSpinner,
			jobSpinner, educationSpinner;
	private EditText cardIDEditText, nameEditText, birthdayEditText,
			telephoneEditText, familyAddressEditText, residentAddressEditText,
			companyEditText;
	private RelativeLayout insuranceCategoryLayout;

	public static HypertensionReportFragment1 newInstance() {
		HypertensionReportFragment1 fragment = new HypertensionReportFragment1();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_followup_report_hypertension1, container,
				false);

		genderSpinner = (Spinner) rootView.findViewById(R.id.genderSpinner);
		ethnicSpinner = (Spinner) rootView.findViewById(R.id.ethnicSpinner);
		educationSpinner = (Spinner) rootView
				.findViewById(R.id.educationSpinner);
		workSpinner = (Spinner) rootView.findViewById(R.id.workSpinner);
		jobSpinner = (Spinner) rootView.findViewById(R.id.jobSpinner);
		marriageSpinner = (Spinner) rootView.findViewById(R.id.marriageSpinner);

		cardIDEditText = (EditText) rootView.findViewById(R.id.cardIDEditText);
		nameEditText = (EditText) rootView.findViewById(R.id.nameEditText);
		birthdayEditText = (EditText) rootView
				.findViewById(R.id.birthdayEditText);
		telephoneEditText = (EditText) rootView
				.findViewById(R.id.telephoneEditText);
		familyAddressEditText = (EditText) rootView
				.findViewById(R.id.familyAddressEditText);
		residentAddressEditText = (EditText) rootView
				.findViewById(R.id.residentAddressEditText);
		companyEditText = (EditText) rootView
				.findViewById(R.id.companyEditText);
		insuranceCategoryLayout = (RelativeLayout) rootView
				.findViewById(R.id.insuranceCategoryLayout);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		birthdayEditText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(getChildFragmentManager(),
						"datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								if ((new Date().getTime() - timeInMillis) < 0) {
									ToastUtils.showToast(getActivity(),
											"出生日期不能大于当前日期！");
									return;
								}
								birthdayEditText.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			cardIDEditText.setText(archiveInfo.getIdcard());
			nameEditText.setText(archiveInfo.getName());
			ViewDataUtil.setSpinnerData(genderSpinner, archiveInfo.getGender());
			birthdayEditText.setText(archiveInfo.getBirthday());
			telephoneEditText.setText(archiveInfo.getTelephone());
			ViewDataUtil.setSpinnerData(ethnicSpinner, archiveInfo.getEthnic());
			familyAddressEditText.setText(archiveInfo.getFamilyAddress());
			residentAddressEditText.setText(archiveInfo.getResidentAddress());
			ViewDataUtil.setSpinnerData(marriageSpinner,
					archiveInfo.getMarriage());
			ViewDataUtil.setSpinnerData(jobSpinner, archiveInfo.getJob());
			ViewDataUtil.setSpinnerData(educationSpinner,
					archiveInfo.getEducation());
			companyEditText.setText(archiveInfo.getWorkUnit());
			setCategoryName(archiveInfo.getCategoryName());

		}
	}

	private void setCategoryName(String insuranceType) {

		ArrayList<CheckBox> itemCheckBoxs = new ArrayList<CheckBox>();
		for (int i = 0; i < insuranceCategoryLayout.getChildCount(); i++) {
			View item = insuranceCategoryLayout.getChildAt(i);
			if (item instanceof CheckBox) {
				itemCheckBoxs.add((CheckBox) item);
			}
		}

		String selectedItems[] = null;
		int selectedCount = -1;
		if (!TextUtils.isEmpty(insuranceType)) {
			selectedItems = insuranceType.split(";");
		}

		String selectedItemText = "";
		int selectedItemIndex = -1;
		if (!ArrayUtils.isEmpty(selectedItems)) {
			selectedItemText = selectedItems[0];
			selectedItemIndex = 0;
			selectedCount = selectedItems.length;
		}

		for (CheckBox itemCheckBox : itemCheckBoxs) {
			String itemText = itemCheckBox.getText().toString();
			if (TextUtils.isEmpty(selectedItemText)) {
				itemCheckBox.setChecked(false);
			} else {
				if (selectedItemText.equals(itemText)) {
					itemCheckBox.setChecked(true);
					if (++selectedItemIndex < selectedCount) {
						selectedItemText = selectedItems[selectedItemIndex];
					} else {
						selectedItemText = "";
					}
				} else {
					itemCheckBox.setChecked(false);
				}
			}
		}

	}
}
