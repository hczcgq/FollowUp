package com.shbestwin.followupmanager.view.dialog.followup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.model.followup.LabInspection;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

/**
 * 
 * @ClassName: ChildrenInspectionDialog
 * @Description: 儿童访视-实验室检查、辅助检查
 * @author junbin.he
 * @date 2015年2月12日 下午2:29:51
 *
 */
public class LabInspectionDialog extends BaseDialogFragment {
    private EditText et_jcxm, et_jcr, et_jcsj, et_jcjg;

    private LabInspection mData;

    public static LabInspectionDialog newInstance() {
        LabInspectionDialog dialog = new LabInspectionDialog();
        return dialog;
    }

    public LabInspectionDialog() {

    }

    public LabInspectionDialog(LabInspection mData) {
        this.mData = mData;
    }

    @Override
    protected View getBodyView(LayoutInflater inflater) {
        View bodyView = View.inflate(getActivity(),
                R.layout.dialog_children_inspection, null);
        et_jcxm = (EditText) bodyView.findViewById(R.id.et_jcxm);
        et_jcr = (EditText) bodyView.findViewById(R.id.et_jcr);
        et_jcsj = (EditText) bodyView.findViewById(R.id.et_jcsj);
        et_jcjg = (EditText) bodyView.findViewById(R.id.et_jcjg);

        et_jcsj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final DatePickerDialog datePickerDialog = DatePickerDialog
                        .newInstance();
                datePickerDialog.show(getChildFragmentManager(),
                        "datePickerDialog");
                datePickerDialog
                        .setOnDatePickerListener(new OnDatePickerListener() {
                            @Override
                            public void onConfirmClick(long timeInMillis,
                                    String formatDate) {
                                et_jcsj.setText(formatDate);
                                datePickerDialog.hide();
                            }
                        });

            }
        });

        if (mData != null) {
            et_jcxm.setText(mData.getSysjc_sysjcxm());
            et_jcjg.setText(mData.getSysjc_jcjg());
            et_jcr.setText(mData.getSysjc_jcr());
            et_jcsj.setText(mData.getSysjc_jcsj());
        }

        return bodyView;
    }

    public LabInspection getInspection() {
        LabInspection inspection = new LabInspection();
        inspection.setSysjc_sysjcxm(et_jcxm.getText().toString());
        inspection.setSysjc_jcr(et_jcr.getText().toString());
        inspection.setSysjc_jcsj(et_jcsj.getText().toString());
        inspection.setSysjc_jcjg(et_jcjg.getText().toString());
        return inspection;
    }
}
