package com.shbestwin.followupmanager.fragment.search;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.http.util.TextUtils;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.http.HttpHelper;
import com.shbestwin.followupmanager.model.MessageItem;
import com.shbestwin.followupmanager.model.UploadManageItem;
import com.shbestwin.followupmanager.view.adapter.UploadManageAdapter;

public class SearchUploadFragment extends BaseFragment {

    private Button btn_upload, btn_download;

    private ListView listView;

    private static final int UPLOAD = 0;

    private static final int DOWNLOAD = 1;

    private SharedPreferences preferences;

    private String upload_manage;

    private List<UploadManageItem> mArraylist = new ArrayList<UploadManageItem>();

    private UploadManageAdapter adapter;

    public static SearchUploadFragment newInstance() {
        SearchUploadFragment fragment = new SearchUploadFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_upload,
                container, false);
        btn_upload = (Button) rootView.findViewById(R.id.btn_upload);
        btn_download = (Button) rootView.findViewById(R.id.btn_download);
        listView = (ListView) rootView.findViewById(R.id.listView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        preferences = getActivity().getSharedPreferences("USER_INFO",
                Context.MODE_PRIVATE);
        if (preferences.contains("UploadManage")) {
            upload_manage = preferences.getString("UploadManage", null);
            System.out.println("upload_manage" + upload_manage);
            if (!TextUtils.isEmpty(upload_manage)) {
                List<UploadManageItem> list = new Gson().fromJson(
                        upload_manage, new TypeToken<List<UploadManageItem>>() {
                        }.getType());
                if (list != null && list.size() > 0) {
                    mArraylist.addAll(list);
                }

                refreshAdapter();
            }
            System.out.println(mArraylist);
        }

        btn_download.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new GetDataTask(DOWNLOAD).execute();
            }
        });

        btn_upload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new GetDataTask(UPLOAD).execute();
            }
        });
    }

    private void refreshAdapter() {
        if (adapter == null) {
            adapter = new UploadManageAdapter(getActivity(), mArraylist);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
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

    private class GetDataTask extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        private int state;

        public GetDataTask(int state) {
            this.state = state;
        }

        @Override
        protected String doInBackground(String... params) {
        	String ServerUrl=getActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE).getString("Server_Url", "");
            File file = getActivity().getApplication().getDatabasePath(
                    "follow_up_manager.db");
            String result = null;
            if (isNetworkAvailable(getActivity())) {
                if (state == UPLOAD) {
                    if (file.exists()) {
                        // 上传
                        HashMap<String, String> hashParams = new HashMap<String, String>();
                        hashParams.put("username", preferences.getString("Username", "t_user"));
                        hashParams.put("password ", preferences.getString("Password", "t_password"));
                        hashParams.put("datafile ", file.getAbsolutePath());
                        try {
                            result = HttpHelper
                                    .uploadHttpClient(
                                            getActivity(),
                                            ServerUrl+"/inter/upDataFile",
                                            hashParams, file.getAbsolutePath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "数据文件不存在！");
                    }
                } else if (state == DOWNLOAD) {
                    // 下载
                    HashMap<String, String> hashParams = new HashMap<String, String>();
                    hashParams.put("username", preferences.getString("Username", "t_user"));
                    hashParams.put("password ", preferences.getString("Password", "t_password"));
                    result = HttpHelper.DowmloadHttpClient(getActivity(),
                    		ServerUrl+"/inter/downFile",
                            hashParams, file);
                }
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
                if (state == UPLOAD) {
                    MessageItem msg = new Gson().fromJson(result,
                            MessageItem.class);
                    if (msg.getSuccess().equals("true")) {
                        mArraylist.add(new UploadManageItem("上传", "上传成功",
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                                        .format(new Date())));
                    } else {
                        mArraylist.add(new UploadManageItem("上传", "上传失败",
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                                        .format(new Date())));
                    }
                    ToastUtils.showToast(getActivity(), msg.getMsg());
                } else if (state == DOWNLOAD) {
                    MessageItem msg = new Gson().fromJson(result,
                            MessageItem.class);
                    if (msg.getSuccess().equals("true")) {
                        mArraylist.add(new UploadManageItem("下载", "下载成功",
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                                        .format(new Date())));
                    } else {
                        mArraylist.add(new UploadManageItem("下载", "下载失败",
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                                        .format(new Date())));
                    }
                    ToastUtils.showToast(getActivity(), msg.getMsg());
                }

                refreshAdapter();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("UploadManage", new Gson().toJson(mArraylist));
                editor.commit();
            }
        }

        @Override
        protected void onPreExecute() {
            String msg = "请求网络。。。";
            if (state == UPLOAD) {
                msg = "正在上传。。。";
            } else if (state == DOWNLOAD) {
                msg = "正在下载。。。";
            }
            progressDialog = ProgressDialog.show(getActivity(), "温馨提示", msg,
                    false, true);
            progressDialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
        }
    }
}
