package com.shbestwin.followupmanager.fragment.archive;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.JsonUtil;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.ArchiveInfo;
import com.shbestwin.followupmanager.model.archive.Environment;

/**
 * 
 * 生活环境
 * 
 * @version
 */
public class EnvironmentFragment extends BaseArchiveFragment {

	private Spinner exhaustingSystem, smokeExtraction, kitchenUsage, fuelType,
			waterSource, avianCorral, healthyToiletType, notHealthyToiletType,
			rubbishHandler, sanitaryConditions, sewageDisposal, houseType,
			houseProperty, houseLighting, houseVentilation, houseWarm,
			airHumidity, archiveStatus;
	private EditText houseNum, appliances, transportation, vehicle, sumArea,
			averageArea;

	public static EnvironmentFragment newInstance() {
		EnvironmentFragment fragment = new EnvironmentFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_archive_environment,
				container, false);

		exhaustingSystem = (Spinner) rootView
				.findViewById(R.id.exhaustingSystem);
		smokeExtraction = (Spinner) rootView.findViewById(R.id.smokeExtraction);
		kitchenUsage = (Spinner) rootView.findViewById(R.id.kitchenUsage);

		fuelType = (Spinner) rootView.findViewById(R.id.fuelType);
		waterSource = (Spinner) rootView.findViewById(R.id.waterSource);
		avianCorral = (Spinner) rootView.findViewById(R.id.avianCorral);

		healthyToiletType = (Spinner) rootView
				.findViewById(R.id.healthyToiletType);
		notHealthyToiletType = (Spinner) rootView
				.findViewById(R.id.notHealthyToiletType);
		rubbishHandler = (Spinner) rootView.findViewById(R.id.rubbishHandler);

		sanitaryConditions = (Spinner) rootView
				.findViewById(R.id.sanitaryConditions);
		sewageDisposal = (Spinner) rootView.findViewById(R.id.sewageDisposal);
		houseType = (Spinner) rootView.findViewById(R.id.houseType);

		houseProperty = (Spinner) rootView.findViewById(R.id.houseProperty);
		houseNum = (EditText) rootView.findViewById(R.id.houseNum);
		houseLighting = (Spinner) rootView.findViewById(R.id.houseLighting);

		houseVentilation = (Spinner) rootView
				.findViewById(R.id.houseVentilation);
		houseWarm = (Spinner) rootView.findViewById(R.id.houseWarm);
		airHumidity = (Spinner) rootView.findViewById(R.id.airHumidity);

		appliances = (EditText) rootView.findViewById(R.id.appliances);
		transportation = (EditText) rootView.findViewById(R.id.transportation);
		vehicle = (EditText) rootView.findViewById(R.id.vehicle);

