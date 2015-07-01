package com.shbestwin.followupmanager.fragment.archive;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.interfaces.FamilyProblemListItemClickHelp;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.FamilyProblem;
import com.shbestwin.followupmanager.view.adapter.archive.FamilyProblemAdapter;
import com.shbestwin.followupmanager.view.dialog.archive.FamilyProblemDialog;
import com.shbestwin.followupmanager.view.dialog.archive.FamilyProblemDialog.OnFamilyProblemDialog;

/**
 * 
 * 家庭问题
 *
 * @version
 */
public class FamilyProblemFragment extends BaseArchiveFragment implements FamilyProblemListItemClickHelp{

	private View familyProblemButton;
	private FamilyProblemAdapter familyProblemAdapter;
	private ListView familyProblemListView;	
	private FamilyProblem mFamilyProblem;
	private List<FamilyProblem> familyProblemList = new ArrayList<FamilyProblem>();
	public static FamilyProblemFragment newInstance() {
		FamilyProblemFragment fragment = new FamilyProblemFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_family_problem, container, false);
		familyProblemButton = rootView.findViewById(R.id.familyProblemButton);
		familyProblemListView = (ListView) rootView.findViewById(R.id.familyProblemListView);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		familyProblemAdapter = new FamilyProblemAdapter(getActivity(), familyProblemList, this);
		familyProblemListView.setAdapter(familyProblemAdapter);
		familyProblemButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showFamilyProblemDialog();
			}
		});	
		refreshData();
	}
	
	
	private void showFamilyProblemDialog() {
		final FamilyProblemDialog familyProblemDialog = FamilyProblemDialog.newInstance();
		familyProblemDialog.show(getChildFragmentManager(), "familyProblemDialog");
		familyProblemDialog.setOnFamilyProblemDialog(new OnFamilyProblemDialog() {
			@Override
			public void onConfirmClick(FamilyProblem data) {
				if(data != null){					
					mFamilyProblem = data;
					familyProblemList.add(mFamilyProblem);
					familyProblemAdapter.notifyDataSetChanged();
				}
				familyProblemDialog.hide();
			}
		});
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String familyProblem = "";
			familyProblem = getFamilyProblem();
			archiveInfo.setFamilyProblem(familyProblem);
		}
		return archiveInfo;
	
	}

	private String getFamilyProblem() {
		try {
			return new JsonUtil().objectsToJson(familyProblemList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		if(familyProblemButton == null){
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String familyProblem = archiveInfo.getFamilyProblem();
			if(familyProblem != null){
			Log.i("cjl", familyProblem);}
			if (TextUtils.isEmpty(familyProblem) || "[]".equals(familyProblem)) {
				
			} else {
				setFamilyProblem(familyProblem);		
			}
		}
	}

	private void setFamilyProblem(String familyProblem) {
		List<FamilyProblem> mfamilyProblemList = new ArrayList<FamilyProblem>();
		try {
			mfamilyProblemList = new JsonUtil().jsonToObjects(familyProblem, FamilyProblem.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num = mfamilyProblemList.size();
		familyProblemList.removeAll(familyProblemList);
			for(int i = 0;i < num;i++){
				familyProblemList.add(mfamilyProblemList.get(i));
			}
			familyProblemAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View item, View widget, final int position, int which) {
		// TODO Auto-generated method stub
		final FamilyProblem myFamilyProblem;
		myFamilyProblem = familyProblemList.get(position);
		switch (which) {
		case R.id.edit_button:	
			final FamilyProblemDialog familyProblemDialog = new FamilyProblemDialog(true,
					myFamilyProblem.getJd(),myFamilyProblem.getFsr(),
					myFamilyProblem.getFsrq(),myFamilyProblem.getZywt(),myFamilyProblem.getWtpg(),
					myFamilyProblem.getCljjg(),myFamilyProblem.getZgzl(),myFamilyProblem.getKgzl(),
					myFamilyProblem.getQt(),myFamilyProblem.getGljh(),myFamilyProblem.getJlys(),
					myFamilyProblem.getJlrq(),myFamilyProblem.getBz());
			familyProblemDialog.show(getChildFragmentManager(), "familyProblemDialog");

			familyProblemDialog.setOnFamilyProblemDialog(new OnFamilyProblemDialog() {
				@Override
				public void onConfirmClick(FamilyProblem familyProblem) {
					if(familyProblem != null){
						//familyProblemListView
						if(!familyProblem.toString().equals(myFamilyProblem.toString())){
							familyProblemList.set(position, familyProblem);
							familyProblemAdapter.notifyDataSetChanged();
						}						
					}		
					familyProblemDialog.hide();
				}
			});	
			break;
		case R.id.delete_button:
			familyProblemList.remove(position);
			familyProblemAdapter.notifyDataSetChanged();
			break;
		case R.id.detail_button:
			final FamilyProblemDialog familyProblemDialog2 = new FamilyProblemDialog(false,
					myFamilyProblem.getJd(),myFamilyProblem.getFsr(),
					myFamilyProblem.getFsrq(),myFamilyProblem.getZywt(),myFamilyProblem.getWtpg(),
					myFamilyProblem.getCljjg(),myFamilyProblem.getZgzl(),myFamilyProblem.getKgzl(),
					myFamilyProblem.getQt(),myFamilyProblem.getGljh(),myFamilyProblem.getJlys(),
					myFamilyProblem.getJlrq(),myFamilyProblem.getBz());
			familyProblemDialog2.show(getChildFragmentManager(), "familyProblemDialog");

			familyProblemDialog2.setOnFamilyProblemDialog(new OnFamilyProblemDialog() {
				@Override
				public void onConfirmClick(FamilyProblem familyProblem) {
					familyProblemDialog2.hide();
				}
			});	
			break;
		default:
			break;
		}
	}
}
