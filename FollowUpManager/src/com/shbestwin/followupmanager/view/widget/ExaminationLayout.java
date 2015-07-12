package com.shbestwin.followupmanager.view.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.shbestwin.followupmanager.R;
import com.shbestwin.followupmanager.common.util.ArrayUtils;
import com.shbestwin.followupmanager.common.util.CollectionUtils;
import com.shbestwin.followupmanager.common.util.ToastUtils;
import com.shbestwin.followupmanager.model.examination.Question;

public class ExaminationLayout extends FrameLayout {
	private ExaminationIndicator examinationIndicator;
	private TextView mainTitleTextView, subTitleTextView, questionTipsTextView,
			questionTextView;
	private RadioGroup optionsRadioGroup;
	private Button preQuestionButton, nextQuestionButton, endButton;

	private List<Question> questions;
	private List<String> options;
	private int currentIndex;
	private List<Integer> answers;

	private boolean isNext = true;

	public ExaminationLayout(Context context) {
		this(context, null);
	}

	public ExaminationLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ExaminationLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View root = LayoutInflater.from(context).inflate(
				R.layout.view_examination, this, true);
		examinationIndicator = (ExaminationIndicator) root
				.findViewById(R.id.examinationIndicator);
		mainTitleTextView = (TextView) root
				.findViewById(R.id.mainTitleTextView);
		subTitleTextView = (TextView) root.findViewById(R.id.subTitleTextView);
		questionTipsTextView = (TextView) root
				.findViewById(R.id.questionTipsTextView);
		questionTextView = (TextView) root.findViewById(R.id.questionTextView);
		optionsRadioGroup = (RadioGroup) root
				.findViewById(R.id.optionsRadioGroup);

		preQuestionButton = (Button) root.findViewById(R.id.preQuestionButton);
		nextQuestionButton = (Button) root
				.findViewById(R.id.nextQuestionButton);
		endButton = (Button) root.findViewById(R.id.endButton);

		endButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onEndClickListener != null) {
					onEndClickListener.onEndClick(answers);
				}
			}
		});

		preQuestionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (check()) {
					handlePre();
				}
			}
		});

		nextQuestionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (check()) {
					handleNext();
				}
			}
		});

		optionsRadioGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						answers.set(currentIndex, checkedId);
						boolean result = answers.get(currentIndex) >= 0 ? true
								: false;
						if (result && isNext
								&& (currentIndex < (questions.size() - 1))) {
							new Handler().postDelayed(new Runnable() {
								@Override
								public void run() {
									isNext = false;
									handleNext();
								}
							}, 500);
						}
					}
				});
		options = null;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	private boolean check() {
		boolean result = answers.get(currentIndex) >= 0 ? true : false;
		if (!result) {
			ToastUtils.showToast(getContext(), "请选择一个答案！");
		}
		return result;
	}

	private void handlePre() {
		currentIndex--;
		if (currentIndex <= 0) {
			preQuestionButton.setEnabled(false);
		} else {
			preQuestionButton.setEnabled(true);
		}
		nextQuestionButton.setEnabled(true);
		changeQuestion();
	}

	private void handleNext() {
		currentIndex++;
		if (currentIndex >= (questions.size() - 1)) {
			nextQuestionButton.setEnabled(false);
		} else {
			nextQuestionButton.setEnabled(true);
		}
		preQuestionButton.setEnabled(true);
		changeQuestion();
		isNext = true;
	}

	public void renderView(String mainTitle, String subTitle,
			List<Question> questions, List<String> options) {
		if (CollectionUtils.isEmpty(questions)
				|| CollectionUtils.isEmpty(options)) {
			return;
		}
		this.questions = questions;
		this.options = options;

		answers = new ArrayList<Integer>();
		for (int i = 0; i < questions.size(); i++) {
			answers.add(-1);
		}
		if (!TextUtils.isEmpty(mainTitle)) {
			mainTitleTextView.setText(mainTitle);
			mainTitleTextView.setVisibility(View.VISIBLE);
		} else {
			mainTitleTextView.setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(subTitle)) {
			subTitleTextView.setText(subTitle);
			subTitleTextView.setVisibility(View.VISIBLE);
		} else {
			subTitleTextView.setVisibility(View.GONE);
		}

		currentIndex = 0;
		preQuestionButton.setEnabled(false);
		if (currentIndex >= (questions.size() - 1)) {
			nextQuestionButton.setEnabled(false);
		}
		examinationIndicator.renderView(questions.size());
		changeQuestion();
	}

	public void renderView(String mainTitle, String subTitle,
			List<Question> questions, String[] options) {
		if (CollectionUtils.isEmpty(questions) || ArrayUtils.isEmpty(options)) {
			return;
		}
		this.questions = questions;

		answers = new ArrayList<Integer>();
		for (int i = 0; i < questions.size(); i++) {
			answers.add(-1);
		}
		if (!TextUtils.isEmpty(mainTitle)) {
			mainTitleTextView.setText(mainTitle);
			mainTitleTextView.setVisibility(View.VISIBLE);
		} else {
			mainTitleTextView.setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(subTitle)) {
			subTitleTextView.setText(subTitle);
			subTitleTextView.setVisibility(View.VISIBLE);
		} else {
			subTitleTextView.setVisibility(View.GONE);
		}

		currentIndex = 0;
		preQuestionButton.setEnabled(false);
		nextQuestionButton.setEnabled(true);
		if (currentIndex >= (questions.size() - 1)) {
			nextQuestionButton.setEnabled(false);
		}
		examinationIndicator.renderView(questions.size());
		changeQuestion();

		optionsRadioGroup.removeAllViews();
		for (int i = 0; i < options.length; i++) {
			RadioButton child = new RadioButton(getContext());
			child.setText(options[i]);
			child.setTextColor(getResources().getColor(R.color.black));
			child.setTextSize(18);
			child.setId(i);
			RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(
					RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT);
			lp.rightMargin = (int) (getResources().getDisplayMetrics().density * 50);
			optionsRadioGroup.addView(child, lp);
		}
	}

	public boolean isFinished() {
		if (CollectionUtils.isEmpty(answers)) {
			return false;
		}
		for (Integer answer : answers) {
			if (answer < 0) {
				return false;
			}
		}
		return true;
	}

	private void changeQuestion() {
		isNext = false;
		questionTipsTextView.setText(Html.fromHtml(String.format(getResources()
				.getString(R.string.jktj_test_question_tips),
				(currentIndex + 1), questions.size())));
		questionTextView.setText(questions.get(currentIndex).getQuestion()
				.replace("|", "\n"));
		examinationIndicator.updateIndex(currentIndex);
		int id = answers.get(currentIndex);
		if (id >= 0) {
			optionsRadioGroup.check(id);
		} else {
			optionsRadioGroup.clearCheck();
		}
		changeOptions();
		isNext = true;
	}

	private void changeOptions() {
		if (options == null) {
			return;
		}
		optionsRadioGroup.removeAllViews();
		String items = options.get(currentIndex);
		String[] optionArray = items.split(",");
		for (int i = 0; i < optionArray.length; i++) {
			RadioButton child = new RadioButton(getContext());
			child.setText(optionArray[i]);
			child.setTextColor(getResources().getColor(R.color.black));
			child.setTextSize(18);
			child.setId(i);
			RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(
					RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT);
			lp.rightMargin = (int) (getResources().getDisplayMetrics().density * 50);
			optionsRadioGroup.addView(child, lp);
		}

	}

	public interface OnEndClickListener {
		public void onEndClick(List<Integer> answers);
	}

	private OnEndClickListener onEndClickListener;

	public void setOnEndClickListener(OnEndClickListener onEndClickListener) {
		this.onEndClickListener = onEndClickListener;
	}
}
