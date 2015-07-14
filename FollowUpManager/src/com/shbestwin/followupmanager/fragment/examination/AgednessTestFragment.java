package com.shbestwin.followupmanager.fragment.examination;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.shbestwin.followupmanager.MyApplication;
import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.examination.ExaminationInfo;
import com.shbestwin.followupmanager.model.examination.Question;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout.OnEndClickListener;

public class AgednessTestFragment extends BaseFragment {
	public static final int TYPE_SELF_CARE = 0x001;
	public static final int TYPE_DEPRESSION = 0x002;
	public static final int TYPE_INTELLIGENCE = 0x003;

	private ViewFlipper viewFlipper;
	private ExaminationLayout examinationLayout;
	private Button startExaminationButton, lastExaminationButton,
			againExaminationButton;
	private TextView titleTextView, contentTextView, tipsTextView;
	private TextView featureTextView;

	private int type;

	public static List<Integer> mAnswers = new ArrayList<Integer>();

	public static AgednessTestFragment newInstance(int type) {
		AgednessTestFragment fragment = new AgednessTestFragment();
		Bundle args = new Bundle();
		args.putInt("type", type);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_examination_agedness_test, container, false);
		viewFlipper = (ViewFlipper) rootView;
		examinationLayout = (ExaminationLayout) rootView
				.findViewById(R.id.examinationLayout);
		startExaminationButton = (Button) rootView
				.findViewById(R.id.startExaminationButton);
		lastExaminationButton = (Button) rootView
				.findViewById(R.id.lastExaminationButton);
		againExaminationButton = (Button) rootView
				.findViewById(R.id.againExaminationButton);
		titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
		contentTextView = (TextView) rootView
				.findViewById(R.id.contentTextView);
		tipsTextView = (TextView) rootView.findViewById(R.id.tipsTextView);
		featureTextView = (TextView) rootView
				.findViewById(R.id.featureTextView);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		type = getArguments().getInt("type");
		if (type == TYPE_SELF_CARE) {
			titleTextView.setText(R.string.jktj_lnpg_self_care_title);
			contentTextView.setText(R.string.jktj_lnpg_self_care_content);
			tipsTextView.setVisibility(View.VISIBLE);
			tipsTextView.setText(R.string.jktj_lnpg_self_care_tips);
		} else if (type == TYPE_DEPRESSION) {
			titleTextView.setText(R.string.jktj_lnpg_depression_title);
			contentTextView.setText(R.string.jktj_lnpg_depression_content);
			tipsTextView.setVisibility(View.GONE);
		} else {
			titleTextView.setText(R.string.jktj_lnpg_intelligence_title);
			contentTextView.setText(R.string.jktj_lnpg_intelligence_content);
			tipsTextView.setVisibility(View.GONE);
		}

		startExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (viewFlipper.getDisplayedChild() == 0) {
					viewFlipper.showNext();
					renderQuestion();
				}
			}
		});

		lastExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		againExaminationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				viewFlipper.setDisplayedChild(0);
			}
		});

		examinationLayout.setOnEndClickListener(new OnEndClickListener() {
			@Override
			public void onEndClick(List<Integer> answers) {
				if (mAnswers != null && mAnswers.size() > 0) {
					mAnswers.clear();
				}
				mAnswers.addAll(answers);
//				System.out.println("mAnswers:"+Arrays.asList(mAnswers.toArray()));
				// 看是否所有题目都做完了，如果是，显示测试结果，否则直接结束
				boolean isFinish = true;
				for (int answer : answers) {
					if (answer < 0) {
						isFinish = false;
						break;
					}
				}
				if (isFinish) {// 显示测试结果
					showConclusion(answers);
				} else {// 直接结束，到初始页面
					if (viewFlipper.getDisplayedChild() == 1) {
						viewFlipper.showPrevious();
					}
				}
			}

		});
	}

	private void renderQuestion() {
		String subTitle = getString(R.string.jktj_test_question_subtitle);
		List<Question> questions = ExaminationManager
				.getInstance(getActivity()).getAgednessQuestions(type);
		String[] options = null;
		if (type == TYPE_SELF_CARE) {
			options = getResources().getStringArray(
					R.array.AgednessSelfCareOptions);
		} else if (type == TYPE_DEPRESSION) {
			subTitle = getString(R.string.jktj_test_question_subtitle1);
			options = getResources().getStringArray(
					R.array.AgednessDepressionOptions);
		} else {
			options = getResources().getStringArray(
					R.array.AgednessIntelligenceOptions);
		}
		examinationLayout.renderView(null, subTitle, questions, options);
	}

	private void showConclusion(List<Integer> answers) {
		viewFlipper.setDisplayedChild(2);

		String feature = "";
		if (type == TYPE_SELF_CARE) {
			feature = getConclusionBySelfCare(answers);
		} else if (type == TYPE_DEPRESSION) {
			feature = getConclusionByDepression(answers);
		} else {
			feature = getConclusionByIntelligence(answers);
		}
		featureTextView.setText(feature);
	}

	// 自理评估
	private String getConclusionBySelfCare(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (int i = 0; i < answers.size(); i++) {
				int answer = answers.get(i);
				switch (i) {
				// 0,0,3,5
				case 0:
				case 2:
					if (answer == 2) {
						score += 3;
					} else if (answer == 3) {
						score += 5;
					}
					break;
				case 1:// 0,1,3,7
					if (answer == 1) {
						score += 1;
					} else if (answer == 2) {
						score += 3;
					} else if (answer == 3) {
						score += 7;
					}
					break;
				case 3:// 0,1,5,10
				case 4:
					if (answer == 1) {
						score += 1;
					} else if (answer == 2) {
						score += 5;
					} else if (answer == 3) {
						score += 10;
					}
					break;
				}
			}

			if (score < 4) {
				result = "可自理";
			} else if (score < 9) {
				result = "轻度依赖";
			} else if (score < 19) {
				result = "中度依赖";
			} else {
				result = "不能自理";
			}
		}
		return result;
	}

	// 抑郁评估
	private String getConclusionByDepression(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (int i = 0; i < answers.size(); i++) {
				int answer = answers.get(i);
				switch (i + 1) {
				case 1:
				case 5:
				case 7:
				case 9:
				case 15:
				case 19:
				case 21:
				case 27:
				case 29:
				case 30:
					if (answer == 1) {
						score += 1;
					}
					break;

				default:
					if (answer == 0) {
						score += 1;
					}
					break;
				}
			}
			if (score < 11) {
				result = "无抑郁症";
			} else if (score < 21) {
				result = "轻度抑郁";
			} else {
				result = "重度抑郁";
			}
		}
		return result;
	}

	// 智力评估
	private String getConclusionByIntelligence(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (Integer answer : answers) {
				if (answer == 0) {
					score += 1;
				}
			}

			if (score < 10) {
				result = "重度";
			} else if (score < 21) {
				result = "中度";
			} else {
				result = "轻度";
			}
			result = "智力评估分数：" + score + "分，痴呆程度：" + result;
		}
		return result;
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
		System.out.println(mAnswers+"--"+mAnswers.size());
		if (mAnswers != null && mAnswers.size() > 0) {
			try {
				JSONObject jsonObject = new JSONObject();
				JSONArray array = new JSONArray();
				for (int i = 0; i < mAnswers.size(); i++) {
					array.put(mAnswers.get(i));
				}
				if (type == TYPE_SELF_CARE) {
					jsonObject.put("SELF_CARE", array);
					examinationInfo.setAgednessSelfcare(jsonObject.toString());
				} else if (type == TYPE_DEPRESSION) {
					jsonObject.put("DEPRESSION", array);
					examinationInfo.setAgednessDepression(jsonObject.toString());
				} else {
					jsonObject.put("INTELLIGENCE", array);
					examinationInfo.setAgednessIntelligence(jsonObject.toString());
				}

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
