package com.example.android.diller10threunion;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question;
    private TextView option1;
    private TextView option2;
    private TextView option3;
    private TextView option4;

    private ImageView mImageView;
    private VideoView mVideo;
    private Button nextButton;
    private ProgressBar progressBar;
    private TextView progressTextView;
    private Integer mCurrentPosition = 1;
    private List<Question> mQuestionsList = null;
    private Integer mSelectedOptionPosition = 0;
    private Integer mNumberOfCorrectAnswers = 0;

    private String mUserName = "היי אתה";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        getWindow().getDecorView().setTag(View.SYSTEM_UI_FLAG_FULLSCREEN);

        mUserName = getIntent().getStringExtra(QuestionsListFactory.USER_NAME);
        initViews();
        QuestionsListFactory factory = new QuestionsListFactory();
        mQuestionsList = factory.getQuestions();
        updateView();
        defaultOptionsView();

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    private void initViews(){
        question = findViewById(R.id.tv_question);
        option1 = findViewById(R.id.q1);
        option2 = findViewById(R.id.q2);
        option3 = findViewById(R.id.q3);
        option4 = findViewById(R.id.q4);
        mVideo = findViewById(R.id.videoView_trivia);
        mImageView = findViewById(R.id.imageView_trivia);
        nextButton = findViewById(R.id.button_next);
        progressBar = findViewById(R.id.progress_bar);
        progressTextView = findViewById(R.id.tv_progress);
    }

    private void updateView(){

        mVideo.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);

        Question q = mQuestionsList.get(mCurrentPosition - 1);
        progressBar.setProgress(mCurrentPosition);
        String CurrentProgress = mCurrentPosition + "/"+ progressBar.getMax();
        progressTextView.setText(CurrentProgress);
        defaultOptionsView();
        if (mCurrentPosition == mQuestionsList.size()){
            nextButton.setText("סיום");
        } else {
            nextButton.setText("שלח");
        }

        question.setText(q.getQuestion());

        if (q.getVideo() != null){

            mImageView.setVisibility(View.GONE);
            mVideo.setVisibility(View.VISIBLE);

//            String videoPath = "android.resource://"+getPackageName()+"/" + q.getVideo();
            String videoPath = q.getVideo();
            Uri uri = Uri.parse(videoPath);
            mVideo.setVideoURI(uri);

            MediaController mediaController = new MediaController(this);
            mVideo.setMediaController(mediaController);
            mediaController.setAnchorView(mVideo);

        } else if (q.getImageResource() != null){

            Glide.with(mImageView.getContext())
                    .load(q.getImageResource())
                    .fitCenter()
                    .into(mImageView);
        }

        option1.setText(q.getOptions()[0]);
        option2.setText(q.getOptions()[1]);
        option3.setText(q.getOptions()[2]);
        option4.setText(q.getOptions()[3]);
    }

    private void defaultOptionsView(){
        List<TextView> options = new ArrayList<>();
        options.add(0, option1);
        options.add(1, option2);
        options.add(2, option3);
        options.add(3, option4);

        for (TextView option : options){
            option.setTextColor(Color.parseColor("#9e9e9e"));
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.trivia_button_shape));
        }
    }

    @Override
    public void onClick(View view) {
        Log.v("view id:",view.getId()+"");
        switch (view.getId()){
            case R.id.q1:
                Log.v("clicked view id:",R.id.q1+"");
                selectedOptionView(option1, 1);
                break;
            case R.id.q2:
                Log.v("clicked view id:",R.id.q2+"");
                selectedOptionView(option2, 2);
                break;
            case R.id.q3:
                Log.v("clicked view id:",R.id.q3+"");
                selectedOptionView(option3, 3);
                break;
            case R.id.q4:
                Log.v("clicked view id:",R.id.q4+"");
                selectedOptionView(option4, 4);
                break;
            case R.id.button_next:
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++;
                    if (mCurrentPosition <= mQuestionsList.size()){
                        updateView();
                    } else {
                        Intent intent = new Intent(GameActivity.this,
                                ResultTriviaActivity.class);
                        intent.putExtra(QuestionsListFactory.USER_NAME, mUserName);
                        intent.putExtra(QuestionsListFactory.CORRECT_ANSWERS, mNumberOfCorrectAnswers);
                        intent.putExtra(QuestionsListFactory.TOTAL_QUESTIONS, mQuestionsList.size());
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Question question = mQuestionsList.get(mCurrentPosition - 1);
                    if (!question.getAnswer().equals(mSelectedOptionPosition)){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_selected_option);
                    } else
                        { mNumberOfCorrectAnswers++; }
                    answerView(question.getAnswer(), R.drawable.correct_option_selected);
                    if (question.getExtraAnswer() != null) {
                        if (question.getVideo() != null){
                            mVideo.setVisibility(View.GONE);
                            mImageView.setVisibility(View.VISIBLE);
                        }
                        mImageView.setImageResource(question.getExtraAnswer()[0]);
                    }
                    if (mCurrentPosition == mQuestionsList.size()){
                        nextButton.setText("סיום");
                    } else {
                        nextButton.setText("שאלה הבאה");
                    }
                    mSelectedOptionPosition = 0;
                }
                break;
        }
    }

    private void answerView(Integer correctAnswer, Integer drawableView){
        switch (correctAnswer){
            case 1:
                option1.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 2:
                option2.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 3:
                option3.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 4:
                option4.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
        }
    }

    private void selectedOptionView(TextView textView, Integer selectedOptionNumber){
        defaultOptionsView();
        mSelectedOptionPosition = selectedOptionNumber;
        textView.setTextColor(Color.parseColor("#263238"));
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setBackground(ContextCompat.getDrawable(this,
                R.drawable.selected_button_shape));
    }
}
