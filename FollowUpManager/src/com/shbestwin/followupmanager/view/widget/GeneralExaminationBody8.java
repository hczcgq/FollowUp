package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.model.followup.FollowUpAged;

public class GeneralExaminationBody8 extends LinearLayout implements
		IBaseGeneralExaminationBody {
	private RelativeLayout cerebrokidneyLayout, kidneyLayout, heartLayout,
			vascularLayout, eyeLayout;
	private CheckBox cerebrokidney0, kidney0, heart0, vascular0, eye0;
	private CheckBox cerebrokidney5, kidney5, heart6, vascular3, eye4;
	private EditText cerebrokidneyEidtText, kidneyeditText, hearteditText,
			vasculareditText, eyeeditText;
	private RadioGroup rg_sjxtjb, rg_qtxijb;
	private RadioButton rb_sjxtjb_wfx,rb_sjxtjb_y,rb_qtxijb_wfx,rb_qtxijb_y;
	private EditText et_sjxtjb, et_qtxijb;
	private boolean isCerebrokidney = false, isKidney = false, isHeart = false,
			isVascular = false, isEye = false;
	private boolean isSjxt=false,isQt=false;

	public GeneralExaminationBody8(Context context) {
		this(context, null);
	}

	public GeneralExaminationBody8(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GeneralExaminationBody8(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_general_examination_body8, this, true);

		rb_sjxtjb_wfx=(RadioButton) rootView.findViewById(R.id.rb_sjxtjb_wfx);
		rb_sjxtjb_y=(RadioButton) rootView.findViewById(R.id.rb_sjxtjb_y);
		rb_qtxijb_wfx=(RadioButton) rootView.findViewById(R.id.rb_qtxijb_wfx);
		rb_qtxijb_y=(RadioButton) rootView.findViewById(R.id.rb_qtxijb_y);
		
		cerebrokidneyLayout = (RelativeLayout) rootView
				.findViewById(R.id.cerebrokidneyLayout);
		kidneyLayout = (RelativeLayout) rootView
				.findViewById(R.id.kidneyLayout);
		heartLayout = (RelativeLayout) rootView.findViewById(R.id.heartLayout);
		vascularLayout = (RelativeLayout) rootView
				.findViewById(R.id.vascularLayout);
		eyeLayout = (RelativeLayout) rootView.findViewById(R.id.eyeLayout);

		cerebrokidney0 = (CheckBox) rootView.findViewById(R.id.cerebrokidney0); // 无
		kidney0 = (CheckBox) rootView.findViewById(R.id.kidney0);
		heart0 = (CheckBox) rootView.findViewById(R.id.heart0);
		vascular0 = (CheckBox) rootView.findViewById(R.id.vascular0);
		eye0 = (CheckBox) rootView.findViewById(R.id.eye0);

		cerebrokidney5 = (CheckBox) rootView.findViewById(R.id.cerebrokidney5);// 其他
		kidney5 = (CheckBox) rootView.findViewById(R.id.kidney5);
		heart6 = (CheckBox) rootView.findViewById(R.id.heart6);
		vascular3 = (CheckBox) rootView.findViewById(R.id.vascular3);
		eye4 = (CheckBox) rootView.findViewById(R.id.eye4);

		cerebrokidneyEidtText = (EditText) rootView
				.findViewById(R.id.cerebrokidneyEidtText); // 其他editText
		kidneyeditText = (EditText) rootView.findViewById(R.id.kidneyeditText);
		hearteditText = (EditText) rootView.findViewById(R.id.hearteditText);
		vasculareditText = (EditText) rootView
				.findViewById(R.id.vasculareditText);
		eyeeditText = (EditText) rootView.findViewById(R.id.eyeeditText);

		rg_sjxtjb = (RadioGroup) rootView.findViewById(R.id.rg_sjxtjb);
		rg_qtxijb = (RadioGroup) rootView.findViewById(R.id.rg_qtxijb);

		et_sjxtjb = (EditText) rootView.findViewById(R.id.et_sjxtjb);
		et_qtxijb = (EditText) rootView.findViewById(R.id.et_qtxijb);

		cerebrokidney0.setOnCheckedChangeListener(new MyCheckedChangeListener());
		kidney0.setOnCheckedChangeListener(new MyCheckedChangeListener());
		heart0.setOnCheckedChangeListener(new MyCheckedChangeListener());
		vascular0.setOnCheckedChangeListener(new MyCheckedChangeListener());
		eye0.setOnCheckedChangeListener(new MyCheckedChangeListener());

		cerebrokidney5.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		kidney5.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		heart6.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		vascular3.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		eye4.setOnCheckedChangeListener(new OtherCheckedChangeListenerEditText());
		
		
		isCerebrokidney = false;
		setCheckBoxStatus(cerebrokidneyLayout, true);
		cerebrokidneyEidtText.setEnabled(false);
		isKidney = false;
		setCheckBoxStatus(kidneyLayout, true);
		kidneyeditText.setEnabled(false);
		isHeart = false;
		setCheckBoxStatus(heartLayout, true);
		hearteditText.setEnabled(false);
		isVascular = false;
		setCheckBoxStatus(vascularLayout, true);
		vasculareditText.setEnabled(false);
		isEye = false;
		setCheckBoxStatus(eyeLayout, true);
		eyeeditText.setEnabled(false);
		
		
		et_sjxtjb.setEnabled(false);
		et_qtxijb.setEnabled(false);
		
		rg_sjxtjb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_sjxtjb_wfx){
					isSjxt=false;
					et_sjxtjb.setEnabled(false);
				}else {
					isSjxt=true;
					et_sjxtjb.setEnabled(true);
				}
			}
		});
		
		rg_qtxijb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_qtxijb_wfx){
					isQt=false;
					et_qtxijb.setEnabled(false);
				}else {
					isQt=true;
					et_qtxijb.setEnabled(true);
				}
			}
		});

	}

	@Override
	public void getData(GeneralExamination generalExamination) {
		generalExamination.setJkwt_nxgjb(ViewDataUtil.getCheckboxData(cerebrokidneyLayout,cerebrokidneyEidtText));
		generalExamination.setJkwt_szjb(ViewDataUtil.getCheckboxData(kidneyLayout,kidneyeditText));
		generalExamination.setJkwt_xzjb(ViewDataUtil.getCheckboxData(heartLayout,hearteditText));
		generalExamination.setJkwt_xgjb(ViewDataUtil.getCheckboxData(vascularLayout,vasculareditText));
		generalExamination.setJkwt_ybjb(ViewDataUtil.getCheckboxData(eyeLayout,eyeeditText));
		generalExamination.setJkwt_sfsjxtjb(isSjxt);
		generalExamination.setJkwt_sjxtjbms(et_sjxtjb.getText().toString());
		generalExamination.setJkwt_sfqtxtjb(isQt);
		generalExamination.setJkwt_qtxtjbms(et_qtxijb.getText().toString());
		
	}

	@Override
	public void setData(GeneralExamination generalExamination) {
		if(generalExamination!=null) {
		    ViewDataUtil.setCheckboxData(cerebrokidneyLayout, cerebrokidneyEidtText, generalExamination.getJkwt_nxgjb());
		    ViewDataUtil.setCheckboxData(kidneyLayout, kidneyeditText, generalExamination.getJkwt_szjb());
		    ViewDataUtil.setCheckboxData(heartLayout, hearteditText, generalExamination.getJkwt_xzjb());
		    ViewDataUtil.setCheckboxData(vascularLayout, vasculareditText, generalExamination.getJkwt_xgjb());
		    ViewDataUtil.setCheckboxData(eyeLayout, eyeeditText, generalExamination.getJkwt_ybjb());
		    et_qtxijb.setText(generalExamination.getJkwt_qtxtjbms());
		    et_sjxtjb.setText(generalExamination.getJkwt_sjxtjbms());
		    
		    if(generalExamination.getJkwt_sfsjxtjb()) {
		        rb_sjxtjb_y.setChecked(true);
		        rb_sjxtjb_wfx.setChecked(false);
		    }else {
		        rb_sjxtjb_y.setChecked(false);
                rb_sjxtjb_wfx.setChecked(true);
            }
		    
		    if(generalExamination.getJkwt_sfqtxtjb()) {
                rb_qtxijb_y.setChecked(true);
                rb_qtxijb_wfx.setChecked(false);
            }else {
                rb_qtxijb_y.setChecked(false);
                rb_qtxijb_wfx.setChecked(true);
            }
		}
	}

	@Override
	public boolean validate() {
		return true;
	}

	/**
	 * Description 监听CheckBox无
	 * 
	 */
	private class MyCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			switch (buttonView.getId()) {
			case R.id.cerebrokidney0:
				isCerebrokidney = !isChecked;
				setCheckBoxStatus(cerebrokidneyLayout, isChecked);
				break;
			case R.id.kidney0:
				isKidney = !isChecked;
				setCheckBoxStatus(kidneyLayout, isChecked);
				break;
			case R.id.heart0:
				isHeart = !isChecked;
				setCheckBoxStatus(heartLayout, isChecked);
				break;
			case R.id.vascular0:
				isVascular = !isChecked;
				setCheckBoxStatus(vascularLayout, isChecked);
				break;
			case R.id.eye0:
				isEye = !isChecked;
				setCheckBoxStatus(eyeLayout, isChecked);
				break;
			default:
				break;
			}

		}
	}

	/**
	 * Description 监听CheckBox其他
	 * 
	 */
	private class OtherCheckedChangeListenerEditText implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.cerebrokidney5:
				cerebrokidneyEidtText.setEnabled(isChecked);
				if (!isChecked) {
					cerebrokidneyEidtText.setText("");
				}
				break;
			case R.id.kidney5:
				kidneyeditText.setEnabled(isChecked);
				if (!isChecked) {
					kidneyeditText.setText("");
				}
				break;
			case R.id.heart6:
				hearteditText.setEnabled(isChecked);
				if (!isChecked) {
					hearteditText.setText("");
				}
				break;
			case R.id.vascular3:
				vasculareditText.setEnabled(isChecked);
				if (!isChecked) {
					vasculareditText.setText("");
				}
				break;
			case R.id.eye4:
				eyeeditText.setEnabled(isChecked);
				if (!isChecked) {
					eyeeditText.setText("");
				}
				break;
			default:
				break;
			}
		}
	}

	private void setCheckBoxStatus(RelativeLayout familyHistory,
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
}
