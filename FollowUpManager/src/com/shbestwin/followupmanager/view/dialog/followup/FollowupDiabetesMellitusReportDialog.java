package com.shbestwin.followupmanager.view.dialog.followup;

import java.util.Date;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.view.dialog.BaseDialogReportFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: ChildrenInspectionDialog
 * @Description: 儿童访视-实验室检查、辅助检查
 * @author junbin.he
 * @date 2015年2月12日 下午2:29:51
 * 
 */
public class FollowupDiabetesMellitusReportDialog extends
		BaseDialogReportFragment {
	private Spinner genderSpinner, ethnicSpinner, familyHistorySpinner,
	marriySpinner;
	private EditText cardIDEditText, nameEditText, birthdayEditText,
			familyAddressEditText, telephoneEditText, reportTimeEditText,
			reportUnitEdittext, reportDocEdittext, bloodSugeEmptyEditText,
			bloodSugeEatEditText, bloodSugeRandomEditText,
			nextFollowupTimeEditText, descibeEditText;
	private TextView confirmView, cancelView;
	private LinearLayout familyHistoryLayout;

	private EditText diabetesMellitus_other;
	private RelativeLayout diabetesMellitusRelativeLayout;
	private CheckBox diabetesMellitus0, diabetesMellitus14,familyHistory0;

	public static FollowupDiabetesMellitusReportDialog newInstance() {
		FollowupDiabetesMellitusReportDialog dialog = new FollowupDiabetesMellitusReportDialog();
		return dialog;
	}

	public FollowupDiabetesMellitusReportDialog() {

	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View rootView = View.inflate(getActivity(),
				R.layout.dialog_followup_diabetesmellitus_report, null);
		confirmView = (TextView) rootView.findViewById(R.id.confirmView);
		cancelView = (TextView) rootView.findViewById(R.id.cancelView);

		familyHistoryLayout = (LinearLayout) rootView
				.findViewById(R.id.familyHistoryLayout);

		marriySpinner = (Spinner) rootView.findViewById(R.id.marriySpinner);
		genderSpinner = (Spinner) rootView.findViewById(R.id.genderSpinner);
		ethnicSpinner = (Spinner) rootView.findViewById(R.id.ethnicSpinner);
		familyHistorySpinner = (Spinner) rootView
				.findViewById(R.id.familyHistorySpinner);

		cardIDEditText = (EditText) rootView.findViewById(R.id.cardIDEditText);
		nameEditText = (EditText) rootView.findViewById(R.id.nameEditText);
		birthdayEditText = (EditText) rootView
				.findViewById(R.id.birthdayEditText);
		telephoneEditText = (EditText) rootView
				.findViewById(R.id.telephoneEditText);
		familyAddressEditText = (EditText) rootView
				.findViewById(R.id.familyAddressEditText);
		reportTimeEditText = (EditText) rootView
				.findViewById(R.id.reportTimeEditText);
		reportUnitEdittext = (EditText) rootView
				.findViewById(R.id.reportUnitEdittext);
		reportDocEdittext = (EditText) rootView
				.findViewById(R.id.reportDocEdittext);

		bloodSugeEmptyEditText = (EditText) rootView
				.findViewById(R.id.bloodSugeEmptyEditText);
		bloodSugeEatEditText = (EditText) rootView
				.findViewById(R.id.bloodSugeEatEditText);
		bloodSugeRandomEditText = (EditText) rootView
				.findViewById(R.id.bloodPressRandomEditText);
		nextFollowupTimeEditText = (EditText) rootView
				.findViewById(R.id.nextFollowupTimeEditText);
		descibeEditText = (EditText) rootView
				.findViewById(R.id.descibeEditText);

		diabetesMellitusRelativeLayout = (RelativeLayout) rootView
				.findViewById(R.id.diabetesMellitusRelativeLayout);
		diabetesMellitus_other = (EditText) rootView
				.findViewById(R.id.et_other);
		diabetesMellitus0 = (CheckBox) rootView.findViewById(R.id.TOD0);
		diabetesMellitus14 = (CheckBox) rootView.findViewById(R.id.TOD14);
		familyHistory0 = (CheckBox) rootView.findViewById(R.id.familyHistory0);
		initView();
		initDate();
		return rootView;
	}

	private void initView() {
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
		reportTimeEditText.setText(DateUtils.formatDate(new Date(),
				"yyyy-MM-dd"));
		reportTimeEditText.setOnClickListener(new OnClickListener() {
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
								reportTimeEditText.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		nextFollowupTimeEditText.setText(DateUtils.formatDate(new Date(),
				"yyyy-MM-dd"));
		nextFollowupTimeEditText.setOnClickListener(new OnClickListener() {
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
								nextFollowupTimeEditText.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		confirmView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onConfirmClickListener != null) {
					onConfirmClickListener.onConfirmClick();
				}
			}
		});

		cancelView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hide();
				if (null != onCancelClickListener) {
					onCancelClickListener.onCancelClick();
				}
			}
		});

		ViewDataUtil.initOtherCheckboxConstraint(diabetesMellitus14,
				diabetesMellitus_other);

		diabetesMellitus0
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						setCheckBoxStatus(diabetesMellitusRelativeLayout,
								isChecked);
					}
				});
		setCheckBoxStatus(diabetesMellitusRelativeLayout,
				false);
		familyHistory0
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setCheckBoxStatus(familyHistoryLayout,
						isChecked);
			}
		});
		familyHistory0.setChecked(true);
	}

	private void initDate() {

		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			cardIDEditText.setText(archiveInfo.getIdcard());
			nameEditText.setText(archiveInfo.getName());
			ViewDataUtil.setSpinnerData(genderSpinner, archiveInfo.getGender());
			birthdayEditText.setText(archiveInfo.getBirthday());
			telephoneEditText.setText(archiveInfo.getTelephone());
			ViewDataUtil.setSpinnerData(ethnicSpinner, archiveInfo.getEthnic());

		}
	}

	private void setCheckBoxStatus(ViewGroup familyHistory,
			boolean isChecked) {
		for (int i = 1; i < familyHistory.getChildCount(); i++) {
			View item = familyHistory.getChildAt(i);
			if (item instanceof CheckBox) {
				((CheckBox) item).setEnabled(!isChecked);
				if (isChecked) {
					((CheckBox) item).setChecked(!isChecked);
				}
			}
		}
	}
	
	
	
	public ReportDiabetesMellitus getReportDiabetesMellitus(){
		ReportDiabetesMellitus entity=new ReportDiabetesMellitus();
		entity.setReportno(System.currentTimeMillis() + "");
		entity.setIdcard(cardIDEditText.getText().toString());
		entity.setName(nameEditText.getText().toString());
		entity.setSex(ViewDataUtil.getSpinnerData(genderSpinner));
		entity.setBirth(birthdayEditText.getText().toString());
		entity.setContactor(telephoneEditText.getText().toString());
		entity.setNation(ViewDataUtil.getSpinnerData(ethnicSpinner));
		entity.setAddress(familyAddressEditText.getText().toString());
		entity.setMarriy(ViewDataUtil.getSpinnerData(marriySpinner));
		entity.setReport_date(reportTimeEditText.getText().toString());
		entity.setReport_unit(reportUnitEdittext.getText().toString());
		entity.setReport_doctor(reportDocEdittext.getText().toString());
		entity.setEmpty_bloodsuger(bloodSugeEmptyEditText.getText().toString());
		entity.setAfter_bloodsuger(bloodSugeEatEditText.getText().toString());
		entity.setRondam_bloodsuger(bloodSugeRandomEditText.getText().toString());
		entity.setExperience(ViewDataUtil.getCheckboxData(diabetesMellitusRelativeLayout, diabetesMellitus_other));
		entity.setHistory_number(ViewDataUtil.getSpinnerData(familyHistorySpinner));
		entity.setHistory_msg(ViewDataUtil.getCheckboxData(familyHistoryLayout, null));
		entity.setNext_followup_date(nextFollowupTimeEditText.getText().toString());
		entity.setDescribe(descibeEditText.getText().toString());
		return entity;
	}

	private OnConfirmClickListener onConfirmClickListener;

	public interface OnConfirmClickListener {
		public void onConfirmClick();
	}

	public void setOnConfirmClickListener(
			OnConfirmClickListener onConfirmClickListener) {
		this.onConfirmClickListener = onConfirmClickListener;
	}

	public interface OnCancelClickListener {
		public void onCancelClick();
	}

	private OnCancelClickListener onCancelClickListener;

	public void setOnCancelClickListener(
			OnCancelClickListener onCancelClickListener) {
		this.onCancelClickListener = onCancelClickListener;
	}

}
