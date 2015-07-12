package com.shbestwin.followupmanager.view.dialog.followup;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.fragment.ArchiveInfoFragment;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.fragment.FollowUpManagerFragment;
import com.shbestwin.followupmanager.fragment.HealthEducationFragment;
import com.shbestwin.followupmanager.fragment.HealthExaminationFragment;
import com.shbestwin.followupmanager.fragment.MedicationGuideFragment;
import com.shbestwin.followupmanager.fragment.examination.AgednessAssessmentFragment;
import com.shbestwin.followupmanager.fragment.examination.GeneralExaminationFragment;
import com.shbestwin.followupmanager.fragment.examination.PhysiqueIdentifyFragment;
import com.shbestwin.followupmanager.fragment.examination.PsychologicaAssessmentFragment;
import com.shbestwin.followupmanager.fragment.examination.QuickExaminationFragment;
import com.shbestwin.followupmanager.fragment.report.HypertensionReportFragment1;
import com.shbestwin.followupmanager.fragment.report.HypertensionReportFragment2;
import com.shbestwin.followupmanager.fragment.report.HypertensionReportFragment3;
import com.shbestwin.followupmanager.model.followup.Inspection;
import com.shbestwin.followupmanager.view.dialog.BaseDialogFragment;
import com.shbestwin.followupmanager.view.dialog.BaseDialogReportFragment;
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
public class FollowupReportDialog extends BaseDialogReportFragment {
	private ViewPager contentViewPager;
	private List<BaseFragment> contentFragmentList;

	public static FollowupReportDialog newInstance() {
		FollowupReportDialog dialog = new FollowupReportDialog();
		return dialog;
	}

	public FollowupReportDialog() {

	}

	@Override
	protected View getBodyView(LayoutInflater inflater) {
		View rootView = View.inflate(getActivity(),
				R.layout.dialog_followup_report, null);
		contentViewPager = (ViewPager) rootView
				.findViewById(R.id.contentViewPager);
		initContentView();
		return rootView;
	}

	private void initContentView() {
		contentFragmentList = new ArrayList<BaseFragment>();
		contentFragmentList.add(HypertensionReportFragment1.newInstance());
		contentFragmentList.add(HypertensionReportFragment2.newInstance());
		contentFragmentList.add(HypertensionReportFragment3.newInstance());

		// contentViewPager.setPageTransformer(true, new
		// RotateDownTransformer());
		// 给ViewPager设置适配器
		contentViewPager.setAdapter(new ContentFragmentPagerAdapter(
				getChildFragmentManager()));
		contentViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		contentViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// leftMenuLayout.checkItem(position);
				// renderPage(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});// 页面变化时的监听器
	}

	private class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

		public ContentFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return contentFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return contentFragmentList.size();
		}
	}

}
