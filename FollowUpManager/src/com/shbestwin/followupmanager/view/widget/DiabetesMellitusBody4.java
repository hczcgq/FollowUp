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
import com.shbestwin.followupmanager.model.followup.FollowUpDiabetesMellitus;

public class DiabetesMellitusBody4 extends LinearLayout  implements IBaseDiabetesMellitusBody{
	private EditText et_rxyl_dqz,et_rxyl_mbz,et_ryjl_dqz,et_ryjl_mbz,et_ydl_cz,et_ydl_fc,et_ydl_cz_s,et_ydl_fc_s,et_zs_dqz,et_zs_mbz;
	private Spinner sn_xltz,sn_zyxw;
	public DiabetesMellitusBody4(Context context) {
		this(context, null);
	}

	public DiabetesMellitusBody4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DiabetesMellitusBody4(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_diabetes_mellitus_body4, this, true);
		et_rxyl_dqz=(EditText) rootView.findViewById(R.id.et_rxyl_dqz);
		et_rxyl_mbz=(EditText) rootView.findViewById(R.id.et_rxyl_mbz);
		et_ryjl_dqz=(EditText) rootView.findViewById(R.id.et_ryjl_dqz);
		et_ryjl_mbz=(EditText) rootView.findViewById(R.id.et_ryjl_mbz);
		et_ydl_cz=(EditText) rootView.findViewById(R.id.et_ydl_cz);
		et_ydl_fc=(EditText) rootView.findViewById(R.id.et_ydl_fc);
		et_ydl_cz_s=(EditText) rootView.findViewById(R.id.et_ydl_cz_s);
		et_ydl_fc_s=(EditText) rootView.findViewById(R.id.et_ydl_fc_s);
		et_zs_dqz=(EditText) rootView.findViewById(R.id.et_zs_dqz);
		et_zs_mbz=(EditText) rootView.findViewById(R.id.et_zs_mbz);
		sn_xltz=(Spinner) rootView.findViewById(R.id.sn_xltz);
		sn_zyxw=(Spinner) rootView.findViewById(R.id.sn_zyxw);
	}
	@Override
	public void getData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {

		followUpDiabetesMellitus.setShzdfs_rxyl(et_rxyl_dqz.getText().toString()+"/"+et_rxyl_mbz.getText().toString());
		followUpDiabetesMellitus.setShzdfs_ryjl(et_ryjl_dqz.getText().toString()+"/"+et_ryjl_mbz.getText().toString());
		followUpDiabetesMellitus.setShzdfs_ydl(et_ydl_cz.getText().toString()+"/"+et_ydl_fc.getText().toString()+"/"+et_ydl_cz_s.getText().toString()+"/"+et_ydl_fc_s.getText().toString());
		followUpDiabetesMellitus.setShzdfs_zs(et_zs_dqz.getText().toString()+"/"+et_zs_mbz.getText().toString());
		followUpDiabetesMellitus.setShzdfs_xltz(ViewDataUtil.getSpinnerData(sn_xltz));
		followUpDiabetesMellitus.setShzdfs_zyxw(ViewDataUtil.getSpinnerData(sn_zyxw));
	}

	@Override
	public void setData(FollowUpDiabetesMellitus followUpDiabetesMellitus) {
		if(followUpDiabetesMellitus!=null) {
		    ViewDataUtil.setSpinnerData(sn_xltz, followUpDiabetesMellitus.getShzdfs_xltz());
		    ViewDataUtil.setSpinnerData(sn_zyxw, followUpDiabetesMellitus.getShzdfs_zs());
		    String smoking=followUpDiabetesMellitus.getShzdfs_rxyl();
		    if(smoking.split("/").length==1) {
		        et_rxyl_dqz.setText(smoking.split("/")[0]);
		    }else if(smoking.split("/").length==2) {
		        et_rxyl_dqz.setText(smoking.split("/")[0]);
                et_rxyl_mbz.setText(smoking.split("/")[1]);
            }
		    String drinking=followUpDiabetesMellitus.getShzdfs_ryjl();
            if(drinking.split("/").length==1) {
                et_ryjl_dqz.setText(drinking.split("/")[0]);
            }else if(drinking.split("/").length==2) {
                et_ryjl_dqz.setText(drinking.split("/")[0]);
                et_ryjl_mbz.setText(drinking.split("/")[1]);
            } 
            
            String food=followUpDiabetesMellitus.getShzdfs_zs();
            if(food.split("/").length==1) {
                et_zs_dqz.setText(food.split("/")[0]);
            }else if(food.split("/").length==2) {
                et_zs_dqz.setText(food.split("/")[0]);
                et_zs_mbz.setText(food.split("/")[1]);
            }
            
            String sporting=followUpDiabetesMellitus.getShzdfs_ydl();
            if(sporting.split("/").length==1) {
                et_ydl_cz.setText(sporting.split("/")[0]);
            }else  if(sporting.split("/").length==2) {
                et_ydl_cz.setText(sporting.split("/")[0]);
                et_ydl_fc.setText(sporting.split("/")[1]);
            }else  if(sporting.split("/").length==3) {
                et_ydl_cz.setText(sporting.split("/")[0]);
                et_ydl_fc.setText(sporting.split("/")[1]);
                et_ydl_cz_s.setText(sporting.split("/")[2]);
            }else  if(sporting.split("/").length==4) {
                et_ydl_cz.setText(sporting.split("/")[0]);
                et_ydl_fc.setText(sporting.split("/")[1]);
                et_ydl_cz_s.setText(sporting.split("/")[2]);
                et_ydl_fc_s.setText(sporting.split("/")[3]);
            }
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setFragment(FragmentManager fragmentManager) {
		// TODO Auto-generated method stub
		
	}

}
