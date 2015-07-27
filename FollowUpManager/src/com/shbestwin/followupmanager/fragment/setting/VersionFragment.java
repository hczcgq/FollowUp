package com.shbestwin.followupmanager.fragment.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.http.UpdateThread;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.report.ReportDiabetesMellitus;
import com.shbestwin.followupmanager.view.dialog.ReportConfirmDialog;
import com.shbestwin.followupmanager.view.dialog.ReportConfirmDialog.OnConfirmClickListener;
import com.shbestwin.followupmanager.view.dialog.followup.FollowupDiabetesMellitusReportDialog;

public class VersionFragment extends BaseFragment {
    private TextView tipsTextView;

    private Button updateVersionButton;

    public static VersionFragment newInstance() {
        VersionFragment fragment = new VersionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting_version,
                container, false);
        tipsTextView = (TextView) rootView.findViewById(R.id.tipsTextView);
        updateVersionButton = (Button) rootView
                .findViewById(R.id.updateVersionButton);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO 检查是本地登录还是网络登录
        String tips = "立创家庭医生移动随访系统软件V3.0\n杭州立创生物科技有限公司\n当前版本号："
                + SystemUtils.getVersionName(getActivity())
                + "\n当前是本地登录，无法判断是否需要更新。";
        tipsTextView.setText(tips);
        updateVersionButton.setEnabled(false);
        // 如果为本地登录，则不允许更新版本
        updateVersionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                showUpdateVersionDialog();
            }
        });
    }

    private void showUpdateVersionDialog(String desc, final String url,
            String external_ver) {
        final ReportConfirmDialog medicationDialog = new ReportConfirmDialog(
                "发现新本版", "确认是否更新！");
        medicationDialog.show(
                ((FragmentActivity) getActivity()).getSupportFragmentManager(),
                "medicationDialog");
        medicationDialog
                .setOnConfirmClickListener(new OnConfirmClickListener() {

                    @Override
                    public void onConfirmClick() {
                        UpdateThread uthread = new UpdateThread(url,
                                getActivity());
                        Thread thread = new Thread(uthread);
                        thread.start();
                        medicationDialog.hide();

                    }
                });
    }
}
