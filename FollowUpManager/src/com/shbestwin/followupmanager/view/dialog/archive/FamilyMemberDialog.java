package com.shbestwin.followupmanager.view.dialog.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.archive.FamilyMember;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: FamilyMemberDialog
 * @Description: 家庭成员增加和修改对话框
 * @author junbin.he
 * @date 2015年1月22日 下午12:58:14
 *
 */
public class FamilyMemberDialog extends BaseDialogFragment {
	private EditText nameEditText,familyMemberBirthday,idCardNoEditText,bzEditText; 
	private FamilyMember familyMember;
	private boolean editEnable = true;
	private Spinner genderSpinner,relationship,education,job,marriage,archiveStatus;
	private String xm,xb,csrq,yhzgx,whcd,zy,sfzh,hyzk,grzk,bz;
	
	public FamilyMemberDialog(){
		
	}
	
	public FamilyMemberDialog(boolean editEnable,String xm,String xb,String csrq,
			String yhzgx,String whcd,String zy,String sfzh,String hyzk,String grzk,String bz) {
		// TODO Auto-generated constructor stub
		this.editEnable = editEnable;
		
		this.xm = xm;
		this.xb = xb;
		this.csrq = csrq;
		this.yhzgx = yhzgx;
		this.whcd = whcd;
		this.zy = zy;
		this.sfzh = sfzh;
		this.hyzk = hyzk;
		this.grzk = grzk;
		this.bz = bz;
	}

	public static FamilyMemberDialog newInstance() {
		FamilyMemberDialog dialog = new FamilyMemberDialog();
		return dialog;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_archive_family_member, null);
		nameEditText = (EditText) bodyView.findViewById(R.id.nameEditText);
		familyMemberBirthday = (EditText) bodyView.findViewById(R.id.familyMemberBirthday);
		idCardNoEditText = (EditText) bodyView.findViewById(R.id.idCardNoEditText);
		bzEditText = (EditText) bodyView.findViewById(R.id.bzEditText);
		genderSpinner = (Spinner) bodyView.findViewById(R.id.genderSpinner);
		relationship = (Spinner) bodyView.findViewById(R.id.relationship);
		education = (Spinner) bodyView.findViewById(R.id.education);
		job = (Spinner) bodyView.findViewById(R.id.job);
		marriage = (Spinner) bodyView.findViewById(R.id.marriage);
		archiveStatus = (Spinner) bodyView.findViewById(R.id.archiveStatus);
		if(xm != null){
			
			setSpinner(genderSpinner,xb);
			setSpinner(relationship,yhzgx);
			setSpinner(education,whcd);
			setSpinner(job,zy);
			setSpinner(marriage,hyzk);
			setSpinner(archiveStatus,grzk);
			nameEditText.setText(xm);
			familyMemberBirthday.setText(csrq); 
			idCardNoEditText.setText(sfzh);
			bzEditText.setText(bz);
	
		}

		if(!editEnable){
			genderSpinner.setEnabled(false);
			relationship.setEnabled(false);
			education.setEnabled(false);
			job.setEnabled(false);
			marriage.setEnabled(false);
			archiveStatus.setEnabled(false);
			nameEditText.setEnabled(false);
			familyMemberBirthday.setEnabled(false);
			idCardNoEditText.setEnabled(false);
			bzEditText.setEnabled(false);
		}
		SystemUtils.showIME(nameEditText, getActivity());
		this.setOnConfirmClickListener(new OnConfirmClickListener() { 
			@Override
			public void onConfirmClick() {
				saveFamilyMember();
				if (onFamilyMemberDialog != null) {					
					onFamilyMemberDialog.onConfirmClick(familyMember);
				}
			}
		});
		return bodyView;
	}


	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		familyMemberBirthday.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						familyMemberBirthday.setText(formatDate);
						datePickerDialog.hide();
					}
				});
			
				
			}
		});
	}
	private boolean validate() {
		if(TextUtils.isEmpty(nameEditText.getText())){
			ToastUtils.showToast(getActivity(), "姓名不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(familyMemberBirthday.getText())){
			ToastUtils.showToast(getActivity(), "出生日期不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(idCardNoEditText.getText())){
			ToastUtils.showToast(getActivity(), "身份证号码不能为空!");
			return false;
		}
		return true;
	}
	private void saveFamilyMember() {
		if (!validate()) {
			return;
		}
		if(familyMember == null){
			familyMember = new FamilyMember();
		}
		familyMember.setName(nameEditText.getText().toString());
		familyMember.setBirthday(familyMemberBirthday.getText().toString());
		familyMember.setIdcard(idCardNoEditText.getText().toString());
		familyMember.setRemark(bzEditText.getText().toString())	;
		familyMember.setGender(genderSpinner.getSelectedItem().toString());
		familyMember.setRelationship(relationship.getSelectedItem().toString());
		familyMember.setEducation(education.getSelectedItem().toString());
		familyMember.setJob(job.getSelectedItem().toString());
		familyMember.setMarriage(marriage.getSelectedItem().toString());
		familyMember.setPersonalStatus(archiveStatus.getSelectedItem().toString());
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
	private OnFamilyMemberDialog onFamilyMemberDialog;

	public void setOnFamilyMemberDialog(OnFamilyMemberDialog onFamilyMemberDialog) {
		this.onFamilyMemberDialog = onFamilyMemberDialog;
	}

	public interface OnFamilyMemberDialog {
		public void onConfirmClick(FamilyMember familyMember);
	}
}
