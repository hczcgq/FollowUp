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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.StringCounterUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.interfaces.PastHistoryListItemClickHelp;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.FamliyHistories;
import com.shbestwin.followupmanager.model.archive.PastHistoryDisease;
import com.shbestwin.followupmanager.model.archive.PastHistoryInjury;
import com.shbestwin.followupmanager.model.archive.PastHistoryOperation;
import com.shbestwin.followupmanager.model.archive.PastHistoryTransfusion;
import com.shbestwin.followupmanager.view.adapter.archive.PastHistoryDiseaseListAdapter;
import com.shbestwin.followupmanager.view.adapter.archive.PastHistoryInjuryListAdapter;
import com.shbestwin.followupmanager.view.adapter.archive.PastHistoryOperationListAdapter;
import com.shbestwin.followupmanager.view.adapter.archive.PastHistoryTransfusionListAdapter;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryDiseaseDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryDiseaseDialog.OnPastHistoryDiseaseDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryInjuryDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryInjuryDialog.OnPastHistoryInjuryDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryOperationDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryOperationDialog.OnPastHistoryOperationDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryTransfusionDialog;
import com.shbestwin.followupmanager.view.dialog.archive.PastHistoryTransfusionDialog.OnPastHistoryTransfusionDialog;

/**
 * 
 * 既往史
 *
 * @version
 */
public class PastHistoryFragment extends BaseArchiveFragment implements PastHistoryListItemClickHelp{
	private ListView diseaseListView, operationListView, injuryListView, transfusionListView;
	
	private PastHistoryDiseaseListAdapter diseaseAdapter;
	private PastHistoryTransfusionListAdapter transfusionAdapter;
	private PastHistoryInjuryListAdapter injuryAdapter;
	private PastHistoryOperationListAdapter operationAdapter;
	
	private View diseaseButton, operationButton, injuryButton, transfusionButton;
	private PastHistoryDisease mPastHistoryDisease;
	private PastHistoryOperation mPastHistoryOperation;
	private PastHistoryInjury mPastHistoryInjury;
	private PastHistoryTransfusion mPastHistoryTransfusion;
	private List<PastHistoryDisease> diseaseList = new ArrayList<PastHistoryDisease>();
	private List<PastHistoryOperation> operationList = new ArrayList<PastHistoryOperation>();
	private List<PastHistoryInjury> injuryList = new ArrayList<PastHistoryInjury>();
	private List<PastHistoryTransfusion> transfusionList = new ArrayList<PastHistoryTransfusion>();

	
	public static PastHistoryFragment newInstance() {
		PastHistoryFragment fragment = new PastHistoryFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_past_history, container, false);
		diseaseListView = (ListView) rootView.findViewById(R.id.diseaseListView);
		operationListView = (ListView) rootView.findViewById(R.id.operationListView);
		injuryListView = (ListView) rootView.findViewById(R.id.injuryListView);
		transfusionListView = (ListView) rootView.findViewById(R.id.transfusionListView);
		diseaseButton = rootView.findViewById(R.id.diseaseButton);
		operationButton = rootView.findViewById(R.id.operationButton);
		injuryButton = rootView.findViewById(R.id.injuryButton);
		transfusionButton = rootView.findViewById(R.id.transfusionButton);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		diseaseAdapter = new PastHistoryDiseaseListAdapter(getActivity(), diseaseList,this);
		diseaseListView.setAdapter(diseaseAdapter);

		operationAdapter = new PastHistoryOperationListAdapter(getActivity(), operationList,this);
		operationListView.setAdapter(operationAdapter);

		injuryAdapter = new PastHistoryInjuryListAdapter(getActivity(), injuryList,this);
		injuryListView.setAdapter(injuryAdapter);

		transfusionAdapter = new PastHistoryTransfusionListAdapter(getActivity(), transfusionList,this);
		transfusionListView.setAdapter(transfusionAdapter);

		diseaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDiseaseDialog();
			}
		});
		operationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showOperationDialog();
			}
		});
		injuryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showInjuryDialog();
			}
		});
		transfusionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTransfusionDialog();
			}
		});
		
		refreshData();
	}

	private void showOperationDialog() {
		final PastHistoryOperationDialog pastHistoryOperationDialog = PastHistoryOperationDialog.newInstance();
		pastHistoryOperationDialog.show(getChildFragmentManager(), "pastHistoryOperationDialog");
		pastHistoryOperationDialog.setOnPastHistoryOperationDialog(new OnPastHistoryOperationDialog() {
			
			@Override
			public void onConfirmClick(PastHistoryOperation pastHistoryOperation) {
				// TODO Auto-generated method stub
				if(pastHistoryOperation != null){
					//familyProblemListView
					mPastHistoryOperation = pastHistoryOperation;
					operationList.add(mPastHistoryOperation);
					operationAdapter.notifyDataSetChanged();
					pastHistoryOperationDialog.hide();
				}		
				
			}
		});
		
	}
	
	private void showInjuryDialog() {
		final PastHistoryInjuryDialog pastHistoryInjuryDialog = PastHistoryInjuryDialog.newInstance();
		pastHistoryInjuryDialog.show(getChildFragmentManager(), "pastHistoryInjuryDialog");
		pastHistoryInjuryDialog.setOnPastHistoryInjuryDialog(new OnPastHistoryInjuryDialog() {
			
			@Override
			public void onConfirmClick(PastHistoryInjury pastHistoryInjury) {
				// TODO Auto-generated method stub
				if(pastHistoryInjury != null){
					//familyProblemListView
					mPastHistoryInjury = pastHistoryInjury;
					injuryList.add(mPastHistoryInjury);
					injuryAdapter.notifyDataSetChanged();
					pastHistoryInjuryDialog.hide();
				}
				
			}
		});
	}
	
	private void showTransfusionDialog() {
		final PastHistoryTransfusionDialog pastHistoryTransfusionDialog = PastHistoryTransfusionDialog.newInstance();
		pastHistoryTransfusionDialog.show(getChildFragmentManager(), "pastHistoryTransfusionDialog");
		pastHistoryTransfusionDialog.setOnPastHistoryTransfusionDialog(new OnPastHistoryTransfusionDialog() {
			
			@Override
			public void onConfirmClick(PastHistoryTransfusion pastHistoryTransfusion) {
				// TODO Auto-generated method stub
				if(pastHistoryTransfusion != null){
					//familyProblemListView
					mPastHistoryTransfusion = pastHistoryTransfusion;
					transfusionList.add(mPastHistoryTransfusion);
					transfusionAdapter.notifyDataSetChanged();
					pastHistoryTransfusionDialog.hide();
				}		
				
			}
		});
	}
	
	private void showDiseaseDialog() {
		final PastHistoryDiseaseDialog pastHistoryDiseaseDialog = PastHistoryDiseaseDialog.newInstance();
		pastHistoryDiseaseDialog.show(getChildFragmentManager(), "pastHistoryDiseaseDialog");

		pastHistoryDiseaseDialog.setOnPastHistoryDiseaseDialog(new OnPastHistoryDiseaseDialog() {
			@Override
			public void onConfirmClick(PastHistoryDisease pastHistoryDisease) {
				if(pastHistoryDisease != null){
					//familyProblemListView
					mPastHistoryDisease = pastHistoryDisease;
					diseaseList.add(mPastHistoryDisease);
					diseaseAdapter.notifyDataSetChanged();
					pastHistoryDiseaseDialog.hide();
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
			String pastHistories = "";
			pastHistories = getPastHistories();
			archiveInfo.setPastHistories(pastHistories);
		}
		return archiveInfo;
	
	}
	
	@Override
	public void refreshData() {
		if (diseaseListView == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String pastHistories = archiveInfo.getPastHistories();
			Log.i("cjl", "既往史---"+pastHistories);
			if (TextUtils.isEmpty(pastHistories)) {
				
			} else {
				setPastHistories(pastHistories);		
			}
		}
	}
	
	private String getPastHistories() {	
		JsonObject jsonObject = new JsonObject();
		try {
			jsonObject.add("disease", new JsonParser().parse(JsonUtil.objectsToJson(diseaseList)).getAsJsonArray());
			jsonObject.add("operation", new JsonParser().parse(JsonUtil.objectsToJson(operationList)).getAsJsonArray());
			jsonObject.add("injury", new JsonParser().parse(JsonUtil.objectsToJson(injuryList)).getAsJsonArray());
			jsonObject.add("transfusion", new JsonParser().parse(JsonUtil.objectsToJson(transfusionList)).getAsJsonArray());
			return jsonObject.toString();
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "";
	}
	private void setPastHistories(String pastHistories) {
		// TODO Auto-generated method stub
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(pastHistories);
		JsonObject jsonObject = jsonEl.getAsJsonObject();
		JsonArray diseaseJsonArray = jsonObject.getAsJsonArray("disease");
		JsonArray operationJsonArray = jsonObject.getAsJsonArray("operation");
		JsonArray injuryJsonArray = jsonObject.getAsJsonArray("injury");
		JsonArray transfusionJsonArray = jsonObject.getAsJsonArray("transfusion");
		if(!(TextUtils.isEmpty(diseaseJsonArray.toString())  || "[]".equals(diseaseJsonArray.toString()))){
			List<PastHistoryDisease> mdiseaseList;
			try {
				mdiseaseList = JsonUtil.jsonToObjects(diseaseJsonArray.toString(), PastHistoryDisease.class);
				int num = mdiseaseList.size();
				diseaseList.removeAll(diseaseList);
				for(int i = 0;i < num;i++){
					diseaseList.add(mdiseaseList.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			diseaseAdapter.notifyDataSetChanged();	
		}
		if(!(TextUtils.isEmpty(operationJsonArray.toString())  || "[]".equals(operationJsonArray.toString()))){
			List<PastHistoryOperation> moperationList;
			try {
				moperationList = JsonUtil.jsonToObjects(operationJsonArray.toString(), PastHistoryOperation.class);
				int num = moperationList.size();
				operationList.removeAll(operationList);
				for(int i = 0;i < num;i++){
					operationList.add(moperationList.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			operationAdapter.notifyDataSetChanged();	
		}
		if(!(TextUtils.isEmpty(injuryJsonArray.toString())  || "[]".equals(injuryJsonArray.toString()))){
			List<PastHistoryInjury> minjuryList;
			try {
				minjuryList = JsonUtil.jsonToObjects(injuryJsonArray.toString(), PastHistoryInjury.class);
				int num = minjuryList.size();
				injuryList.removeAll(injuryList);
				for(int i = 0;i < num;i++){
					injuryList.add(minjuryList.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			injuryAdapter.notifyDataSetChanged();	
		}
		if(!(TextUtils.isEmpty(transfusionJsonArray.toString())  || "[]".equals(transfusionJsonArray.toString()))){
			List<PastHistoryTransfusion> mtransfusionList;
			try {
				mtransfusionList = JsonUtil.jsonToObjects(transfusionJsonArray.toString(), PastHistoryTransfusion.class);
				int num = mtransfusionList.size();
				transfusionList.removeAll(transfusionList);
				for(int i = 0;i < num;i++){
					transfusionList.add(mtransfusionList.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			transfusionAdapter.notifyDataSetChanged();	
		}
	}

	@Override
	public void onClickDisease(View item, View widget, final int position, int which) {
		// TODO Auto-generated method stub
		final PastHistoryDisease myPastHistoryDisease;
		myPastHistoryDisease = diseaseList.get(position);
		switch (which) {
		case R.id.update:	
			final PastHistoryDiseaseDialog pastHistoryDiseaseDialog = new PastHistoryDiseaseDialog(
					myPastHistoryDisease.getType(), myPastHistoryDisease.getTreatResult(),
					myPastHistoryDisease.getName(), myPastHistoryDisease.getDate(), myPastHistoryDisease.getOnsetDate());
			pastHistoryDiseaseDialog.show(getChildFragmentManager(), "pastHistoryDiseaseDialog");

			pastHistoryDiseaseDialog.setOnPastHistoryDiseaseDialog(new OnPastHistoryDiseaseDialog() {
				@Override
				public void onConfirmClick(PastHistoryDisease pastHistoryDisease) {
					if(pastHistoryDisease != null){
						//familyProblemListView
						if(!pastHistoryDisease.toString().equals(myPastHistoryDisease.toString())){
							diseaseList.set(position, pastHistoryDisease);
							diseaseAdapter.notifyDataSetChanged();
						}						
					}		
					pastHistoryDiseaseDialog.hide();
				}
			});	
			break;
		case R.id.delete:
			diseaseList.remove(position);
			diseaseAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	@Override
	public void onClickOperation(View item, View widget, final int position, int which) {
		// TODO Auto-generated method stub
		final PastHistoryOperation myPastHistoryOperation;
		myPastHistoryOperation = operationList.get(position);
		switch (which) {
		case R.id.update:	
			final PastHistoryOperationDialog pastHistoryOperationDialog = new PastHistoryOperationDialog(
					myPastHistoryOperation.getType(), myPastHistoryOperation.getTreatResult(),
					myPastHistoryOperation.getName(), myPastHistoryOperation.getDate(), myPastHistoryOperation.getOnsetDate(),myPastHistoryOperation.getReason());
			pastHistoryOperationDialog.show(getChildFragmentManager(), "pastHistoryOperationDialog");
			pastHistoryOperationDialog.setOnPastHistoryOperationDialog(new OnPastHistoryOperationDialog() {
				
				@Override
				public void onConfirmClick(PastHistoryOperation pastHistoryOperation) {
					// TODO Auto-generated method stub
					if(pastHistoryOperation != null){		
						if(!pastHistoryOperation.toString().equals(myPastHistoryOperation.toString())){
							operationList.set(position, pastHistoryOperation);
							operationAdapter.notifyDataSetChanged();
						}						
					}		
					pastHistoryOperationDialog.hide();
				
					
				}
			});
			break;
		case R.id.delete:
			operationList.remove(position);
			operationAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	@Override
	public void onClickInjury(View item, View widget, final int position, int which) {
		// TODO Auto-generated method stub
		final PastHistoryInjury myPastHistoryInjury;
		myPastHistoryInjury = injuryList.get(position);
		switch (which) {
		case R.id.update:	
			final PastHistoryInjuryDialog pastHistoryInjuryDialog = new PastHistoryInjuryDialog(
					myPastHistoryInjury.getType(), myPastHistoryInjury.getTreatResult(),
					myPastHistoryInjury.getName(), myPastHistoryInjury.getDate(), myPastHistoryInjury.getOnsetDate());
			pastHistoryInjuryDialog.show(getChildFragmentManager(), "pastHistoryInjuryDialog");

			pastHistoryInjuryDialog.setOnPastHistoryInjuryDialog(new OnPastHistoryInjuryDialog() {
				@Override
				public void onConfirmClick(PastHistoryInjury pastHistoryInjury) {
					if(pastHistoryInjury != null){				
						if(!pastHistoryInjury.toString().equals(myPastHistoryInjury.toString())){
							injuryList.set(position, pastHistoryInjury);
							injuryAdapter.notifyDataSetChanged();
						}						
					}		
					pastHistoryInjuryDialog.hide();
				}
			});	
			break;
		case R.id.delete:
			injuryList.remove(position);
			injuryAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	@Override
	public void onClickTransfusion(View item, View widget, final int position,
			int which) {
		// TODO Auto-generated method stub
		final PastHistoryTransfusion myPastHistoryTransfusion;
		myPastHistoryTransfusion = transfusionList.get(position);
		switch (which) {
		case R.id.update:	
			final PastHistoryTransfusionDialog pastHistoryTransfusionDialog = new PastHistoryTransfusionDialog(
					myPastHistoryTransfusion.getType(), myPastHistoryTransfusion.getTreatResult(),
					myPastHistoryTransfusion.getName(), myPastHistoryTransfusion.getDate(), myPastHistoryTransfusion.getOnsetDate(),myPastHistoryTransfusion.getReason());
			pastHistoryTransfusionDialog.show(getChildFragmentManager(), "pastHistoryTransfusionDialog");
			pastHistoryTransfusionDialog.setOnPastHistoryTransfusionDialog(new OnPastHistoryTransfusionDialog() {
				
				@Override
				public void onConfirmClick(PastHistoryTransfusion pastHistoryTransfusion) {
					// TODO Auto-generated method stub
					if(pastHistoryTransfusion != null){
						if(!pastHistoryTransfusion.toString().equals(myPastHistoryTransfusion.toString())){
							transfusionList.set(position, pastHistoryTransfusion);
							transfusionAdapter.notifyDataSetChanged();
						}						
					}		
					pastHistoryTransfusionDialog.hide();		
				}
			});
			break;
		case R.id.delete:
			transfusionList.remove(position);
			transfusionAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
}
