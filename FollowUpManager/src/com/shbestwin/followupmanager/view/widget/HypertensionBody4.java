package com.shbestwin.followupmanager.view.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpHypertension;

public class HypertensionBody4 extends LinearLayout  implements IBaseHypertensionBody{
	
	
	private EditText et_rxyl_dqz,et_rxyl_mbz,et_ryjl_dqz,et_ryjl_mbz,et_ydl_cz,et_ydl_fc,et_syqk_dqz,et_syqk_mbz;
	private Spinner sn_syqk_l,sn_xltz,sn_zyxw;
	
	public HypertensionBody4(Context context) {
		this(context, null);
	}

	public HypertensionBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HypertensionBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_hypertension_body4, this, true);
		et_rxyl_dqz=(EditText) rootView.findViewById(R.id.et_rxyl_dqz);
		et_rxyl_mbz=(EditText) rootView.findViewById(R.id.et_rxyl_mbz);
		et_ryjl_dqz=(EditText) rootView.findViewById(R.id.et_ryjl_dqz);
		et_ryjl_mbz=(EditText) rootView.findViewById(R.id.et_ryjl_mbz);
		et_ydl_cz=(EditText) rootView.findViewById(R.id.et_ydl_cz);
		et_ydl_fc=(EditText) rootView.findViewById(R.id.et_ydl_fc);
		et_syqk_dqz=(EditText) rootView.findViewById(R.id.et_syqk_dqz);
		et_syqk_mbz=(EditText) rootView.findViewById(R.id.et_syqk_mbz);
		sn_syqk_l=(Spinner) rootView.findViewById(R.id.sn_syqk_l);
		sn_xltz=(Spinner) rootView.findViewById(R.id.sn_xltz);
		sn_zyxw=(Spinner) rootView.findViewById(R.id.sn_zyxw);
		
	}
	@Override
	public void getData(FollowUpHypertension followUpHypertension) {
		followUpHypertension.setShzdfs_rxyl(et_rxyl_dqz.getText().toString()+"/"+et_rxyl_mbz.getText().toString());
		followUpHypertension.setShzdfs_ryjl(et_ryjl_dqz.getText().toString()+"/"+et_ryjl_mbz.getText().toString());
		followUpHypertension.setShzdfs_ydl(et_ydl_cz.getText().toString()+"/"+et_ydl_fc.getText().toString());
		followUpHypertension.setShzdfs_syqk(et_syqk_dqz.getText().toString()+"/"+et_syqk_mbz.getText().toString()+"/"+ViewDataUtil.getSpinnerData(sn_syqk_l));
		followUpHypertension.setShzdfs_xltz(ViewDataUtil.getSpinnerData(sn_xltz));
	}

	@Override
	public void setData(FollowUpHypertension followUpHypertension) {
	    if(followUpHypertension!=null){
	       String smoking=followUpHypertension.getShzdfs_rxyl();
	       if(smoking.split("/").length==1) {
	           et_rxyl_dqz.setText(smoking.split("/")[0]);
	       }else if(smoking.split("/").length==2){
	    	   et_rxyl_dqz.setText(smoking.split("/")[0]);
	           et_rxyl_mbz.setText(smoking.split("/")[1]);
	       }
	       
	       String drinking=followUpHypertension.getShzdfs_ryjl();
           if(drinking.split("/").length==1) {
               et_ryjl_dqz.setText(drinking.split("/")[0]);
           }else if(drinking.split("/").length==2) {
               et_ryjl_dqz.setText(drinking.split("/")[0]);
               et_ryjl_mbz.setText(drinking.split("/")[1]);
           }
           
           String sporting=followUpHypertension.getShzdfs_ydl();
           if(sporting.split("/").length==1) {
               et_ydl_cz.setText(sporting.split("/")[0]);
               
           }else if(sporting.split("/").length==2) {
               et_ydl_cz.setText(sporting.split("/")[0]);
               et_ydl_fc.setText(sporting.split("/")[1]);
           }
           
           
           String yan=followUpHypertension.getShzdfs_syqk();
           if(yan.split("/").length==1) {
               et_syqk_dqz.setText(yan.split("/")[0]);
           }else if(yan.split("/").length==2) {
               et_syqk_dqz.setText(yan.split("/")[0]);
               et_syqk_mbz.setText(yan.split("/")[1]);
           }else if(yan.split("/").length==3) {
               et_syqk_dqz.setText(yan.split("/")[0]);
               et_syqk_mbz.setText(yan.split("/")[1]);
               ViewDataUtil.setSpinnerData(sn_syqk_l,yan.split("/")[2] );
           }
           
           ViewDataUtil.setSpinnerData(sn_xltz, followUpHypertension.getShzdfs_xltz());
//           ViewDataUtil.setSpinnerData(sn_zyxw, followUpHypertension.getShzdfs_z);
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
