package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;

/**
 * 
 * @ClassName: MentalDiseaseMedicineDialog
 * @Description: 精神病-用药情况
 * @author junbin.he
 * @date 2015年2月12日 下午2:29:51
 *
 */
public class MentalDiseaseMedicineDialog extends BaseDialogFragment {
	private EditText medicineNameEditText;

	public static MentalDiseaseMedicineDialog newInstance() {
		MentalDiseaseMedicineDialog dialog = new MentalDiseaseMedicineDialog();
		return dialog;
	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View bodyView = View.inflate(getActivity(), R.layout.dialog_followup_medicine, null);
		medicineNameEditText = (EditText) bodyView.findViewById(R.id.medicineNameEditText);
		SystemUtils.showIME(medicineNameEditText, getActivity());
		return bodyView;
	}

//	@Override
//	protected int getWidth() {
//		return (int) getResources().getDimension(R.dimen.dialog_mental_disease_inspection_width);
//	}

}
