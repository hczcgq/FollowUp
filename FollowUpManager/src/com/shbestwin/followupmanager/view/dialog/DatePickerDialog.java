package com.shbestwin.followupmanager.view.dialog;

import java.util.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.shbestwin.followupmanager.R;

public class DatePickerDialog extends BaseDialogFragment {
	private DatePicker datePicker;

	private int[] days = new int[3];

	public static DatePickerDialog newInstance() {
		DatePickerDialog dialog = new DatePickerDialog();
		return dialog;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_common_date_picker, null);
		datePicker = (DatePicker) bodyView.findViewById(R.id.datePicker);
		setOnConfirmClickListener(new OnConfirmClickListener() {
			@Override
			public void onConfirmClick() {
				if (onDatePickerListener != null) {
					days[0] = datePicker.getYear();
					days[1] = datePicker.getMonth() + 1;
					days[2] = datePicker.getDayOfMonth();
					onDatePickerListener.onConfirmClick(getTimeInMillis(), getFormatDate());
				}
			}
		});
		return bodyView;
	}

	private String getFormatDate() {
		StringBuffer result = new StringBuffer();
		result.append(days[0]).append("-");
		if (days[1] < 10) {
			result.append("0");
		}
		result.append(days[1]).append("-");

		if (days[2] < 10) {
			result.append("0");
		}
		result.append(days[2]);

		return result.toString();
	}

	private long getTimeInMillis() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, days[0]); // 设置年份
		c.set(Calendar.MONTH, days[1] - 1);// 设置月份
		c.set(Calendar.DAY_OF_MONTH, days[2]);// 设置月份的日期号码
		return c.getTimeInMillis();
	}

	@Override
	protected int getWidth() {
		return (int) getResources().getDimension(R.dimen.dialog_date_picker_width);
	}

	private OnDatePickerListener onDatePickerListener;

	public void setOnDatePickerListener(OnDatePickerListener onDatePickerListener) {
		this.onDatePickerListener = onDatePickerListener;
	}

	public interface OnDatePickerListener {
		public void onConfirmClick(long timeInMillis, String formatDate);
	}
}
