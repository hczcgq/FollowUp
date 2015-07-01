package com.shbestwin.followupmanager.fragment.search;

import java.util.Arrays;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.DateUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.common.util.ViewDataUtil;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog;
import com.shbestwin.followupmanager.view.dialog.DatePickerDialog.OnDatePickerListener;

public class SearchPeopleFragment extends BaseFragment implements
		OnClickListener {
	private Button btn_list, btn_image;
	private LinearLayout peopleListLinearLayout, peopleImageLinearLayout;
	private Spinner healthExaminationSpinner, quikeExaminationSpinner,
			imageTypeExaminationSpinner;
	private EditText startDateEditText, endDateEditText;
	private Button btn_search;
	
	private String[] examinationArray;

	public static SearchPeopleFragment newInstance() {
		SearchPeopleFragment fragment = new SearchPeopleFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_search_people,
				container, false);

		initView(rootView);
		return rootView;
	}

	private void initView(View rootView) {
		btn_list = (Button) rootView.findViewById(R.id.btn_list);
		btn_image = (Button) rootView.findViewById(R.id.btn_image);
		btn_list.setOnClickListener(this);
		btn_image.setOnClickListener(this);
		peopleListLinearLayout = (LinearLayout) rootView
				.findViewById(R.id.peopleListLinearLayout);
		peopleImageLinearLayout = (LinearLayout) rootView
				.findViewById(R.id.peopleImageLinearLayout);
		healthExaminationSpinner = (Spinner) rootView
				.findViewById(R.id.healthExaminationSpinner);
		quikeExaminationSpinner = (Spinner) rootView
				.findViewById(R.id.quikeExaminationSpinner);
		imageTypeExaminationSpinner = (Spinner) rootView
				.findViewById(R.id.imageTypeExaminationSpinner);
		startDateEditText = (EditText) rootView
				.findViewById(R.id.startDateEditText);
		endDateEditText = (EditText) rootView
				.findViewById(R.id.endDateEditText);
		btn_search = (Button) rootView.findViewById(R.id.btn_search);
		btn_search.setOnClickListener(this);
		// 使用当前日期
		startDateEditText.setText(DateUtils
				.formatDate(new Date(), "yyyy-MM-dd"));
		endDateEditText.setText(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		changePage(0);

		startDateEditText.setOnClickListener(new OnClickListener() {
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
								startDateEditText.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});

		endDateEditText.setOnClickListener(new OnClickListener() {
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
								endDateEditText.setText(formatDate);
								datePickerDialog.hide();
							}
						});
			}
		});
		
		
		healthExaminationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				System.out.println("position--------"+ViewDataUtil.getSpinnerData(healthExaminationSpinner));
				if(position==0){
					examinationArray=getActivity().getResources().getStringArray(R.array.quickExamination);
				}else if(position==1){
					examinationArray=getActivity().getResources().getStringArray(R.array.followupExamination);
				}
				
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,Arrays.asList(examinationArray));
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置
				quikeExaminationSpinner.setAdapter(adapter);// 设置显示信息 
		        
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_list:
			btn_list.setEnabled(false);
			btn_image.setEnabled(true);
			peopleListLinearLayout.setVisibility(View.VISIBLE);
			peopleImageLinearLayout.setVisibility(View.GONE);
			changePage(0);
			break;
		case R.id.btn_image:
			btn_list.setEnabled(true);
			btn_image.setEnabled(false);
			peopleListLinearLayout.setVisibility(View.GONE);
			peopleImageLinearLayout.setVisibility(View.VISIBLE);
			changePage(1);
			break;
		}
	}

	private void changePage(int index) {
		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();
		ft.replace(R.id.peoplemainlayout, newInstance(index));
		ft.commitAllowingStateLoss();
	}

	private Fragment newInstance(int index) {
		switch (index) {
		case 0:
			return SearchPeopleListFragment.newInstance();
		case 1:
			return SearchPeopleImageFragment.newInstance();
		}
		return null;
	}

}
