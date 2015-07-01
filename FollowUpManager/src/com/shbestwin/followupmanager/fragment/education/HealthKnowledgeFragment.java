package com.shbestwin.followupmanager.fragment.education;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;

/**
 * 
 * 健康知识
 *
 * @version
 */
public class HealthKnowledgeFragment extends BaseFragment {
	private AutoCompleteTextView searchACTextView;
	private Button searchButton;
	private TextView contentTextView;

	public static HealthKnowledgeFragment newInstance() {
		HealthKnowledgeFragment fragment = new HealthKnowledgeFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_education_health_knowledge, container, false);

		searchACTextView = (AutoCompleteTextView) rootView.findViewById(R.id.searchACTextView);
		searchButton = (Button) rootView.findViewById(R.id.searchButton);
		contentTextView = (TextView) rootView.findViewById(R.id.contentTextView);
		return rootView;
	}

	private String[] healthKnowledgeThemes;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		healthKnowledgeThemes = getResources().getStringArray(R.array.HealthKnowledgeThemes);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, healthKnowledgeThemes);
		searchACTextView.setAdapter(adapter);

		searchACTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				handleSearch();
			}
		});

		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleSearch();
			}
		});
	}

	private void handleSearch() {
		SystemUtils.hiddenIME(searchButton);
		String keyword = searchACTextView.getText().toString();
		if (TextUtils.isEmpty(keyword)) {
			ToastUtils.showToast(getActivity(), R.string.yyzd_keyword_empty);
			return;
		}

		int position = -1;
		for (int i = 0; i < healthKnowledgeThemes.length; i++) {
			if (healthKnowledgeThemes[i].equals(keyword)) {
				position = i;
			}
		}
		switch (position) {
		case 0:
			contentTextView.setText(R.string.jkjy_diet_guide_czfpfz);
			break;
		case 1:
			contentTextView.setText(R.string.jkjy_diet_guide_gxyfz);
			break;
		case 2:
			contentTextView.setText(R.string.jkjy_diet_guide_gxzfz);
			break;
		case 3:
			contentTextView.setText(R.string.jkjy_diet_guide_tnbfz);
			break;
		default:
			contentTextView.setText("此药物知识待添加");
			break;
		}
	}
}
