package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody7 extends LinearLayout  implements IBaseHypertensionBody{
	
	private RadioGroup rg_check;
	private RadioButton rb_w,rb_y;
	private EditText et_other;
	private boolean isHas=false;
	
	public HypertensionBody7(Context context) {
		this(context, null);
	}

	public HypertensionBody7(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody7(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body7, this, true);
		rg_check=(RadioGroup) rootView.findViewById(R.id.rg_check);
		et_other=(EditText) rootView.findViewById(R.id.et_other);
		rb_w=(RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y=(RadioButton) rootView.findViewById(R.id.rb_y);
		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_w){
					isHas=false;
				}else if(checkedId==R.id.rb_y){
					isHas=true;
				}
			}
		});
	}
	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setYwblfy_ywblfy(isHas);
		followUpHypertension.setJkwt_ywblfyms(et_other.getText().toString());
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null) {
	        et_other.setText(followUpHypertension.getJkwt_ywblfyms());
	        if(followUpHypertension.getYwblfy_ywblfy()) {
	            rb_w.setChecked(false);
	            rb_y.setChecked(true);
	        }else {
	            rb_w.setChecked(true);
                rb_y.setChecked(false);
            }
	    }
	}

	@Override
	public boolean validate() {
		return true;
	}
	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub
		
	}
}
