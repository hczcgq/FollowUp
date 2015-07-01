package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.Inspection;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;

/**
 * 
 * @ClassName: ChildrenInspectionDialog
 * @Description: 儿童访视-实验室检查、辅助检查
 * @author junbin.he
 * @date 2015年2月12日 下午2:29:51
 *
 */
public class InspectionDialog extends BaseDialogFragment {
	private EditText et_jcxm,et_jcr,et_jcsj,et_jcjg;
	private Inspection mData;
	
	public static InspectionDialog newInstance() {
		InspectionDialog dialog = new InspectionDialog();
		return dialog;
	}
	
	public InspectionDialog(){
		
	}
	
	public InspectionDialog(Inspection mData){
		this.mData=mData;
	}
	
	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_children_inspection, null);
		et_jcxm = (EditText) bodyView.findViewById(R.id.et_jcxm);
		et_jcr = (EditText) bodyView.findViewById(R.id.et_jcr);
		et_jcsj = (EditText) bodyView.findViewById(R.id.et_jcsj);
		et_jcjg = (EditText) bodyView.findViewById(R.id.et_jcjg);
		
		if(mData!=null){
			et_jcxm.setText(mData.getFzjc_fzjcxm());
			et_jcjg.setText(mData.getFzjc_jcjg());
			et_jcr.setText(mData.getFzjc_jcr());
			et_jcsj.setText(mData.getFzjc_jcsj());
		}
		
		return bodyView;
	}
	
	public Inspection getInspection(){
		Inspection inspection=new Inspection();
		inspection.setFzjc_fzjcxm(et_jcxm.getText().toString());
		inspection.setFzjc_jcr(et_jcr.getText().toString());
		inspection.setFzjc_jcsj(et_jcsj.getText().toString());
		inspection.setFzjc_jcjg(et_jcjg.getText().toString());
		return inspection;
	}
}
