package com.shbestwin.followupmanager.view.widget;

import java.util.Date;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpPostpartum;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class PostpartumBody1 extends LinearLayout implements IBasePostpartumBody {
	private FragmentManager fragmentManager;
	private EditText et_fsrq;
	public PostpartumBody1(Context context) {
		this(context, null);
	}

	public PostpartumBody1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PostpartumBody1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_postpartum_body1, this, true);
		et_fsrq=(EditText) rootView.findViewById(R.id.et_fsrq);
		et_fsrq.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_fsrq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_fsrq.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
	}

	@Override
	public void getData(FollowUpPostpartum followUpPostpartum) {
		followUpPostpartum.setGrxx_fsrq(et_fsrq.getText().toString());

	}

	@Override
	public void setData(FollowUpPostpartum followUpPostpartum) {
		if(followUpPostpartum!=null){
			et_fsrq.setText(followUpPostpartum.getGrxx_fsrq());
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void setFragment(FragmentManager fragmentManager) {
		this.fragmentManager=fragmentManager;
		
	}

}
