package com.shbestwin.followupmanager.view.dialog.followup;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.device.BloodPressure;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.report.ReportHyoertension;
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
public class FollowupHypertensionReportDialog extends BaseDialogReportFragment {
	private Spinner genderSpinner, ethnicSpinner, familyHistorySpinner;
	private EditText cardIDEditText, nameEditText, birthdayEditText,
			telephoneEditText, reportTimeEditText, reportUnitEdittext,
			reportDocEdittext, bloodPressCheckEditText1,
			bloodPressCheckEditText2, bloodPressLeveEditText,
			bloodPressTypeEditText, heightEditText, weightEditText,
			pluseEditText, bloodPressEditText1, bloodPressEditText2,
			nextFollowupTimeEditText, descibeEditText;
	private TextView confirmView, cancelView;
	private LinearLayout familyHistoryLayout;
	private BloodPressure bloodPressure;

	public static FollowupHypertensionReportDialog newInstance() {
		FollowupHypertensionReportDialog dialog = new FollowupHypertensionReportDialog();
		return dialog;
	}

	public FollowupHypertensionReportDialog() {

	}
	public FollowupHypertensionReportDialog(BloodPressure bloodPressure) {
	    this.bloodPressure=bloodPressure;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View rootView = View.inflate(getActivity(),
				R.layout.dialog_followup_hypertension_report, null);
		confirmView = (TextView) rootView.findViewById(R.id.confirmView);
		cancelView = (TextView) rootView.findViewById(R.id.cancelView);

		familyHistoryLayout = (LinearLayout) rootView
				.findViewById(R.id.familyHistoryLayout);

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
		reportTimeEditText = (EditText) rootView
				.findViewById(R.id.reportTimeEditText);
		reportUnitEdittext = (EditText) rootView
				.findViewById(R.id.reportUnitEdittext);
		reportDocEdittext = (EditText) rootView
				.findViewById(R.id.reportDocEdittext);

		bloodPressCheckEditText1 = (EditText) rootView
				.findViewById(R.id.bloodPressCheckEditText1);
		bloodPressCheckEditText2 = (EditText) rootView
				.findViewById(R.id.bloodPressCheckEditText2);
		bloodPressLeveEditText = (EditText) rootView
				.findViewById(R.id.bloodPressLeveEditText);
		bloodPressTypeEditText = (EditText) rootView
				.findViewById(R.id.bloodPressTypeEditText);
		heightEditText = (EditText) rootView.findViewById(R.id.heightEditText);
		weightEditText = (EditText) rootView.findViewById(R.id.weightEditText);
		pluseEditText = (EditText) rootView.findViewById(R.id.pluseEditText);
		bloodPressEditText1 = (EditText) rootView
				.findViewById(R.id.bloodPressEditText1);
		bloodPressEditText2 = (EditText) rootView
				.findViewById(R.id.bloodPressEditText2);
		nextFollowupTimeEditText = (EditText) rootView
				.findViewById(R.id.nextFollowupTimeEditText);
		descibeEditText = (EditText) rootView
				.findViewById(R.id.descibeEditText);
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
		reportTimeEditText.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
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
		
		nextFollowupTimeEditText.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
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
			
			ExaminationInfo examinationInfo = MyApplication.getInstance().getExaminationInfo();
			if(examinationInfo!=null) {
				String routineCheckups = examinationInfo.getRoutineCheckups();
				if (!TextUtils.isEmpty(routineCheckups)) {
					try {
						JSONObject jsonObject = new JSONObject(routineCheckups);
						if (!TextUtils.isEmpty(jsonObject.getString("height"))) {
							heightEditText.setText(jsonObject.getString("height"));
						}
						if (!TextUtils.isEmpty(jsonObject.getString("weight"))) {
							weightEditText.setText(jsonObject.getString("weight"));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			
			if(bloodPressure!=null) {
			    bloodPressCheckEditText1.setText(bloodPressure.getSystolicPressure()+"");
			    bloodPressCheckEditText2.setText(bloodPressure.getDiastolicPressure()+"");
			    bloodPressEditText1.setText(bloodPressure.getSystolicPressure()+"");
			    bloodPressEditText2.setText(bloodPressure.getDiastolicPressure()+"");
			    pluseEditText.setText(bloodPressure.getPulseRate()+"");
			    bloodPressTypeEditText.setText(bloodPressure.getConclusion());
			}
		}
	}
	
	
	public ReportHyoertension getReportHyoertension(){
		ReportHyoertension entity=new ReportHyoertension();
		entity.setReportno(new SimpleDateFormat("yyyyMMdd")
        .format(new Date()) + cardIDEditText.getText());
		entity.setIdcard(cardIDEditText.getText().toString());
		entity.setName(nameEditText.getText().toString());
		entity.setSex(ViewDataUtil.getSpinnerData(genderSpinner));
		entity.setBirth(birthdayEditText.getText().toString());
		entity.setContactor(telephoneEditText.getText().toString());
		entity.setNation(ViewDataUtil.getSpinnerData(ethnicSpinner));
		entity.setReport_date(reportTimeEditText.getText().toString());
		entity.setReport_unit(reportUnitEdittext.getText().toString());
		entity.setReport_doctor(reportDocEdittext.getText().toString());
		entity.setCheck_bloodpress(bloodPressCheckEditText1.getText().toString()+"/"+bloodPressCheckEditText2.getText().toString());
		entity.setBloodpress_level(bloodPressLeveEditText.getText().toString());
		entity.setBloodpress_type(bloodPressTypeEditText.getText().toString());
		entity.setWeight(weightEditText.getText().toString());
		entity.setHeight(heightEditText.getText().toString());
		entity.setPluse(pluseEditText.getText().toString());
		entity.setBloodpress(bloodPressEditText1.getText().toString()+"/"+bloodPressEditText2.getText().toString());
		entity.setHistory_number(ViewDataUtil.getSpinnerData(familyHistorySpinner));
		entity.setHistory_msg(ViewDataUtil.getCheckboxData(familyHistoryLayout, null));
		entity.setNext_followup_date(nextFollowupTimeEditText.getText().toString());
		entity.setDescribe(descibeEditText.getText().toString());
		entity.setUpdateTime(System.currentTimeMillis() + "");
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
