package com.shbestwin.followupmanager.fragment.setting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.SystemUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.http.HttpHelper;
import com.shbestwin.followupmanager.http.UpdateThread;
import com.shbestwin.followupmanager.manager.AccompanyManager;
import com.shbestwin.followupmanager.manager.FollowUpManager;
import com.shbestwin.followupmanager.model.Accompany;
import com.shbestwin.followupmanager.model.MessageItem;
import com.shbestwin.followupmanager.model.UploadManageItem;
import com.shbestwin.followupmanager.model.VersionItem;
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
        // String tips = "立创家庭医生移动随访系统软件V3.0\n杭州立创生物科技有限公司\n当前版本号："
        // + SystemUtils.getVersionName(getActivity())
        // + "\n当前是本地登录，无法判断是否需要更新。";
        String tips = "立创家庭医生移动随访系统软件V3.0\n杭州立创生物科技有限公司\n当前版本号："
                + SystemUtils.getVersionName(getActivity());
        tipsTextView.setText(tips);
        // updateVersionButton.setEnabled(false);
        // 如果为本地登录，则不允许更新版本
        updateVersionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // showUpdateVersionDialog();

                new GetDataTask().execute();
            }
        });
    }

    public boolean isNetworkAvailable(Context context) {
        boolean value = false;
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            value = true;
        }
        return value;
    }

    private String getVersionName() {
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity()
                    .getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    private int getVersionCode() {
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity()
                    .getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }

    private String getImsi() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) getActivity()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSubscriberId();
    }

    private void showUpdateVersionDialog(String desc, final String url,
            String external_ver) {
        final ReportConfirmDialog medicationDialog = new ReportConfirmDialog(
                "发现新本版", "确认是否更新！", "确认");
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

    private class GetDataTask extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            String ServerUrl = getActivity().getSharedPreferences("USER_INFO",
                    Context.MODE_PRIVATE).getString("Server_Url",
                    MyApplication.serverUrl);
            String result = null;
            String versionCode = String.valueOf(getVersionCode());
            String versionName = getVersionName();
            String IMSI = getImsi();
            String client = "Android";
            if (isNetworkAvailable(getActivity())) {
                HashMap<String, String> hashParams = new HashMap<String, String>();
                hashParams.put("currentversion", versionName);
                hashParams.put("appCode ", versionCode);
                hashParams.put("clientType ", client);
                hashParams.put("imsi ", IMSI);
                result = HttpHelper.GetHttpClient(getActivity(), ServerUrl
                        + "/inter/updateSoftVersion", hashParams);
            } else {
                ToastUtils.showToast(getActivity(), "请检查网络连接。");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (result != null) {
                System.out.println("请求结果：" + result);
                VersionItem versionItem = new Gson().fromJson(result,
                        VersionItem.class);
                System.out.println(versionItem.getVersion()+"---"+getVersionName());
                if (!versionItem.getVersion().equals(getVersionName())) {
                    showUpdateVersionDialog(versionItem.getDesc(),
                            versionItem.getDownPath(), versionItem.getVersion());
                } else {
                    ToastUtils.showToast(getActivity(), "已是最新版本。");
                }
            }
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), "温馨提示",
                    "正在检查更新", false, true);
            progressDialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
        }
    }
}
