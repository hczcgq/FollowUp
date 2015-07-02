package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.Insulin;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;

/**
 * 
 * @ClassName: InsulinDialog
 * @Description: 糖尿病-胰岛素
 * @author junbin.he
 * @date 2015年2月26日 上午11:18:25
 *
 */
public class InsulinDialog extends BaseDialogFragment {
	private EditText et_ywlx,et_sypl,et_yyjl;
	private Insulin mData;

	public static InsulinDialog newInstance() {
		InsulinDialog dialog = new InsulinDialog();
		return dialog;
	}
	
	public InsulinDialog(){
        
    }
    
    public InsulinDialog(Insulin mData){
        this.mData=mData;
    }   

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_diabetes_mellitus_insulin, null);
		et_ywlx = (EditText) bodyView.findViewById(R.id.et_ywlx);
		et_sypl = (EditText) bodyView.findViewById(R.id.et_sypl);
		et_yyjl = (EditText) bodyView.findViewById(R.id.et_yyjl);
		
		if(mData!=null) {
		    et_ywlx.setText(mData.getYds_ywzl());
		    et_sypl.setText(mData.getYds_sypl());
		    et_yyjl.setText(mData.getYds_syjl());
		}
		
		return bodyView;
	}
	
	public Insulin getInsulin(){
	    Insulin insulin=new Insulin();
	    insulin.setYds_ywzl(et_ywlx.getText().toString());
	    insulin.setYds_sypl(et_sypl.getText().toString());
	    insulin.setYds_syjl(et_yyjl.getText().toString());
        return insulin;
    }

}
