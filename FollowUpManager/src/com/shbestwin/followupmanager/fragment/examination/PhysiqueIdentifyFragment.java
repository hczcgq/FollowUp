package com.shbestwin.followupmanager.fragment.examination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.examination.PhysiqueType;
import com.shbestwin.followupmanager.model.examination.Question;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout.OnEndClickListener;

/**
 * 
 * 体质辨识
 * 
 * @version
 */
public class PhysiqueIdentifyFragment extends BaseFragment {
	private ViewFlipper viewFlipper;

	private Button startExaminationButton, lastExaminationButton;

	private Button physiqueReportButton, returnButton;

	private ExaminationLayout examinationLayout;

	private BarChart physiqueBarChart;

	private List<PhysiqueType> physiqueTypeList = null;

	private TextView physiqueNameTextView, physiqueFeatureTextView,
			physiqueGuidanceTextView;

	private Button againExaminationButton;

	private List<Integer> mAnswers = new ArrayList<Integer>();

	public static PhysiqueIdentifyFragment newInstance() {
		PhysiqueIdentifyFragment fragment = new PhysiqueIdentifyFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_examination_physique_identify, container,
				false);
		viewFlipper = (ViewFlipper) rootView;
		startExaminationButton = (Button) rootView
				.findViewById(R.id.startExaminationButton);
		lastExaminationButton = (Button) rootView
				.findViewById(R.id.lastExaminationButton);

		physiqueReportButton = (Button) rootView
				.findViewById(R.id.physiqueReportButton);
		returnButton = (Button) rootView.findViewById(R.id.returnButton);
		examinationLayout = (ExaminationLayout) rootView
				.findViewById(R.id.examinationLayout);

		physiqueBarChart = (BarChart) rootView
				.findViewById(R.id.physiqueBarChart);

		physiqueNameTextView = (TextView) rootView
				.findViewById(R.id.physiqueNameTextView);
		physiqueFeatureTextView = (TextView) rootView
				.findViewById(R.id.physiqueFeatureTextView);
		physiqueGuidanceTextView = (TextView) rootView
				.findViewById(R.id.physiqueGuidanceTextView);
		againExaminationButton = (Button) rootView
				.findViewById(R.id.againExaminationButton);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		physiqueTypeList = new ArrayList<PhysiqueType>();
		physiqueTypeList.add(new PhysiqueType("A", "平和质"));
		physiqueTypeList.add(new PhysiqueType("B", "气虚质"));
		physiqueTypeList.add(new PhysiqueType("C", "阳虚质"));

		physiqueTypeList.add(new PhysiqueType("D", "阴虚质"));
		physiqueTypeList.add(new PhysiqueType("E", "痰湿型"));
		physiqueTypeList.add(new PhysiqueType("F", "湿热型"));

		physiqueTypeList.add(new PhysiqueType("G", "血瘀型"));
		physiqueTypeList.add(new PhysiqueType("H", "气郁型"));
		physiqueTypeList.add(new PhysiqueType("I", "特禀型"));

		startExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (viewFlipper.getDisplayedChild() == 0) {
					nextAnim();
					viewFlipper.showNext();
					renderQuestion();
				}
			}
		});

		lastExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Integer> answers = new ArrayList<Integer>();
				ExaminationInfo examinationInfo = MyApplication.getInstance()
						.getExaminationInfo();
				if (examinationInfo != null) {
					String msg = examinationInfo.getPhysiqueIdentify();
					if (TextUtils.isEmpty(msg)) {
						return;
					}
					try {
						JSONObject jsonObject = new JSONObject(msg);
						JSONArray array = jsonObject
								.getJSONArray("PhysiqueIdentify");
						for (int i = 0; i < array.length(); i++) {
							answers.add(array.optInt(i));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					if (answers != null && answers.size() > 0) {
//						System.out.println(Arrays.asList(answers.toArray()));
//						generateResult(answers);
//						nextAnim();
//						viewFlipper.showNext();
//						viewFlipper.showNext();
//						generateChart();
					}
				}

			}
		});

		examinationLayout.setOnEndClickListener(new OnEndClickListener() {
			@Override
			public void onEndClick(List<Integer> answers) {
				if (mAnswers != null && mAnswers.size() > 0) {
					mAnswers.clear();
				}
				mAnswers.addAll(answers);
				// 看是否所有题目都做完了，如果是，显示测试结果，否则直接结束
				if (examinationLayout.isFinished()) {// 显示测试结果

					generateResult(answers);

					nextAnim();
					viewFlipper.showNext();
					generateChart();
				} else {// 直接结束，到初始页面
					if (viewFlipper.getDisplayedChild() == 1) {
						preAnim();
						viewFlipper.showPrevious();
					}
				}
			}
		});

		physiqueReportButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				viewFlipper.setDisplayedChild(3);
				// 显示结论报告
				showReport();
			}

		});

		returnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				preAnim();
				viewFlipper.setDisplayedChild(0);
			}
		});

		againExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				physiqueTypeList = null;
				viewFlipper.setDisplayedChild(0);
			}
		});
	}

	private void nextAnim() {
		// viewFlipper.setInAnimation(getActivity(), R.anim.flip_right_in);
		// viewFlipper.setOutAnimation(getActivity(), R.anim.flip_right_out);
	}

	private void preAnim() {
		// viewFlipper.setInAnimation(getActivity(), R.anim.flip_left_in);
		// viewFlipper.setOutAnimation(getActivity(), R.anim.flip_left_out);
	}

	private void generateResult(List<Integer> answers) {
//		List<Question> questions = examinationLayout.getQuestions();
		 List<Question> questions = ExaminationManager
		 .getInstance(getActivity()).getPhysiqueQuestions();
		for (PhysiqueType physiqueType : physiqueTypeList) {
			int score = 0;// 得分
			int questionCount = 0;// 题目数
			int result = 0;// 结果：1-是， 2-基本是， 3-否

			for (int i = 0; i < questions.size(); i++) {
				Question question = questions.get(i);
				if (question.getType().contains(physiqueType.getTypeCode())) {
					questionCount++;
					score += (answers.get(i) + 1);
				}
			}

			if (physiqueType.getTypeCode() != "A") {
				if (score >= 40) {
					result = 1;
				} else if (score < 30) {
					result = 3;
				} else {
					result = 2;
				}
			}
			physiqueType.setScore(score);
			physiqueType.setQuestionCount(questionCount);
			physiqueType.setResult(result);
		}

		// 平和质 1-是， 2-基本是， 3-否
		PhysiqueType physiqueType = physiqueTypeList.get(0);
		if (physiqueType.getScore() >= 60) {
			boolean isLT30 = true;
			boolean isLT40 = true;
			for (int i = 1; i < physiqueTypeList.size(); i++) {
				PhysiqueType item = physiqueTypeList.get(i);
				if (item.getScore() >= 30) {
					isLT30 = false;
				}
				if (item.getScore() >= 40) {
					isLT40 = false;
				}
			}
			if (isLT30) {
				physiqueType.setResult(1);
			} else {
				if (isLT40) {
					physiqueType.setResult(2);
				} else {
					physiqueType.setResult(3);
				}
			}
		} else {
			physiqueType.setResult(3);// 否
		}
	}

	private void generateChart() {
		physiqueBarChart.setDrawYValues(true);
		physiqueBarChart.set3DEnabled(true);
		// scaling can now only be done on x- and y-axis separately
		physiqueBarChart.setPinchZoom(false);
		physiqueBarChart.setTouchEnabled(false);

		physiqueBarChart.setDrawBarShadow(false);

		physiqueBarChart.setDrawVerticalGrid(false);
		physiqueBarChart.setDrawHorizontalGrid(false);
		physiqueBarChart.setDrawGridBackground(false);
		physiqueBarChart.setDescription("");

		XLabels xLabels = physiqueBarChart.getXLabels();
		xLabels.setPosition(XLabelPosition.BOTTOM);
		xLabels.setCenterXLabelText(true);
		xLabels.setSpaceBetweenLabels(0);

		setData();

		physiqueBarChart.setDrawYLabels(false);
		physiqueBarChart.setDrawLegend(false);
		physiqueBarChart.animateY(2500);
	}

	public static final int[] VORDIPLOM_COLORS = { Color.parseColor("#FF6666"),
			Color.parseColor("#FF9966"), Color.parseColor("#FFCC00"),
			Color.parseColor("#CCCC33"), Color.parseColor("#009966"),
			Color.parseColor("#009999"), Color.parseColor("#3399CC"),
			Color.parseColor("#6666CC"), Color.parseColor("#663366") };

	private void setData() {
		ArrayList<String> xVals = new ArrayList<String>();
		ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

		int index = 0;
		for (PhysiqueType physiqueType : physiqueTypeList) {
			xVals.add(physiqueType.getTypeName());
			yVals.add(new BarEntry(physiqueType.getScore(), index++));
		}

		BarDataSet set1 = new BarDataSet(yVals, "体质分类得分");
		set1.setBarSpacePercent(35f);

		set1.setColors(VORDIPLOM_COLORS);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);
		physiqueBarChart.setData(data);
	}

	private void renderQuestion() {
		String[] options = getResources().getStringArray(
				R.array.PhysiqueOptions);
		List<Question> questions = ExaminationManager
				.getInstance(getActivity()).getPhysiqueQuestions();
		examinationLayout.renderView(null,
				getString(R.string.jktj_test_question_subtitle), questions,
				options);
	}

	private String getCouclusion() {
		if (physiqueTypeList != null && physiqueTypeList.size() == 9) {
			int index = 0;
			int score = physiqueTypeList.get(0).getScore();
			for (int i = 1; i < physiqueTypeList.size(); i++) {
				if (physiqueTypeList.get(i).getScore() > score) {
					index = i;
				}
			}

			String result = "";
			switch (index) {
			case 0:
				result = "A";
				break;
			case 1:
				result = "B";
				break;
			case 2:
				result = "C";
				break;
			case 3:
				result = "D";
				break;
			case 4:
				result = "E";
				break;
			case 5:
				result = "F";
				break;
			case 6:
				result = "G";
				break;
			case 7:
				result = "H";
				break;
			case 8:
				result = "I";
				break;
			}

			return result;
		}
		return "";
	}

	private void showReport() {
		String result = getCouclusion();
		String physiqueName = "";
		String physiqueFeature = "";
		String physiqueGuidance = "";
		if ("A".equals(result)) {
			physiqueName = "平和体质";
			physiqueFeature = getString(R.string.jktj_tzsb_A_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_A_guidance);
		} else if ("B".equals(result)) {
			physiqueName = "气虚体质";
			physiqueFeature = getString(R.string.jktj_tzsb_B_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_B_guidance);
		} else if ("C".equals(result)) {
			physiqueName = "阳虚体质";
			physiqueFeature = getString(R.string.jktj_tzsb_C_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_C_guidance);
		} else if ("D".equals(result)) {
			physiqueName = "阴虚体质";
			physiqueFeature = getString(R.string.jktj_tzsb_D_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_D_guidance);
		} else if ("E".equals(result)) {
			physiqueName = "痰湿体质";
			physiqueFeature = getString(R.string.jktj_tzsb_E_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_E_guidance);
		} else if ("F".equals(result)) {
			physiqueName = "湿热体质";
			physiqueFeature = getString(R.string.jktj_tzsb_F_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_F_guidance);
		} else if ("G".equals(result)) {
			physiqueName = "血瘀体质";
			physiqueFeature = getString(R.string.jktj_tzsb_G_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_G_guidance);
		} else if ("H".equals(result)) {
			physiqueName = "气郁体质";
			physiqueFeature = getString(R.string.jktj_tzsb_H_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_H_guidance);
		} else if ("I".equals(result)) {
			physiqueName = "特禀体质";
			physiqueFeature = getString(R.string.jktj_tzsb_I_feature);
			physiqueGuidance = getString(R.string.jktj_tzsb_I_guidance);
		}
		physiqueNameTextView.setText(physiqueName);
		physiqueFeatureTextView.setText(physiqueFeature);
		physiqueGuidanceTextView.setText(physiqueGuidance);
	}

	@Override
	public void onSave() {
		super.onSave();
		ExaminationInfo examinationInfo = MyApplication.getInstance()
				.getExaminationInfo();
		if (examinationInfo == null) {
			ToastUtils.showToast(getActivity(), "请先进行体检登记!");
			return;
		}
		if (mAnswers != null && mAnswers.size() > 0) {
			try {
				JSONObject jsonObject = new JSONObject();
				JSONArray array = new JSONArray();
				for (int i = 0; i < mAnswers.size(); i++) {
					array.put(mAnswers.get(i));
				}
				jsonObject.put("PhysiqueIdentify", array);
				examinationInfo.setPhysiqueIdentify(jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ExaminationManager.getInstance(getActivity())
				.saveOrUpdateExaminationInfo(examinationInfo);
		ToastUtils.showToast(getActivity(), "保存数据成功！");
		MyApplication.getInstance().setExaminationInfo(examinationInfo);
	}
}
