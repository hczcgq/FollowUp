package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;

/**
 * 
 * @ClassName: MentalDiseaseInspectionDialog
 * @Description: 精神病-实验室（辅助）检查
 * @author junbin.he
 * @date 2015年2月12日 下午2:29:51
 *
 */
public class MentalDiseaseInspectionDialog extends BaseDialogFragment {
	private EditText inspectionItemEditText;

	public static MentalDiseaseInspectionDialog newInstance() {
		MentalDiseaseInspectionDialog dialog = new MentalDiseaseInspectionDialog();
		return dialog;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_mental_disease_inspection, null);
		inspectionItemEditText = (EditText) bodyView.findViewById(R.id.inspectionItemEditText);
		SystemUtils.showIME(inspectionItemEditText, getActivity());
		return bodyView;
	}

	@Override
	protected int getWidth() {
		return (int) getResources().getDimension(R.dimen.dialog_mental_disease_inspection_width);
	}

}
