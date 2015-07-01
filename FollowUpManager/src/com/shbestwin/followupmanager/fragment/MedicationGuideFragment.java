package com.shbestwin.followupmanager.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import android.widget.ListView;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.manager.MedicationGuideManager;
import com.shbestwin.followupmanager.model.MedicationInfo;

/**
 * 
 * @ClassName: MedicationGuideFragment
 * @Description: 用药指导
 *
 */
public class MedicationGuideFragment extends BaseFragment {
	private AutoCompleteTextView searchACTextView;
	private Button searchButton;

	private ListView leftMenuListView;
	private ViewPager contentViewPager;
	
	private MedicationInfo medicationInfo;

	public static MedicationGuideFragment newInstance() {
		MedicationGuideFragment fragment = new MedicationGuideFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_medication_guide, container, false);
		searchACTextView = (AutoCompleteTextView) rootView.findViewById(R.id.searchACTextView);
		searchButton = (Button) rootView.findViewById(R.id.searchButton);
		leftMenuListView =(ListView) rootView.findViewById(R.id.leftMenuListView);
		contentViewPager = (ViewPager) rootView.findViewById(R.id.contentViewPager);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initLeftMenu();
		
		String[] medications = MedicationGuideManager.getInstance(getActivity()).getMedicationList();
		if (!ArrayUtils.isEmpty(medications)) {
			for (int i = 0; i < medications.length; i++) {
				mLog.i(TAG, medications[i]);
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, medications);
			searchACTextView.setAdapter(adapter);
		}

		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SystemUtils.hiddenIME(v);
				handleSearch();
			}
		});

		initContentView();
	}
	
	private void initLeftMenu() {
		String menuItems[] = getResources().getStringArray(R.array.MedicationMenuItem);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.view_medication_guide_menu_item, menuItems);
		leftMenuListView.setAdapter(adapter);
		leftMenuListView.setItemChecked(0, true);
		leftMenuListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				leftMenuListView.setItemChecked(position, true);
				contentViewPager.setCurrentItem(position);
			}
		});
	}
	

	private ArrayList<View> contentViewList = new ArrayList<View>();
	private ContentViewPagerAdapter contentViewPagerAdapter;

	private void initContentView() {
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewList.add(View.inflate(getActivity(), R.layout.view_medication_guide_content_item, null));
		contentViewPagerAdapter = new ContentViewPagerAdapter();
		contentViewPager.setAdapter(contentViewPagerAdapter);

		contentViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				leftMenuListView.setItemChecked(position, true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		changeContentData();
	}



	private void changeContentData() {
		if (medicationInfo == null) {
			medicationInfo = new MedicationInfo();
		}
		// 使用默认值
		if (TextUtils.isEmpty(medicationInfo.getPharmacology())) {
			medicationInfo.setPharmacology(getString(R.string.yyzd_default_content_pharmacology));// 1、药理学
		}
		if (TextUtils.isEmpty(medicationInfo.getIndications())) {
			medicationInfo.setIndications(getString(R.string.yyzd_default_content_indications));// 2、适用症
		}

		if (TextUtils.isEmpty(medicationInfo.getContraindications())) {
			medicationInfo.setContraindications(getString(R.string.yyzd_default_content_contraindications));// 3、禁忌症
		}

		if (TextUtils.isEmpty(medicationInfo.getAdverseReactions())) {
			medicationInfo.setAdverseReactions(getString(R.string.yyzd_default_content_adverseReactions));// 4、不良反应
		}

		if (TextUtils.isEmpty(medicationInfo.getNotes())) {
			medicationInfo.setNotes(getString(R.string.yyzd_default_content_notes));// 5、注意事项
		}

		if (TextUtils.isEmpty(medicationInfo.getInteraction())) {
			medicationInfo.setInteraction(getString(R.string.yyzd_default_content_interaction));// 6、相互作用
		}

		setItemContent(0, medicationInfo.getPharmacology());// 1、药理学
		setItemContent(1, medicationInfo.getIndications());// 2、适用症
		setItemContent(2, medicationInfo.getContraindications());// 3、禁忌症
		setItemContent(3, medicationInfo.getAdverseReactions());// 4、不良反应
		setItemContent(4, medicationInfo.getNotes());// 5、注意事项
		setItemContent(5, medicationInfo.getInteraction());// 6、相互作用

	}

	private void setItemContent(int index, String content) {
		((TextView) contentViewList.get(index).findViewById(R.id.contentTextView)).setText(content);
	}

	private class ContentViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return contentViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(contentViewList.get(position));

		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(contentViewList.get(position), 0);
			return contentViewList.get(position);
		}
	}

	private void handleSearch() {
		String keyword = searchACTextView.getText().toString();
		if (TextUtils.isEmpty(keyword)) {
			ToastUtils.showToast(getActivity(), R.string.yyzd_keyword_empty);
			return;
		}

		medicationInfo = MedicationGuideManager.getInstance(getActivity()).getMedicationInfo(keyword);
		changeContentData();
	}
}
