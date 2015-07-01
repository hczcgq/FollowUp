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
import com.shbestwin.followupmanager.model.archive.PastHistoryInjury;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: PastHistoryInjuryDialog
 * @Description: 既往史-外伤 增加和修改对话框
 * @author junbin.he
 * @date 2015年1月22日 下午12:58:14
 *
 */
public class PastHistoryInjuryDialog extends BaseDialogFragment {
	private Spinner type,treatResult;
	private EditText nameEditText,date,onsetDate;
	private PastHistoryInjury pastHistoryInjury;
	private String string = null,string2 = null,string3 = null,string4 = null,string5 = null;

	public static PastHistoryInjuryDialog newInstance() {
		PastHistoryInjuryDialog dialog = new PastHistoryInjuryDialog();
		return dialog;
	}

	public PastHistoryInjuryDialog(String string,String string2,String string3,String string4,String string5){
		this.string = string;
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
		this.string5 = string5;
	}
	
	public PastHistoryInjuryDialog(){
		
	}
	
	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_archive_past_history_injury, null);
		nameEditText = (EditText) bodyView.findViewById(R.id.nameEditText);
		date = (EditText) bodyView.findViewById(R.id.date);
		onsetDate = (EditText) bodyView.findViewById(R.id.onsetDate);
		type = (Spinner) bodyView.findViewById(R.id.type);
		treatResult = (Spinner) bodyView.findViewById(R.id.treatResult);
		if(string != null){
			setSpinner(type,string);
			setSpinner(treatResult,string2);
			nameEditText.setText(string3);
			date.setText(string4);
			onsetDate.setText(string5);
		}
		SystemUtils.showIME(nameEditText, getActivity());
		this.setOnConfirmClickListener(new OnConfirmClickListener() {
			@Override
			public void onConfirmClick() {
				savePastHistoryInjury();
				if (onPastHistoryInjuryDialog != null) {				
					onPastHistoryInjuryDialog.onConfirmClick(pastHistoryInjury);
				}
			}
		});
		return bodyView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		
		date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						date.setText(formatDate);
						datePickerDialog.hide();
					}
				});			
			}
		});
		
		onsetDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance();
				datePickerDialog.show(getChildFragmentManager(), "datePickerDialog");
				datePickerDialog.setOnDatePickerListener(new OnDatePickerListener() {
					@Override
					public void onConfirmClick(long timeInMillis, String formatDate) {
						onsetDate.setText(formatDate);
						datePickerDialog.hide();
					}
				});			
			}
		});
	}

	private boolean validate() {
		if(TextUtils.isEmpty(nameEditText.getText())){
			ToastUtils.showToast(getActivity(), "外伤名称不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(date.getText())){
			ToastUtils.showToast(getActivity(), "外伤日期不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(onsetDate.getText())){
			ToastUtils.showToast(getActivity(), "发病日期不能为空!");
			return false;
		}
		return true;
	}
	private void savePastHistoryInjury() {
		if (!validate()) {
			return;
		}
		if(pastHistoryInjury == null){
			pastHistoryInjury = new PastHistoryInjury();
		}
		
		pastHistoryInjury.setName(nameEditText.getText().toString());
		pastHistoryInjury.setType(type.getSelectedItem().toString());
		pastHistoryInjury.setDate(date.getText().toString());
		pastHistoryInjury.setOnsetDate(onsetDate.getText().toString());
		pastHistoryInjury.setTreatResult(treatResult.getSelectedItem().toString());
		
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
	
	private OnPastHistoryInjuryDialog onPastHistoryInjuryDialog;

	public void setOnPastHistoryInjuryDialog(OnPastHistoryInjuryDialog onPastHistoryInjuryDialog) {
		this.onPastHistoryInjuryDialog = onPastHistoryInjuryDialog;
	}
	public interface OnPastHistoryInjuryDialog {
		public void onConfirmClick(PastHistoryInjury pastHistoryInjury);
	}
}
