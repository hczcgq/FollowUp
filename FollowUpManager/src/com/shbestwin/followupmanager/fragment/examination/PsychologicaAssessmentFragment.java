package com.shbestwin.followupmanager.fragment.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.fragment.BaseFragment;
import com.shbestwin.followupmanager.manager.ExaminationManager;
import com.shbestwin.followupmanager.model.examination.Question;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout;
import com.shbestwin.followupmanager.view.widget.ExaminationLayout.OnEndClickListener;

/**
 * 
 * 心理评估
 *
 * @version
 */
public class PsychologicaAssessmentFragment extends BaseFragment implements OnClickListener {
	private ViewFlipper viewFlipper;
	private ExaminationLayout examinationLayout;
	private ImageView sdsImageView, sasImageView, psqiImageView, saqImageView, uclaImageView, gcqImageView, scl90ImageView, qlscaImageView;
	private LinearLayout contentLayout;
	private String type;

	private TextView resultTitleTextView, featureTextView, guidanceTextView;
	private Button returnButton;

	public static PsychologicaAssessmentFragment newInstance() {
		PsychologicaAssessmentFragment fragment = new PsychologicaAssessmentFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_examination_psychological_assessment, container, false);
		viewFlipper = (ViewFlipper) rootView;
		examinationLayout = (ExaminationLayout) rootView.findViewById(R.id.examinationLayout);

		sdsImageView = (ImageView) rootView.findViewById(R.id.sdsImageView);
		sasImageView = (ImageView) rootView.findViewById(R.id.sasImageView);
		psqiImageView = (ImageView) rootView.findViewById(R.id.psqiImageView);
		saqImageView = (ImageView) rootView.findViewById(R.id.saqImageView);
		uclaImageView = (ImageView) rootView.findViewById(R.id.uclaImageView);
		gcqImageView = (ImageView) rootView.findViewById(R.id.gcqImageView);
		scl90ImageView = (ImageView) rootView.findViewById(R.id.scl90ImageView);
		qlscaImageView = (ImageView) rootView.findViewById(R.id.qlscaImageView);

		resultTitleTextView = (TextView) rootView.findViewById(R.id.resultTitleTextView);
		featureTextView = (TextView) rootView.findViewById(R.id.featureTextView);
		guidanceTextView = (TextView) rootView.findViewById(R.id.guidanceTextView);
		contentLayout = (LinearLayout) rootView.findViewById(R.id.contentLayout);
		returnButton = (Button) rootView.findViewById(R.id.returnButton);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sdsImageView.setOnClickListener(this);
		sasImageView.setOnClickListener(this);
		psqiImageView.setOnClickListener(this);
		saqImageView.setOnClickListener(this);
		uclaImageView.setOnClickListener(this);
		gcqImageView.setOnClickListener(this);
		scl90ImageView.setOnClickListener(this);
		qlscaImageView.setOnClickListener(this);

		examinationLayout.setOnEndClickListener(new OnEndClickListener() {
			@Override
			public void onEndClick(List<Integer> answers) {
				// 看是否所有题目都做完了，如果是，显示测试结果，否则直接结束
				boolean isFinish = true;
				for (int answer : answers) {
					if (answer < 0) {
						isFinish = false;
						break;
					}
				}
				if (isFinish) {// 显示测试结果
					viewFlipper.setDisplayedChild(2);
					showConclusion(answers);
				} else {// 直接结束，到初始页面
					if (viewFlipper.getDisplayedChild() == 1) {
						viewFlipper.showPrevious();
					}
				}
			}
		});

		returnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				viewFlipper.setDisplayedChild(0);
			}
		});
	}

	@Override
	public void onClick(View v) {
		type = (String) v.getTag();
		if (viewFlipper.getDisplayedChild() == 0) {
			viewFlipper.showNext();
			renderQuestion();
		}
	}

	private void renderQuestion() {
		String mainTitle = "";
		String[] options = null;
		List<Question> questions = ExaminationManager.getInstance(getActivity()).getPsychologicaQuestions(type);

		if ("sds".equals(type)) {
			options = getResources().getStringArray(R.array.SDSOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_sds);
		} else if ("sas".equals(type)) {
			options = getResources().getStringArray(R.array.SASOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_sas);
		} else if ("psqi".equals(type)) {
			List<String> _options = ExaminationManager.getInstance(getActivity()).getPsychologicaOptionss(type);
			mainTitle = getString(R.string.jktj_xlpg_title_psqi);
			examinationLayout.renderView(mainTitle, getString(R.string.jktj_test_question_subtitle), questions, _options);
			return;
		} else if ("saq".equals(type)) {
			options = getResources().getStringArray(R.array.SAQOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_saq);
		} else if ("ucla".equals(type)) {
			options = getResources().getStringArray(R.array.UCLAOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_ucla);
		} else if ("gcq".equals(type)) {
			options = getResources().getStringArray(R.array.GCQOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_gcq);
		} else if ("scl90".equals(type)) {
			options = getResources().getStringArray(R.array.SCL90Options);
			mainTitle = getString(R.string.jktj_xlpg_title_scl90);
		} else if ("qlsca".equals(type)) {
			options = getResources().getStringArray(R.array.QLSCAOptions);
			mainTitle = getString(R.string.jktj_xlpg_title_qlsca);
		}
		examinationLayout.renderView(mainTitle, getString(R.string.jktj_test_question_subtitle), questions, options);
	}

	/**
	 * 
	 * @Title: showConclusion
	 * @Description: 显示结论
	 * @param
	 * @return void
	 * @throws
	 */
	private void showConclusion(List<Integer> answers) {
		String resultTitle = "";
		String feature = "";
		String guidance = "";
		contentLayout.setVisibility(View.GONE);
		featureTextView.setVisibility(View.VISIBLE);
		if ("sds".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_sds) + " - 测试结果：";
			feature = getConclusionBySds(answers);
		} else if ("sas".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_sas) + " - 测试结果：";
			feature = getConclusionBySas(answers);
		} else if ("psqi".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_psqi) + " - 测试结果：";
			feature = getConclusionByPsqi(answers);
		} else if ("saq".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_saq) + " - 测试结果：";
			feature = getConclusionBySaq(answers);
		} else if ("ucla".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_ucla) + " - 测试结果：";
			feature = getConclusionByUcla(answers);
		} else if ("gcq".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_gcq) + " - 测试结果：";
			feature = getConclusionByGcq(answers);
		} else if ("scl90".equals(type)) {
			contentLayout.setVisibility(View.VISIBLE);
			featureTextView.setVisibility(View.GONE);
			resultTitle = getString(R.string.jktj_xlpg_title_scl90) + " - 测试结果：";
			contentLayout.removeAllViews();
			ArrayList<SCL90> result = getConclusionByScl90(answers);
			contentLayout.addView(createItem("指标", "原始分", "平均分"), LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			for (SCL90 sCL90 : result) {
				contentLayout.addView(createItem(sCL90.name, "" + sCL90.score, "" + sCL90.avgScore), LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			}
		} else if ("qlsca".equals(type)) {
			resultTitle = getString(R.string.jktj_xlpg_title_qlsca) + " - 测试结果：";
			feature = getConclusionByQlsca(answers);
		}
		resultTitleTextView.setText(resultTitle);
		featureTextView.setText(feature);
		guidanceTextView.setText(guidance);
	}

	private LinearLayout createItem(String text1, String text2, String text3) {
		LinearLayout item = new LinearLayout(getActivity());
		item.setVerticalGravity(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		item.addView(createItemView(text1), lp);
		item.addView(createItemView(text2), lp);
		item.addView(createItemView(text3), lp);
		return item;
	}

	private TextView createItemView(String text) {
		// <TextView
		// android:layout_width="0dp"
		// android:layout_height="wrap_content"
		// android:layout_weight="1"
		// android:padding="10dp"
		// android:text="平均分"
		// android:textColor="@color/black"
		// android:textSize="@dimen/global_font_size_large" />
		TextView textView = new TextView(getActivity());
		textView.setTextColor(getResources().getColor(R.color.black));
		textView.setTextSize(18);
		int padding = (int) (getResources().getDisplayMetrics().density * 5);
		textView.setPadding(padding, padding, padding, padding);
		textView.setText(text);
		return textView;
	}

	/**
	 * 
	 * @Title: getConclusionBySds
	 * @Description: 抑郁自评量表（SDS）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionBySds(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (Integer answer : answers) {
				if (answer == 0) {// 回答“是”得1分，回答“否”得0分
					score++;
				}
			}

			if (score < 5) {
				result = "你的心理基本正常，没有抑郁症状。";
			} else if (score < 11) {
				result = "你有轻微的抑郁症状，可采取自我心理调节，保持乐观开朗的心境。";
			} else if (score < 21) {
				result = "你属于中度的抑郁，要找医生咨询，并进行必要的诊疗。";
			} else {
				result = "你精神明显抑郁，症状非常严重，你应该请医生给你治疗，同时应进行精              神上的自我训练，让自己及早从消极、压抑的情绪中解脱出来。";
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionBySas
	 * @Description: 焦虑自评量表（SAS）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionBySas(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (Integer answer : answers) {
				if (answer == 1) {// A为0分，B为1分
					score++;
				}
			}
			if (score < 4) {
				result = "你的心境平和如镜。在面对诸多问题时，你阵脚不乱，应付自如，带着微笑与必胜的信念迎向生活。";
			} else if (score < 10) {
				result = "这表明你身心状态良好，一般可以好好地控制自己的情绪，但依然有少焦虑。您可通过自然疗法，如运动疗法、负离子疗法等放松心情，降低焦虑，减少不必要的担心。";
			} else {
				result = "看得出你为生活操心。分数越高，你越容易焦虑，越易于承受各方面的精神压力。因此你常为一些不值得担心的事而放心不下，甚至于被激怒、无故发脾气、烦躁不安。您有必要立即采取行之有效的治疗措施，以免延误病情。同时，在确保疗效的情况下，一定要尽量采取自然疗法，以免造成身体上更大的伤害。祝您永远健康！";
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionByPsqi
	 * @Description: 匹兹堡睡眠质量指数（PSQI）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionByPsqi(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			// 因子1：问题1
			// 因子3：问题3
			// 因子4：问题4
			// 因子6：问题15

			// 因子2：问题2+问题5
			// 因子5：问题6,7,8,9,10,11,12,13,14

			// 因子7：问题16+问题17
			int score = 0;
			int score2 = 0;
			int score5 = 0;
			int score7 = 0;
			for (int i = 0; i < answers.size(); i++) {
				switch (i + 1) {
				case 1:
				case 3:
				case 4:
				case 15:
					score += answers.get(i);
					break;
				case 2:
				case 5:
					score2 += answers.get(i);
					break;
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
					score5 += answers.get(i);
					break;
				case 16:
				case 17:
					score7 += answers.get(i);
					break;
				}
			}

			if (score5 <= 0) {
				score5 = 0;
			} else if (score5 < 10) {
				score5 = 1;
			} else if (score5 < 19) {
				score5 = 2;
			} else {
				score5 = 3;
			}

			if (score7 <= 0) {
				score7 = 0;
			} else if (score2 < 3) {
				score7 = 1;
			} else if (score2 < 5) {
				score7 = 2;
			} else {
				score7 = 3;
			}

			if (score7 <= 0) {
				score7 = 0;
			} else if (score2 < 3) {
				score7 = 1;
			} else if (score2 < 5) {
				score7 = 2;
			} else {
				score7 = 3;
			}

			score += (score2 + score5 + score7);
			if (score < 6) {
				result = "睡眠质量很好";
			} else if (score < 11) {
				result = "睡眠质量还行";
			} else if (score < 16) {
				result = "睡眠质量一般";
			} else {
				result = "睡眠质量很差";
			}

		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionBySaq
	 * @Description: 自杀态度问卷（SAQ）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionBySaq(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			// 1—15题，17—20题——每题选1记0分，选2记1分，选3记2分，选4记4分。
			// 　　16题——选1记4分，选2记2分，选3记1分，选4记0分。
			int score = 0;
			for (int i = 0; i < answers.size(); i++) {
				if (i == 15) {
					switch (answers.get(i)) {
					case 0:
						score += 4;
						break;
					case 1:
						score += 2;
						break;
					case 2:
						score += 1;
						break;
					}
				} else {
					switch (answers.get(i)) {
					case 1:
						score += 1;
						break;
					case 2:
						score += 2;
						break;
					case 3:
						score += 4;
						break;
					}
				}

				if (score < 26) {
					result = "你是个乐天派，生活幸福，无忧无虑，令人羡慕。";
				} else if (score < 36) {
					result = "你生活中有些小烦恼，承受了一些压力，有时候觉得压力过大，但大多已过去。只要保持健康、积极的心态，生活一定幸福、美满！";
				} else if (score < 50) {
					result = "你面对的压力较大，有时候想过逃避或休息，但效果似乎并不好。总的来说，你的压力需要得到释放，建议你调整心情，或休息一段时间，或努力进取，或找人倾诉，尽快从低落中走出来，保持健康积极心态！";
				} else if (score < 66) {
					result = "你面临严重的心理危机，压力很大，心情沮丧，对未来感觉渺茫，有逃避想法。需要家人、朋友的关怀和支持，建议走出自我小圈子，积极和他人沟通，寻求他人帮助，可以考虑心理咨询，恢复轻松愉快心情。";
				} else {
					result = "你承受巨大的压力，心情极度恶劣，甚至感到消极厌世，心理状况不容乐观，也许需要很好休息、宣泄、调整，甚至需要外界的帮助和支持。多与家人沟通，很有必要做心理咨询缓解压力！";
				}
			}

		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionByUcla
	 * @Description: UCLA孤独量表
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionByUcla(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			// 0,4,5,8,9,14,15,18,19
			// 问题前标有星号的依据下面的等级计分：
			// 1=一直，2=有时，3=很少，4=从不
			// 问题前未标有星号的依据下面的等级计分：
			// 1=从不，2=很少，3=有时，4=一直
			int score = 0;
			for (int i = 0; i < answers.size(); i++) {
				if (i == 0 || i == 4 || i == 5 || i == 8 || i == 9 || i == 14 || i == 15 || i == 18 || i == 19) {
					switch (answers.get(i)) {
					case 0:// 从不
						score += 4;
						break;
					case 1:// 很少
						score += 3;
						break;
					case 2:// 有时
						score += 2;
						break;
					case 3:// 一直
						score += 1;
						break;
					}
				} else {
					switch (answers.get(i)) {
					case 0:// 从不
						score += 1;
						break;
					case 1:// 很少
						score += 2;
						break;
					case 2:// 有时
						score += 3;
						break;
					case 3:// 一直
						score += 4;
						break;
					}
				}
			}

			// 大于44说明孤独感很强，小于28孤独感很弱，大部分人介于33-39之间
			if (score > 44) {
				result = "孤独感很强";
			} else if (score < 28) {
				result = "孤独感很弱";
			} else {
				result = "孤独感一般";
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionByGcq
	 * @Description: 舒适状况量表（GCQ）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionByGcq(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (Integer answer : answers) {
				switch (answer) {
				case 1:
					score += 1;
					break;
				case 2:
					score += 2;
					break;
				case 3:
					score += 3;
					break;

				}
			}
			result = "舒适状况分数：" + score + "分，具体请咨询您的家庭医生";
		}
		return result;
	}

	/**
	 * 
	 * @Title: getConclusionByScl90
	 * @Description: 90项症状清单（SCL-90）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private ArrayList<SCL90> getConclusionByScl90(List<Integer> answers) {
		ArrayList<SCL90> result = null;
		if (!CollectionUtils.isEmpty(answers)) {
			result = new ArrayList<PsychologicaAssessmentFragment.SCL90>();
			result.add(new SCL90("躯体化", 12, "1.37 ± 0.48"));
			result.add(new SCL90("强迫", 10, "1.62 ± 0.58"));
			result.add(new SCL90("人际关系", 9, "1.65 ± 0.51"));
			result.add(new SCL90("抑郁", 13, "1.50 ± 0.59"));
			result.add(new SCL90("焦虑", 10, "1.39 ± 0.43"));
			result.add(new SCL90("敌对", 6, "1.48 ± 0.56"));
			result.add(new SCL90("恐怖", 7, "1.23 ± 0.41"));
			result.add(new SCL90("偏执", 6, "1.43 ± 0.57"));
			result.add(new SCL90("精神病性", 10, "1.29 ± 0.42"));
			result.add(new SCL90("其他项目", 7, "1.29 ± 0.42"));
			for (int i = 0; i < answers.size(); i++) {
				int score = answers.get(i) + 1;
				switch (i + 1) {
				// F1
				case 1:
				case 4:
				case 12:
				case 27:
				case 40:
				case 42:
				case 48:
				case 49:
				case 52:
				case 53:
				case 56:
				case 58:
					result.get(0).score += score;
					break;
				// F2
				case 3:
				case 9:
				case 10:
				case 28:
				case 38:
				case 45:
				case 46:
				case 51:
				case 55:
				case 65:
					result.get(1).score += score;
					break;
				// F3
				case 6:
				case 21:
				case 34:
				case 36:
				case 37:
				case 41:
				case 61:
				case 69:
				case 73:
					result.get(2).score += score;
					break;
				// F4
				case 5:
				case 14:
				case 15:
				case 20:
				case 22:
				case 26:
				case 29:
				case 30:
				case 31:
				case 32:
				case 54:
				case 71:
				case 79:
					result.get(3).score += score;
					break;
				// F5
				case 2:
				case 17:
				case 23:
				case 33:
				case 39:
				case 57:
				case 72:
				case 78:
				case 80:
				case 86:
					result.get(4).score += score;
					break;
				// F6
				case 11:
				case 24:
				case 63:
				case 67:
				case 74:
				case 81:
					result.get(5).score += score;
					break;
				// F7
				case 13:
				case 25:
				case 47:
				case 50:
				case 70:
				case 75:
				case 82:
					result.get(6).score += score;
					break;
				// F8
				case 8:
				case 18:
				case 43:
				case 68:
				case 76:
				case 83:
					result.get(7).score += score;
					break;
				// F9
				case 7:
				case 16:
				case 35:
				case 62:
				case 77:
				case 84:
				case 85:
				case 87:
				case 88:
				case 90:
					result.get(8).score += score;
					break;
				// F10
				case 19:
				case 44:
				case 59:
				case 60:
				case 64:
				case 66:
				case 89:
					result.get(9).score += score;
					break;
				}
			}
			for (SCL90 sCL90 : result) {
				sCL90.avgScore = new BigDecimal(sCL90.score).divide(new BigDecimal(sCL90.count), 2, RoundingMode.DOWN).floatValue();
			}
		}
		return result;
	}

	static class SCL90 {
		String name;
		int score;
		int count;
		float avgScore;
		String result;
		String reference;

		public SCL90(String name, int count, String reference) {
			super();
			this.name = name;
			this.count = count;
			this.reference = reference;
		}
	}

	/**
	 * 
	 * @Title: getConclusionByQlsca
	 * @Description: 儿童少年生活质量量表（QLSCA）
	 * @param @param answers
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getConclusionByQlsca(List<Integer> answers) {
		String result = "";
		if (!CollectionUtils.isEmpty(answers)) {
			int score = 0;
			for (Integer answer : answers) {
				switch (answer) {
				case 1:
					score += 1;
					break;
				case 2:
					score += 2;
					break;
				case 3:
					score += 3;
					break;
				}
			}
			result = "少儿生活质量分数：" + score + "分，具体请咨询您的家庭医生";
		}
		return result;
	}
}
