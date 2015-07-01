package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.Medication;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;

/**
 * 
 * @ClassName: MedicationDialog
 * @Description: 用药情况
 * @author junbin.he
 * @date 2015年2月26日 上午11:39:46
 *
 */
public class MedicationDialog extends BaseDialogFragment {
	private Medication mData;
	
	private TextView et_yymc,et_yl,et_syzjl;
	private Spinner sn_ywlx,sn_ywdw,sn_yf,sn_jyfs;

	public static MedicationDialog newInstance() {
		MedicationDialog dialog = new MedicationDialog();
		return dialog;
	}
	
	public MedicationDialog(){
        
    }
    
    public MedicationDialog(Medication mData){
        this.mData=mData;
    }
	

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_followup_medicine, null);
		et_yymc = (EditText) bodyView.findViewById(R.id.et_yymc);
		et_yl = (EditText) bodyView.findViewById(R.id.et_yl);
		et_syzjl = (EditText) bodyView.findViewById(R.id.et_syzjl);
		sn_ywlx = (Spinner) bodyView.findViewById(R.id.sn_ywlx);
		sn_ywdw = (Spinner) bodyView.findViewById(R.id.sn_ywdw);
		sn_yf = (Spinner) bodyView.findViewById(R.id.sn_yf);
		sn_jyfs = (Spinner) bodyView.findViewById(R.id.sn_jyfs);
		
		
		if(mData!=null){
		    et_yymc.setText(mData.getYyqk_ywmc());
		    et_yl.setText(mData.getYyqk_yl());
		    et_syzjl.setText(mData.getYyqk_syzjl());
		    ViewDataUtil.setSpinnerData(sn_ywlx, mData.getYyqk_ywlx());
		    ViewDataUtil.setSpinnerData(sn_ywdw, mData.getYyqk_dw());
		    ViewDataUtil.setSpinnerData(sn_yf, mData.getYyqk_yf());
		    ViewDataUtil.setSpinnerData(sn_jyfs, mData.getYyqk_gyfs());
        }
		
		return bodyView;
	}
	
	public Medication getMedication(){
        Medication medication=new Medication();
        medication.setYyqk_ywmc(et_yymc.getText().toString());
        medication.setYyqk_yl(et_yl.getText().toString());
        medication.setYyqk_syzjl(et_syzjl.getText().toString());
        medication.setYyqk_ywlx(ViewDataUtil.getSpinnerData(sn_ywlx));
        medication.setYyqk_yf(ViewDataUtil.getSpinnerData(sn_yf));
        medication.setYyqk_dw(ViewDataUtil.getSpinnerData(sn_ywdw));
        medication.setYyqk_gyfs(ViewDataUtil.getSpinnerData(sn_jyfs));
        return medication;
    }
}
