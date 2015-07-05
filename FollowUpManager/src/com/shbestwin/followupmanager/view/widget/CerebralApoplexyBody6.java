package com.shbestwin.followupmanager.view.widget;

import java.util.Date;
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
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class CerebralApoplexyBody6 extends LinearLayout implements
		IBaseCerebralApoplexyBody {
	private RadioGroup rg_check;
	private EditText et_zz_cx_sj, et_zz_ngs_sj, et_zz_tia_sj, et_xzb_cx_sj,
			et_xzb_ngs_sj, et_xzb_tia_sj, et_pad, et_pad_sj, et_qt, et_qt_sj,
			et_mrs, et_mrs_jg, et_bi, et_bi_jg, et_ss, et_ss_jg, et_sfysqm;
	private boolean isHas = false;
	private FragmentManager fragmentManager;

	private LinearLayout xfzzsjzdLayout, qtxgsjLayout;

	private RadioButton rb_w, rb_y;

	public CerebralApoplexyBody6(Context context) {
		this(context, null);
	}

	public CerebralApoplexyBody6(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CerebralApoplexyBody6(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.view_cerebral_apoplexy_body6, this, true);
		rg_check = (RadioGroup) rootView.findViewById(R.id.rg_check);
		rb_w = (RadioButton) rootView.findViewById(R.id.rb_w);
		rb_y = (RadioButton) rootView.findViewById(R.id.rb_y);

		xfzzsjzdLayout = (LinearLayout) rootView
				.findViewById(R.id.xfzzsjzdLayout);
		qtxgsjLayout = (LinearLayout) rootView.findViewById(R.id.qtxgsjLayout);
		et_zz_cx_sj = (EditText) rootView.findViewById(R.id.et_zz_cx_sj);
		et_zz_ngs_sj = (EditText) rootView.findViewById(R.id.et_zz_ngs_sj);
		et_zz_tia_sj = (EditText) rootView.findViewById(R.id.et_zz_tia_sj);
		et_xzb_cx_sj = (EditText) rootView.findViewById(R.id.et_xzb_cx_sj);
		et_xzb_ngs_sj = (EditText) rootView.findViewById(R.id.et_xzb_ngs_sj);
		et_xzb_tia_sj = (EditText) rootView.findViewById(R.id.et_xzb_tia_sj);
		et_pad = (EditText) rootView.findViewById(R.id.et_pad);
		et_pad_sj = (EditText) rootView.findViewById(R.id.et_pad_sj);
		et_qt = (EditText) rootView.findViewById(R.id.et_qt);
		et_qt_sj = (EditText) rootView.findViewById(R.id.et_qt_sj);
		et_mrs = (EditText) rootView.findViewById(R.id.et_mrs);
		et_mrs_jg = (EditText) rootView.findViewById(R.id.et_mrs_jg);
		et_bi = (EditText) rootView.findViewById(R.id.et_bi);
		et_bi_jg = (EditText) rootView.findViewById(R.id.et_bi_jg);
		et_ss = (EditText) rootView.findViewById(R.id.et_ss);
		et_ss_jg = (EditText) rootView.findViewById(R.id.et_ss_jg);
		et_sfysqm = (EditText) rootView.findViewById(R.id.et_sfysqm);

		rg_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_w) {
					isHas = false;
				} else if (checkedId == R.id.rb_y) {
					isHas = true;
				}
			}
		});

		et_zz_cx_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_zz_cx_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_zz_cx_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_zz_ngs_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_zz_ngs_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_zz_ngs_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_zz_tia_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_zz_tia_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_zz_tia_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		et_xzb_cx_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xzb_cx_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_xzb_cx_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_xzb_ngs_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xzb_ngs_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_xzb_ngs_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_xzb_tia_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_xzb_tia_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_xzb_tia_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_pad_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_pad_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_pad_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		et_qt_sj.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		et_qt_sj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final DatePickerDialog datePickerDialog = DatePickerDialog
						.newInstance();
				datePickerDialog.show(fragmentManager, "datePickerDialog");
				datePickerDialog
						.setOnDatePickerListener(new OnDatePickerListener() {
							@Override
							public void onConfirmClick(long timeInMillis,
									String formatDate) {
								et_qt_sj.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

	}

	@Override
	public void getData(FollowUpStroke followUpStroke) {

		followUpStroke.setZdsj_sfzd(isHas);
		followUpStroke.setZdsj_xfczsjzd(ViewDataUtil.getCheckboxData(
				xfzzsjzdLayout, null)); // 新发卒中事件诊断
		followUpStroke.setZdsj_qtxgsj(ViewDataUtil.getCheckboxData(
				qtxgsjLayout, null));
		followUpStroke.setZdsj_ydmc(et_pad.getText().toString());
		followUpStroke.setZdsj_ydsj(et_pad_sj.getText().toString());
		followUpStroke.setZdsj_kfdlmc(et_qt.getText().toString());
		followUpStroke.setZdsj_kfdlsj(et_qt_sj.getText().toString());
		followUpStroke.setZdsj_pf_bi(et_bi.getText().toString());
		followUpStroke.setZdsj_pf_bijg(et_bi_jg.getText().toString());
		followUpStroke.setZdsj_pf_mrs(et_mrs.getText().toString());
		followUpStroke.setZdsj_pf_mrsjg(et_mrs_jg.getText().toString());
		followUpStroke.setZdsj_pf_sspf(et_ss.getText().toString());
		followUpStroke.setZdsj_pf_sspfjg(et_ss_jg.getText().toString());
		followUpStroke.setZdsj_sfysqm(et_sfysqm.getText().toString());
	}

	@Override
	public void setData(FollowUpStroke followUpStroke) {
		if (followUpStroke != null) {
			et_pad.setText(followUpStroke.getZdsj_ydmc());
			et_pad_sj.setText(followUpStroke.getZdsj_ydsj());
			et_qt.setText(followUpStroke.getZdsj_kfdlmc());
			et_qt_sj.setText(followUpStroke.getZdsj_kfdlsj());
			et_mrs.setText(followUpStroke.getZdsj_pf_mrs());
			et_mrs_jg.setText(followUpStroke.getZdsj_pf_mrsjg());
			et_bi.setText(followUpStroke.getZdsj_pf_bi());
			et_bi_jg.setText(followUpStroke.getZdsj_pf_bijg());
			et_ss.setText(followUpStroke.getZdsj_pf_sspf());
			et_ss_jg.setText(followUpStroke.getZdsj_pf_sspfjg());
			et_sfysqm.setText(followUpStroke.getZdsj_sfysqm());
			if (followUpStroke.getZdsj_sfzd()) {
				rb_y.setChecked(true);
				rb_w.setChecked(false);
			} else {
				rb_y.setChecked(false);
				rb_w.setChecked(true);
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
		this.fragmentManager = fragmentManager;
	}
}
