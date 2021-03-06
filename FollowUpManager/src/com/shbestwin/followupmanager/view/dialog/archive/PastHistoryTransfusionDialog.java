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
import com.shbestwin.followupmanager.model.archive.PastHistoryTransfusion;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: PastHistorytransfusionDialog
 * @Description: 既往史-输血 增加和修改对话框
 * @author junbin.he
 * @date 2015年1月22日 下午12:58:14
 *
 */
public class PastHistoryTransfusionDialog extends BaseDialogFragment {
	private Spinner type,treatResult;
	private EditText nameEditText,date,onsetDate,reason;
	private PastHistoryTransfusion pastHistoryTransfusion;
	private String string = null,string2 = null,string3 = null,string4 = null,string5 = null,string6 = null;

	public static PastHistoryTransfusionDialog newInstance() {
		PastHistoryTransfusionDialog dialog = new PastHistoryTransfusionDialog();
		return dialog;
	}

	public PastHistoryTransfusionDialog(String string,String string2,String string3,String string4,String string5,String string6){
		this.string = string;
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
		this.string5 = string5;
		this.string6 = string6;
	}
	
	public PastHistoryTransfusionDialog(){
		
	}
	
	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_archive_past_history_transfusion, null);
		nameEditText = (EditText) bodyView.findViewById(R.id.nameEditText);
		date = (EditText) bodyView.findViewById(R.id.date);
		onsetDate = (EditText) bodyView.findViewById(R.id.onsetDate);
		reason = (EditText) bodyView.findViewById(R.id.reason);
		type = (Spinner) bodyView.findViewById(R.id.type);
		treatResult = (Spinner) bodyView.findViewById(R.id.treatResult);
		if(string != null){
			setSpinner(type,string);
			setSpinner(treatResult,string2);
			nameEditText.setText(string3);
			date.setText(string4);
			onsetDate.setText(string5);
			reason.setText(string6);
		}
		SystemUtils.showIME(nameEditText, getActivity());
		this.setOnConfirmClickListener(new OnConfirmClickListener() {
			@Override
			public void onConfirmClick() {
				savePastHistoryTransfusion();
				if (onPastHistoryTransfusionDialog != null) {				
					onPastHistoryTransfusionDialog.onConfirmClick(pastHistoryTransfusion);
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
			ToastUtils.showToast(getActivity(), "输血名称不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(date.getText())){
			ToastUtils.showToast(getActivity(), "输血日期不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(onsetDate.getText())){
			ToastUtils.showToast(getActivity(), "发病日期不能为空!");
			return false;
		}
		if(TextUtils.isEmpty(reason.getText())){
			ToastUtils.showToast(getActivity(), "输血原因不能为空!");
			return false;
		}
		return true;
	}
	private void savePastHistoryTransfusion() {
		if (!validate()) {
			return;
		}
		if(pastHistoryTransfusion == null){
			pastHistoryTransfusion = new PastHistoryTransfusion();
		}
		
		pastHistoryTransfusion.setName(nameEditText.getText().toString());
		pastHistoryTransfusion.setType(type.getSelectedItem().toString());
		pastHistoryTransfusion.setDate(date.getText().toString());
		pastHistoryTransfusion.setOnsetDate(onsetDate.getText().toString());
		pastHistoryTransfusion.setReason(reason.getText().toString());
		pastHistoryTransfusion.setTreatResult(treatResult.getSelectedItem().toString());
		
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
	
	private OnPastHistoryTransfusionDialog onPastHistoryTransfusionDialog;

	public void setOnPastHistoryTransfusionDialog(OnPastHistoryTransfusionDialog onPastHistoryTransfusionDialog) {
		this.onPastHistoryTransfusionDialog = onPastHistoryTransfusionDialog;
	}
	public interface OnPastHistoryTransfusionDialog {
		public void onConfirmClick(PastHistoryTransfusion pastHistoryTransfusion);
	}
}
