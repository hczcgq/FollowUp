package com.shbestwin.followupmanager.fragment.archive;

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
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class PersonalInfoFragment extends BaseArchiveFragment {
	private Spinner genderSpinner, relationshipSpinner, ethnicSpinner, educationSpinner, bloodTypeSpinner, accountPropertySpinner, marriageSpinner;
	private Spinner RHNegativeSpinner, jobSpinner, nationalitySpinner, assistTypeSpinner, residentTypeSpinner, certificateTypeSpinner;
	private EditText birthdayEditText, workUnitEditText, telephoneEditText, contactNameEditText, contactPhoneEditText;
	private EditText emailEditText, postcodeEditText, familyAddressEditText, residentAddressEditText, managerUnitEditText, createDateEditText;
	private RelativeLayout negativeEventLayout;
	private CheckBox negativeEventOther;
	private EditText negativeEventEditText;

//	private boolean negativeEventOtherIsChecked;

	public static PersonalInfoFragment newInstance() {
		PersonalInfoFragment fragment = new PersonalInfoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_personal_info, container, false);

		genderSpinner = (Spinner) rootView.findViewById(R.id.genderSpinner);
		relationshipSpinner = (Spinner) rootView.findViewById(R.id.relationshipSpinner);
		ethnicSpinner = (Spinner) rootView.findViewById(R.id.ethnicSpinner);
		educationSpinner = (Spinner) rootView.findViewById(R.id.educationSpinner);
		bloodTypeSpinner = (Spinner) rootView.findViewById(R.id.bloodTypeSpinner);
		accountPropertySpinner = (Spinner) rootView.findViewById(R.id.accountPropertySpinner);
		marriageSpinner = (Spinner) rootView.findViewById(R.id.marriageSpinner);
		RHNegativeSpinner = (Spinner) rootView.findViewById(R.id.RHNegativeSpinner);
		jobSpinner = (Spinner) rootView.findViewById(R.id.jobSpinner);
		nationalitySpinner = (Spinner) rootView.findViewById(R.id.nationalitySpinner);
		assistTypeSpinner = (Spinner) rootView.findViewById(R.id.assistTypeSpinner);
		residentTypeSpinner = (Spinner) rootView.findViewById(R.id.residentTypeSpinner);
		certificateTypeSpinner = (Spinner) rootView.findViewById(R.id.certificateTypeSpinner);

		birthdayEditText = (EditText) rootView.findViewById(R.id.birthdayEditText);
		workUnitEditText = (EditText) rootView.findViewById(R.id.workUnitEditText);
		telephoneEditText = (EditText) rootView.findViewById(R.id.telephoneEditText);
		contactNameEditText = (EditText) rootView.findViewById(R.id.contactNameEditText);
		contactPhoneEditText = (EditText) rootView.findViewById(R.id.contactPhoneEditText);
		emailEditText = (EditText) rootView.findViewById(R.id.emailEditText);
		postcodeEditText = (EditText) rootView.findViewById(R.id.postcodeEditText);
		familyAddressEditText = (EditText) rootView.findViewById(R.id.familyAddressEditText);
		residentAddressEditText = (EditText) rootView.findViewById(R.id.residentAddressEditText);
		managerUnitEditText = (EditText) rootView.findViewById(R.id.managerUnitEditText);
		createDateEditText = (EditText) rootView.findViewById(R.id.createDateEditText);

		negativeEventLayout = (RelativeLayout) rootView.findViewById(R.id.negativeEventLayout);
		negativeEventOther = (CheckBox) rootView.findViewById(R.id.negativeEventOther);
		negativeEventEditText = (EditText) rootView.findViewById(R.id.negativeEventEditText);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ViewDataUtil.initOtherCheckboxConstraint(negativeEventOther, negativeEventEditText);

		birthdayEditText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						if ((new Date().getTime() - timeInMillis) < 0) {
							ToastUtils.showToast(getActivity(), "出生日期不能大于当前日期！");
							return;
						}
						birthdayEditText.setText(formatDate);
						datePickerDialog.hide();
					}
				});
			}
		});

		createDateEditText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						createDateEditText.setText(formatDate);
						datePickerDialog.hide();
					}
				});
			}
		});
		refreshData();
	}

	@Override
	public boolean validate() {
		if (TextUtils.isEmpty(birthdayEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "出生日期不能为空！");
			return false;
		}

		if (TextUtils.isEmpty(workUnitEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "工作单位不能为空！");
			return false;
		}

		if (TextUtils.isEmpty(telephoneEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "本人电话不能为空！");
			return false;
		}

		if (TextUtils.isEmpty(familyAddressEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "家庭住址不能为空！");
			return false;
		}

		if (TextUtils.isEmpty(residentAddressEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "户籍地址不能为空！");
			return false;
		}

//		if (ViewDataUtil.validateOtherCheckbox(negativeEventOther, negativeEventEditText)) {
//			ToastUtils.showToast(getActivity(), "请输入其他负性事件！");
//			return false;
//		}

		if (TextUtils.isEmpty(createDateEditText.getText().toString())) {
			ToastUtils.showToast(getActivity(), "建档日期不能为空！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			archiveInfo = new ArchiveInfo();
		}
		archiveInfo.setGender(ViewDataUtil.getSpinnerData(genderSpinner));
		archiveInfo.setRelationship(ViewDataUtil.getSpinnerData(relationshipSpinner));
		archiveInfo.setEthnic(ViewDataUtil.getSpinnerData(ethnicSpinner));
		archiveInfo.setEducation(ViewDataUtil.getSpinnerData(educationSpinner));
		archiveInfo.setBloodType(ViewDataUtil.getSpinnerData(bloodTypeSpinner));
		archiveInfo.setAccountProperty(ViewDataUtil.getSpinnerData(accountPropertySpinner));
		archiveInfo.setMarriage(ViewDataUtil.getSpinnerData(marriageSpinner));

		archiveInfo.setRHNegative(ViewDataUtil.getSpinnerData(RHNegativeSpinner));
		archiveInfo.setJob(ViewDataUtil.getSpinnerData(jobSpinner));
		archiveInfo.setNationality(ViewDataUtil.getSpinnerData(nationalitySpinner));
		archiveInfo.setAssistType(ViewDataUtil.getSpinnerData(assistTypeSpinner));
		archiveInfo.setResidentType(ViewDataUtil.getSpinnerData(residentTypeSpinner));
		archiveInfo.setCertificateType(ViewDataUtil.getSpinnerData(certificateTypeSpinner));

		archiveInfo.setBirthday(birthdayEditText.getText().toString());
		archiveInfo.setWorkUnit(workUnitEditText.getText().toString());
		archiveInfo.setTelephone(telephoneEditText.getText().toString());
		archiveInfo.setContactName(contactNameEditText.getText().toString());
		archiveInfo.setContactPhone(contactPhoneEditText.getText().toString());

		archiveInfo.setEmail(emailEditText.getText().toString());
		archiveInfo.setPostcode(postcodeEditText.getText().toString());
		archiveInfo.setFamilyAddress(familyAddressEditText.getText().toString());
		archiveInfo.setResidentAddress(residentAddressEditText.getText().toString());
		archiveInfo.setManagerUnit(managerUnitEditText.getText().toString());
		archiveInfo.setCreateDate(createDateEditText.getText().toString());

		archiveInfo.setNegativeEvent(getNegativeEvent());

		return archiveInfo;
	}

	private String getNegativeEvent() {
		return ViewDataUtil.getCheckboxData(negativeEventLayout, negativeEventEditText);
	}

	@Override
	public void refreshData() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			resetData();
		} else {
			renderData(archiveInfo);
		}
	}

	private void renderData(ArchiveInfo archiveInfo) {
		ViewDataUtil.setSpinnerData(genderSpinner, archiveInfo.getGender());
		ViewDataUtil.setSpinnerData(relationshipSpinner, archiveInfo.getRelationship());
		ViewDataUtil.setSpinnerData(ethnicSpinner, archiveInfo.getEthnic());
		ViewDataUtil.setSpinnerData(educationSpinner, archiveInfo.getEducation());
		ViewDataUtil.setSpinnerData(bloodTypeSpinner, archiveInfo.getBloodType());
		ViewDataUtil.setSpinnerData(accountPropertySpinner, archiveInfo.getAccountProperty());
		ViewDataUtil.setSpinnerData(marriageSpinner, archiveInfo.getMarriage());
		ViewDataUtil.setSpinnerData(RHNegativeSpinner, archiveInfo.getRHNegative());
		ViewDataUtil.setSpinnerData(jobSpinner, archiveInfo.getJob());
		ViewDataUtil.setSpinnerData(nationalitySpinner, archiveInfo.getNationality());
		ViewDataUtil.setSpinnerData(assistTypeSpinner, archiveInfo.getAssistType());
		ViewDataUtil.setSpinnerData(residentTypeSpinner, archiveInfo.getResidentType());
		ViewDataUtil.setSpinnerData(certificateTypeSpinner, archiveInfo.getCertificateType());
		birthdayEditText.setText(archiveInfo.getBirthday());
		workUnitEditText.setText(archiveInfo.getWorkUnit());
		telephoneEditText.setText(archiveInfo.getTelephone());
		contactNameEditText.setText(archiveInfo.getContactName());
		contactPhoneEditText.setText(archiveInfo.getContactPhone());
		emailEditText.setText(archiveInfo.getEmail());
		postcodeEditText.setText(archiveInfo.getPostcode());
		familyAddressEditText.setText(archiveInfo.getFamilyAddress());
		residentAddressEditText.setText(archiveInfo.getResidentAddress());
		managerUnitEditText.setText(archiveInfo.getManagerUnit());
		createDateEditText.setText(archiveInfo.getCreateDate());

		setNegativeEvent(archiveInfo.getNegativeEvent());
	}

	private void setNegativeEvent(String negativeEvent) {
		ViewDataUtil.setCheckboxData(negativeEventLayout, negativeEventEditText, negativeEvent);
	}

	private void resetData() {
		genderSpinner.setSelection(0);
		relationshipSpinner.setSelection(0);
		ethnicSpinner.setSelection(0);
		educationSpinner.setSelection(0);
		bloodTypeSpinner.setSelection(0);
		accountPropertySpinner.setSelection(0);
		marriageSpinner.setSelection(0);
		RHNegativeSpinner.setSelection(0);
		jobSpinner.setSelection(0);
		nationalitySpinner.setSelection(0);
		assistTypeSpinner.setSelection(0);
		residentTypeSpinner.setSelection(0);
		certificateTypeSpinner.setSelection(0);
		birthdayEditText.setText("");
		workUnitEditText.setText("");
		telephoneEditText.setText("");
		contactNameEditText.setText("");
		contactPhoneEditText.setText("");
		emailEditText.setText("");
		postcodeEditText.setText("");
		familyAddressEditText.setText("");
		residentAddressEditText.setText("");
		managerUnitEditText.setText("");
		// 使用当前日期
		createDateEditText.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

		ViewDataUtil.resetCheckboxData(negativeEventLayout, negativeEventEditText);
	}
}
