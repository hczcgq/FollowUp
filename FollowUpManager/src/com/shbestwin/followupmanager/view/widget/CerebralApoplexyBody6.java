package com.shbestwin.followupmanager.view.widget;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.model.followup.FollowUpStroke;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class CerebralApoplexyBody6 extends LinearLayout implements
		IBaseCerebralApoplexyBody {

	private RadioGroup rg_check;
	private CheckBox ck_zz_cx, ck_zz_ngs, ck_zz_tia, ck_xzb_cx, ck_xzb_ngs,
			ck_xzb_tia;
	private EditText et_zz_cx_sj, et_zz_ngs_sj, et_zz_tia_sj, et_xzb_cx_sj,
			et_xzb_ngs_sj, et_xzb_tia_sj, et_pad, et_pad_sj, et_qt, et_qt_sj,
			et_mrs, et_mrs_jg, et_bi, et_bi_jg, et_ss, et_ss_jg, et_sfysqm;
	private boolean isHas = false;
	private FragmentManager fragmentManager;
	private HashMap<Integer, String> map_xfzzsjzd = new HashMap<Integer, String>();
	private HashMap<Integer, String> map_atxgsj = new HashMap<Integer, String>();

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

		ck_zz_cx = (CheckBox) rootView.findViewById(R.id.ck_zz_cx);
		ck_zz_ngs = (CheckBox) rootView.findViewById(R.id.ck_zz_ngs);
		ck_zz_tia = (CheckBox) rootView.findViewById(R.id.ck_zz_tia);
		ck_xzb_cx = (CheckBox) rootView.findViewById(R.id.ck_xzb_cx);
		ck_xzb_ngs = (CheckBox) rootView.findViewById(R.id.ck_xzb_ngs);
		ck_xzb_tia = (CheckBox) rootView.findViewById(R.id.ck_xzb_tia);

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

		ck_zz_cx.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					map_xfzzsjzd.put(0, buttonView.getText().toString());
				} else {
					map_xfzzsjzd.remove(0);
				}
			}
		});
		ck_zz_ngs
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							map_xfzzsjzd
									.put(1, buttonView.getText().toString());
						} else {
							map_xfzzsjzd.remove(1);
						}
					}
				});
		ck_zz_tia
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							map_xfzzsjzd
									.put(2, buttonView.getText().toString());
						} else {
							map_xfzzsjzd.remove(2);
						}
					}
				});

		ck_xzb_cx
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							map_atxgsj.put(0, buttonView.getText().toString());
						} else {
							map_atxgsj.remove(0);
						}
					}
				});
		ck_xzb_ngs
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							map_atxgsj.put(1, buttonView.getText().toString());
						} else {
							map_atxgsj.remove(1);
						}
					}
				});
		ck_xzb_tia
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							map_atxgsj.put(2, buttonView.getText().toString());
						} else {
							map_atxgsj.remove(2);
						}
					}
				});
	}

	@Override
	public void getData(FollowUpStroke followUpStroke) {
		
		JsonObject json_xfzzsjzd=new JsonObject();
		JsonObject json_qtxgsj=new JsonObject();
		JsonArray array_xfzzsjzd=new JsonArray();
		JsonArray array_qtxgsj=new JsonArray();
		
		Iterator iterator_xfzzsjzd=map_xfzzsjzd.entrySet().iterator();
		while (iterator_xfzzsjzd.hasNext()) {
			Map.Entry entry =  (Entry) iterator_xfzzsjzd.next();
			JsonObject object=new JsonObject();
			object.addProperty("zdsj_xfczsjzd_num", String.valueOf(entry.getKey()));
			object.addProperty("zdsj_xfczsjzd_name", String.valueOf(entry.getValue()));
			if(entry.getKey()=="0"){
				object.addProperty("zdsj_xfczsjzd_date",et_zz_cx_sj.getText().toString());
			}else if(entry.getKey()=="1"){
				object.addProperty("zdsj_xfczsjzd_date",et_zz_ngs_sj.getText().toString());
			}else if(entry.getKey()=="2"){
				object.addProperty("zdsj_xfczsjzd_date",et_zz_tia_sj.getText().toString());
			}
			array_xfzzsjzd.add(object);
		}
		json_xfzzsjzd.add("zdsj_xfczsjzd", array_xfzzsjzd);
		
		
		Iterator iterator_qtxgsj=map_xfzzsjzd.entrySet().iterator();
		while (iterator_qtxgsj.hasNext()) {
			Map.Entry entry =  (Entry) iterator_qtxgsj.next();
			JsonObject object=new JsonObject();
			object.addProperty("zdsj_qtxgsj_num", String.valueOf(entry.getKey()));
			object.addProperty("zdsj_qtxgsj_name", String.valueOf(entry.getValue()));
			if(entry.getKey()=="0"){
				object.addProperty("zdsj_qtxgsj_date",et_xzb_cx_sj.getText().toString());
			}else if(entry.getKey()=="1"){
				object.addProperty("zdsj_qtxgsj_date",et_xzb_ngs_sj.getText().toString());
			}else if(entry.getKey()=="2"){
				object.addProperty("zdsj_qtxgsj_date",et_xzb_tia_sj.getText().toString());
			}
			array_qtxgsj.add(object);
		}
		json_qtxgsj.add("zdsj_qtxgsj", array_qtxgsj);

		followUpStroke.setZdsj_sfzd(isHas);
		followUpStroke.setZdsj_xfczsjzd(json_xfzzsjzd.toString());  //新发卒中事件诊断
		followUpStroke.setZdsj_qtxgsj(json_qtxgsj.toString());
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
		// TODO Auto-generated method stub

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
