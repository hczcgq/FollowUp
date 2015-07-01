package com.shbestwin.followupmanager.view.dialog.archive;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.archive.FamilyProblem;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: FamilyProblemDialog
 * @Description: 家庭问题增加和修改对话框
 * @author junbin.he
 * @date 2015年2月25日 下午2:52:39
 *
 */
public class FamilyProblemDialog extends BaseDialogFragment {

	private Spinner problemPhaseSpinner;
	private EditText happenNameEditText, happenDateEditText, problemEvaluateEditText, mainProblemEditText, handleResultEditText,
	subjectDataEditText, objectiveDataEditText, otherDataEditText,
	managementPlanEditText, recordDoctorEditText, recordDateEditText, remarkEditText;
	private String jd = null,fsr = null,fsrq = null,zywt = null,wtpg = null,cljjg = null,
			zgzl = null,kgzl = null,qt = null,gljh = null,jlys = null,jlrq = null,bz = null;
	private FamilyProblem familyProblem;
	private boolean editEnable = true;
	public FamilyProblemDialog(){
		
	}
	
	public FamilyProblemDialog(boolean editEnable,String jd, String fsr, String fsrq,
			String zywt, String wtpg, String cljjg, String zgzl, String kgzl,
			String qt, String gljh, String jlys, String jlrq, String bz) {
		// TODO Auto-generated constructor stub
		this.editEnable = editEnable;
		this.jd = jd;
		this.fsr = fsr;
		this.fsrq = fsrq;
		this.zywt = zywt;
		this.wtpg = wtpg;
		this.cljjg = cljjg;
		this.zgzl = zgzl;
		this.kgzl = kgzl;
		this.qt = qt;
		this.gljh = gljh;
		this.jlys = jlys;
		this.jlrq = jlrq;
		this.bz = bz;
	}

	public static FamilyProblemDialog newInstance() {
		FamilyProblemDialog dialog = new FamilyProblemDialog();
		return dialog;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_archive_family_problem, null);
		problemPhaseSpinner = (Spinner) bodyView.findViewById(R.id.problemPhaseSpinner);
		happenNameEditText = (EditText) bodyView.findViewById(R.id.happenNameEditText);
		happenDateEditText = (EditText) bodyView.findViewById(R.id.happenDateEditText);
		problemEvaluateEditText = (EditText) bodyView.findViewById(R.id.problemEvaluateEditText);
		mainProblemEditText = (EditText) bodyView.findViewById(R.id.mainProblemEditText);
		handleResultEditText = (EditText) bodyView.findViewById(R.id.handleResultEditText);
		subjectDataEditText = (EditText) bodyView.findViewById(R.id.subjectDataEditText);
		objectiveDataEditText = (EditText) bodyView.findViewById(R.id.objectiveDataEditText);
		otherDataEditText = (EditText) bodyView.findViewById(R.id.otherDataEditText);
		managementPlanEditText = (EditText) bodyView.findViewById(R.id.managementPlanEditText);
		recordDoctorEditText = (EditText) bodyView.findViewById(R.id.recordDoctorEditText);
		recordDateEditText = (EditText) bodyView.findViewById(R.id.recordDateEditText);
		remarkEditText = (EditText) bodyView.findViewById(R.id.remarkEditText);
		if(fsr != null){
			setSpinner(problemPhaseSpinner,jd);
			happenNameEditText.setText(fsr);
			happenDateEditText.setText(fsrq); 
			problemEvaluateEditText.setText(wtpg);
			mainProblemEditText.setText(zywt);
			handleResultEditText.setText(cljjg);
			subjectDataEditText.setText(zgzl);
			objectiveDataEditText.setText(kgzl);
			otherDataEditText.setText(qt);
			managementPlanEditText.setText(gljh);
			recordDoctorEditText.setText(jlys);
			recordDateEditText.setText(jlrq);
			remarkEditText.setText(bz);
		}

		if(!editEnable){
			problemPhaseSpinner.setEnabled(false);
			happenNameEditText.setEnabled(false);
			happenDateEditText.setEnabled(false);
			problemEvaluateEditText.setEnabled(false);
			mainProblemEditText.setEnabled(false);
			handleResultEditText.setEnabled(false);
			subjectDataEditText.setEnabled(false);
			objectiveDataEditText.setEnabled(false);
			otherDataEditText.setEnabled(false);
			managementPlanEditText.setEnabled(false);
			recordDoctorEditText.setEnabled(false);
			recordDateEditText.setEnabled(false);
			remarkEditText.setEnabled(false);
		}
		SystemUtils.showIME(happenNameEditText, getActivity());

		this.setOnConfirmClickListener(new OnConfirmClickListener() { 
			@Override
			public void onConfirmClick() {
				saveFamilyProblem();
				if (onFamilyProblemDialog != null) {					
					onFamilyProblemDialog.onConfirmClick(familyProblem);
				}
			}
		});
		return bodyView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		happenDateEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						happenDateEditText.setText(formatDate);
						datePickerDialog.hide();
					}
				});			
			}
		});
		recordDateEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						recordDateEditText.setText(formatDate);
						datePickerDialog.hide();
					}
				});			
			}
		});
	}

	private boolean validate() {
		if(TextUtils.isEmpty(happenNameEditText.getText())){
			ToastUtils.showToast(getActivity(), "发生人不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(happenDateEditText.getText())){
			ToastUtils.showToast(getActivity(), "发生日期不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(recordDateEditText.getText())){
			ToastUtils.showToast(getActivity(), "记录日期不能为空!");
			return false;
		}
		return true;
	}

	private void saveFamilyProblem() {
		if (!validate()) {
			return;
		}
		if(familyProblem == null){
			familyProblem = new FamilyProblem();
		}
			familyProblem.setJd(problemPhaseSpinner.getSelectedItem().toString());
			familyProblem.setFsr(happenNameEditText.getText().toString());
			familyProblem.setFsrq(happenDateEditText.getText().toString());
			familyProblem.setZywt(mainProblemEditText.getText().toString());
			familyProblem.setWtpg(problemEvaluateEditText.getText().toString());
			familyProblem.setCljjg(handleResultEditText.getText().toString());
			familyProblem.setZgzl(subjectDataEditText.getText().toString());
			familyProblem.setKgzl(objectiveDataEditText.getText().toString());
			familyProblem.setQt(otherDataEditText.getText().toString());
			familyProblem.setGljh(managementPlanEditText.getText().toString());
			familyProblem.setJlys(recordDoctorEditText.getText().toString());
			familyProblem.setJlrq(recordDateEditText.getText().toString());
			familyProblem.setBz(remarkEditText.getText().toString());

	}
	
	private void setSpinner(Spinner spinner, String text) {
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			String item = spinner.getAdapter().getItem(i).toString();
			if (item.equals(text)) {
				spinner.setSelection(i);
				break;
			}
		}
	}
	private OnFamilyProblemDialog onFamilyProblemDialog;

	public void setOnFamilyProblemDialog(OnFamilyProblemDialog onFamilyProblemDialog) {
		this.onFamilyProblemDialog = onFamilyProblemDialog;
	}

	public interface OnFamilyProblemDialog {
		public void onConfirmClick(FamilyProblem familyProblem);
	}

}