		sumArea = (EditText) rootView.findViewById(R.id.sumArea);
		averageArea = (EditText) rootView.findViewById(R.id.averageArea);
		archiveStatus = (Spinner) rootView.findViewById(R.id.archiveStatus);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		refreshData();
	}

	@Override
	public boolean validate() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo == null) {
			ToastUtils.showToast(getActivity(), "请先填写个人信息！");
			return false;
		}
		if (TextUtils.isEmpty(houseNum.getText())) {
			ToastUtils.showToast(getActivity(), "请填写房间数！");
			return false;
		}
		if (TextUtils.isEmpty(appliances.getText())) {
			ToastUtils.showToast(getActivity(), "请填写家用电器！");
			return false;
		}
		if (TextUtils.isEmpty(transportation.getText())) {
			ToastUtils.showToast(getActivity(), "请填写交通工具！");
			return false;
		}
		if (TextUtils.isEmpty(vehicle.getText())) {
			ToastUtils.showToast(getActivity(), "请填写文体设备！");
			return false;
		}
		if (TextUtils.isEmpty(sumArea.getText())) {
			ToastUtils.showToast(getActivity(), "请填写总面积！");
			return false;
		}
		if (TextUtils.isEmpty(averageArea.getText())) {
			ToastUtils.showToast(getActivity(), "请填写人均面积！");
			return false;
		}
		return true;
	}

	@Override
	public ArchiveInfo getArchiveInfo() {
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String environment = "";
			environment = getEnvironment();
			archiveInfo.setEnvironment(environment);
		}
		return archiveInfo;
	}

	@Override
	public void refreshData() {

		if (exhaustingSystem == null) {
			return;
		}
		ArchiveInfo archiveInfo = MyApplication.getInstance().getArchiveInfo();
		if (archiveInfo != null) {
			String enviroment = archiveInfo.getEnvironment();
			if (TextUtils.isEmpty(enviroment) || "[]".equals(enviroment)) {
				exhaustingSystem.setSelection(0);
				smokeExtraction.setSelection(0);
				kitchenUsage.setSelection(0);
				fuelType.setSelection(0);
				waterSource.setSelection(0);
				avianCorral.setSelection(0);
				healthyToiletType.setSelection(0);
				notHealthyToiletType.setSelection(0);
				rubbishHandler.setSelection(0);
				sanitaryConditions.setSelection(0);
				sewageDisposal.setSelection(0);
				houseType.setSelection(0);
				houseProperty.setSelection(0);
				houseLighting.setSelection(0);
				houseVentilation.setSelection(0);
				houseWarm.setSelection(0);
				airHumidity.setSelection(0);
				archiveStatus.setSelection(0);
				houseNum.setText("");
				appliances.setText("");
				transportation.setText("");
				vehicle.setText("");
				sumArea.setText("");
				averageArea.setText("");
			} else {
				setEnvironment(enviroment);
			}
		}

	}

	private String getEnvironment() {
		Environment environment = new Environment();
		environment.setExhaustingSystem(exhaustingSystem.getSelectedItem()
				.toString());
		environment.setSmokeExtraction(smokeExtraction.getSelectedItem()
				.toString());
		environment.setKitchenUsage(kitchenUsage.getSelectedItem().toString());

		environment.setFuelType(fuelType.getSelectedItem().toString());
		environment.setAvianCorral(avianCorral.getSelectedItem().toString());
		environment.setWaterSource(waterSource.getSelectedItem().toString());

		environment.setHealthyToiletType(healthyToiletType.getSelectedItem()
				.toString());
		environment.setNotHealthyToiletType(notHealthyToiletType
				.getSelectedItem().toString());
		environment.setRubbishHandler(rubbishHandler.getSelectedItem()
				.toString());

		environment.setSanitaryConditions(sanitaryConditions.getSelectedItem()
				.toString());
		environment.setSewageDisposal(sewageDisposal.getSelectedItem()
				.toString());
		environment.setHouseType(houseType.getSelectedItem().toString());

		environment
				.setHouseProperty(houseProperty.getSelectedItem().toString());
		environment
				.setHouseLighting(houseLighting.getSelectedItem().toString());
		environment.setHouseVentilation(houseVentilation.getSelectedItem()
				.toString());

		environment.setHouseWarm(houseWarm.getSelectedItem().toString());
		environment.setAirHumidity(airHumidity.getSelectedItem().toString());
		environment
				.setArchiveStatus(archiveStatus.getSelectedItem().toString());

		environment.setHouseNum(houseNum.getText().toString());
		environment.setAppliances(appliances.getText().toString());
		environment.setTransportation(transportation.getText().toString());
		environment.setVehicle(vehicle.getText().toString());
		environment.setSumArea(sumArea.getText().toString());
		environment.setAverageArea(averageArea.getText().toString());

		try {
			return new JsonUtil().objectToJson(environment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private void setEnvironment(String enviroment) {
		Environment menvironment = new Environment();
		try {
			menvironment = (Environment) new JsonUtil().jsonToObject(
					enviroment, Environment.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSpinner(exhaustingSystem, menvironment.getExhaustingSystem());
		setSpinner(smokeExtraction, menvironment.getSmokeExtraction());
		setSpinner(kitchenUsage, menvironment.getKitchenUsage());

		setSpinner(fuelType, menvironment.getFuelType());
		setSpinner(waterSource, menvironment.getWaterSource());
		setSpinner(avianCorral, menvironment.getAvianCorral());

		setSpinner(healthyToiletType, menvironment.getHealthyToiletType());
		setSpinner(notHealthyToiletType, menvironment.getNotHealthyToiletType());
		setSpinner(rubbishHandler, menvironment.getRubbishHandler());

		setSpinner(sanitaryConditions, menvironment.getSanitaryConditions());
		setSpinner(sewageDisposal, menvironment.getSewageDisposal());
		setSpinner(houseType, menvironment.getHouseType());

		setSpinner(houseProperty, menvironment.getHouseProperty());
		setSpinner(houseLighting, menvironment.getHouseLighting());
		setSpinner(houseVentilation, menvironment.getHouseVentilation());

		setSpinner(houseWarm, menvironment.getHouseWarm());
		setSpinner(airHumidity, menvironment.getAirHumidity());
		setSpinner(archiveStatus, menvironment.getArchiveStatus());

		houseNum.setText(menvironment.getHouseNum());
		appliances.setText(menvironment.getAppliances());
		transportation.setText(menvironment.getTransportation());
		vehicle.setText(menvironment.getVehicle());
		sumArea.setText(menvironment.getSumArea());
		averageArea.setText(menvironment.getAverageArea());

	}

	private void setSpinner(Spinner spinner, String text) {
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			String item = spinner.getAdapter().getItem(i).toString();
			if (item.equals(text)) {
				spinner.setSelection(i);
				break;
			}
		}
	}
}
