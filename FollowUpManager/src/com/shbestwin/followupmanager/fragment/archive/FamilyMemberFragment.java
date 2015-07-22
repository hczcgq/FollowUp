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
import com.shbestwin.followupmanager.interfaces.FamilyMemberListItemClickHelp;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.FamilyMember;
import com.shbestwin.followupmanager.view.adapter.archive.FamilyMemberAdapter;
import com.shbestwin.followupmanager.view.dialog.archive.FamilyMemberDialog;
import com.shbestwin.followupmanager.view.dialog.archive.FamilyMemberDialog.OnFamilyMemberDialog;

/**
 * 
 * 家庭成员
 *
 * @version
 */
public class FamilyMemberFragment extends BaseArchiveFragment implements FamilyMemberListItemClickHelp{
	private View familyMermberButton;
	private FamilyMemberAdapter familyMemberAdapter;
	private ListView familyMemberListView;	
	private FamilyMember mFamilyMember;
	private List<FamilyMember> familyMemberList = new ArrayList<FamilyMember>();
	
	public static FamilyMemberFragment newInstance() {
		FamilyMemberFragment fragment = new FamilyMemberFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_family_member, container, false);
		familyMermberButton = rootView.findViewById(R.id.familyMermberButton);
		familyMemberListView = (ListView) rootView.findViewById(R.id.familyMemberListView);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		familyMemberAdapter = new FamilyMemberAdapter(getActivity(), familyMemberList, this);
		familyMemberListView.setAdapter(familyMemberAdapter);
		familyMermberButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showFamilyMemberDialog();
			}
		});
		refreshData();	
	}
	
	
	private void showFamilyMemberDialog() {
		final FamilyMemberDialog familyMemberDialog = FamilyMemberDialog.newInstance();
		familyMemberDialog.show(getChildFragmentManager(), "familyMemberDialog");
		familyMemberDialog.setOnFamilyMemberDialog(new OnFamilyMemberDialog() {
			
			@Override
			public void onConfirmClick(FamilyMember familyMember) {
				// TODO Auto-generated method stub
				if(familyMember != null){
					
					mFamilyMember = familyMember;
					familyMemberList.add(mFamilyMember);
					familyMemberAdapter.notifyDataSetChanged();
					familyMemberDialog.hide();
				}
				
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
			String familyMember = "";
			familyMember = getFamilyMember();
			archiveInfo.setFamilyMermber(familyMember);
		}
		return archiveInfo;
	
	}

	private String getFamilyMember() {
		try {
			return new JsonUtil().objectsToJson(familyMemberList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void refreshData() {
		if(familyMermberButton == null){
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String familyMember = archiveInfo.getFamilyMermber();
//			Log.i("cjl", familyMember);
			if (TextUtils.isEmpty(familyMember) || "[]".equals(familyMember)) {
				familyMemberList.removeAll(familyMemberList);
				familyMemberAdapter.notifyDataSetChanged();
			} else {
				setFamilyMember(familyMember);
				
			}
		}
		
	}
	
	private void setFamilyMember(String familyMember) {
		List<FamilyMember> mfamilyMemberList = new ArrayList<FamilyMember>();
		try {
			mfamilyMemberList = new JsonUtil().jsonToObjects(familyMember, FamilyMember.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num = mfamilyMemberList.size();
		familyMemberList.removeAll(familyMemberList);
			for(int i = 0;i < num;i++){
				familyMemberList.add(mfamilyMemberList.get(i));
			}
			familyMemberAdapter.notifyDataSetChanged();
	}


	@Override
	public void onClick(View item, View widget, final int position, int which) {
		// TODO Auto-generated method stub
		final FamilyMember myFamilyMember;
		myFamilyMember = familyMemberList.get(position);
		switch (which) {
		case R.id.edit_btn:	
			final FamilyMemberDialog familyMemberDialog = new FamilyMemberDialog(true,
					myFamilyMember.getName(),myFamilyMember.getGender(),
					myFamilyMember.getBirthday(),myFamilyMember.getRelationship(),myFamilyMember.getEducation(),
					myFamilyMember.getJob(),myFamilyMember.getIdcard(),myFamilyMember.getMarriage(),
					myFamilyMember.getPersonalStatus(),myFamilyMember.getRemark());
			familyMemberDialog.show(getChildFragmentManager(), "familyMemberDialog");

			familyMemberDialog.setOnFamilyMemberDialog(new OnFamilyMemberDialog() {
				@Override
				public void onConfirmClick(FamilyMember familyMember) {
					if(familyMember != null){
						if(!familyMember.toString().equals(myFamilyMember.toString())){
							familyMemberList.set(position, familyMember);
							familyMemberAdapter.notifyDataSetChanged();
							familyMemberDialog.hide();
						}						
					}		
					
				}
			});	
			break;
		case R.id.delete_btn:
			familyMemberList.remove(position);
			familyMemberAdapter.notifyDataSetChanged();
			break;
		case R.id.detail_btn:
			final FamilyMemberDialog familyMemberDialog2 = new FamilyMemberDialog(false,
					myFamilyMember.getName(),myFamilyMember.getGender(),
					myFamilyMember.getBirthday(),myFamilyMember.getRelationship(),myFamilyMember.getEducation(),
					myFamilyMember.getJob(),myFamilyMember.getIdcard(),myFamilyMember.getMarriage(),
					myFamilyMember.getPersonalStatus(),myFamilyMember.getRemark());
			familyMemberDialog2.show(getChildFragmentManager(), "familyMemberDialog");

			familyMemberDialog2.setOnFamilyMemberDialog(new OnFamilyMemberDialog() {
				@Override
				public void onConfirmClick(FamilyMember familyMember) {
					familyMemberDialog2.hide();
				}
			});	
			break;
		default:
			break;
		}
	}
}
