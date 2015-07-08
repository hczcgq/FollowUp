package com.shbestwin.followupmanager.fragment.examination;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BaseQuickExaminationFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodFatFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodGlucoseFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodOximeterFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BloodPressureFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.BodyCompositionFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.ElectrocardiogramAnalyzerFragment;
import com.shbestwin.followupmanager.fragment.examination.quick.RoutineExaminationFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.manager.device.PrintManager;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.examination.GeneralExamination;
import com.shbestwin.followupmanager.view.widget.IBaseGeneralExaminationBody;
import com.shbestwin.followupmanager.view.widget.TabMenuLayout;

/**
 * 
 * 快速体检
 *
 * @version
 */
public class QuickExaminationFragment extends BaseFragment {
    private TabMenuLayout tabMenuLayout;

    private ViewPager bodyViewPager;

    private List<BaseQuickExaminationFragment> contentFragmentList;

    public static QuickExaminationFragment newInstance() {
        QuickExaminationFragment fragment = new QuickExaminationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_examination_quick_examination, container,
                false);
        tabMenuLayout = (TabMenuLayout) rootView
                .findViewById(R.id.tabMenuLayout);
        bodyViewPager = (ViewPager) rootView.findViewById(R.id.bodyViewPager);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTabView();
        initBodyView();
    }

    private void initTabView() {
        String[] tabMenu = getResources().getStringArray(
                R.array.jktjQuickExaminationTabMenu);
        tabMenuLayout.renderMenu(tabMenu);
        tabMenuLayout.checkItem(0);

        tabMenuLayout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bodyViewPager.setCurrentItem(checkedId);
            }
        });
    }

    /**
     * 初始化内容视图
     *
     */
    private void initBodyView() {
        contentFragmentList = new ArrayList<BaseQuickExaminationFragment>();
        contentFragmentList.add(RoutineExaminationFragment.newInstance());// 常规体检
        contentFragmentList.add(BloodPressureFragment.newInstance());// 血压
        contentFragmentList.add(BloodGlucoseFragment.newInstance());// 血糖
        contentFragmentList.add(BloodFatFragment.newInstance());// 血脂
        contentFragmentList.add(BloodOximeterFragment.newInstance());// 血氧
        contentFragmentList
                .add(ElectrocardiogramAnalyzerFragment.newInstance());// 心电分析
        contentFragmentList.add(BodyCompositionFragment.newInstance());// 人体成分
        // contentFragmentList.add(HumorFragment.newInstance());// 体液

        // contentViewPager.setPageTransformer(true, new
        // RotateDownTransformer());
        // 给ViewPager设置适配器
        bodyViewPager.setAdapter(new ContentFragmentPagerAdapter(
                getChildFragmentManager()));
        bodyViewPager.setCurrentItem(0);// 设置当前显示标签页为第一页
        bodyViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabMenuLayout.checkItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });// 页面变化时的监听器
        ExaminationInfo generalExamination = MyApplication.getInstance()
                .getExaminationInfo();
        if (generalExamination != null) {
            BaseQuickExaminationFragment baseFragment = contentFragmentList
                    .get(bodyViewPager.getCurrentItem());
            baseFragment.getSaveData(generalExamination);
            
            System.out.println(generalExamination.getRoutineCheckups());
        }

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

    @Override
    public void onConclusion() {
        BaseFragment baseFragment = contentFragmentList.get(bodyViewPager
                .getCurrentItem());
        baseFragment.onConclusion();
    }

    @Override
    public void onPrint() {

        ExaminationInfo examinationInfo = MyApplication.getInstance()
                .getExaminationInfo();
        BaseQuickExaminationFragment baseFragment = contentFragmentList
                .get(bodyViewPager.getCurrentItem());
        String printData = baseFragment.getPrintData(examinationInfo
                .getExaminationNo());
        PrintManager printManager = new PrintManager(getActivity());

        if (printManager.connectDevice()) {
            printManager.print(printData);
            printManager.closeDevice();
        }
    }

    @Override
    public void onSave() {
        ExaminationInfo examinationInfo = MyApplication.getInstance()
                .getExaminationInfo();
        if (examinationInfo == null) {
            ToastUtils.showToast(getActivity(), "请先进行体检登记!");
            return;
        }

        BaseQuickExaminationFragment baseFragment = contentFragmentList
                .get(bodyViewPager.getCurrentItem());
        baseFragment.getSaveData(examinationInfo);

        ExaminationManager.getInstance(getActivity())
                .saveOrUpdateExaminationInfo(examinationInfo);
        ToastUtils.showToast(getActivity(), "保存数据成功！");
        MyApplication.getInstance().setExaminationInfo(examinationInfo);
    }

    @Override
    public void onUpload() {
        BaseFragment baseFragment = contentFragmentList.get(bodyViewPager
                .getCurrentItem());
        baseFragment.onUpload();
    }

}
