package com.example.android.diller10threunion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ResultTriviaActivity extends AppCompatActivity {

    private TextView userName;
    private TextView finish;
    private TextView scoreAnnounce;
    private TextView scoreResult;
    private ImageView image;
    private Button finishButton;
    private Button morePicsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_result);

        userName = findViewById(R.id.user_name_end_of_trivia);
        finish = findViewById(R.id.goodbye);
        scoreAnnounce = findViewById(R.id.score_announce_tv);
        scoreResult = findViewById(R.id.result_number_tv);
        image = findViewById(R.id.imageView_end_trivia);
        finishButton = findViewById(R.id.finish_button);
        morePicsButton = findViewById(R.id.more_pics_button);

        String mUserName = getIntent().getStringExtra(QuestionsListFactory.USER_NAME);
        int numCorrectAnswers = getIntent().getIntExtra(QuestionsListFactory.CORRECT_ANSWERS,
                0);
        int numTotalQuestions = getIntent().getIntExtra(QuestionsListFactory.TOTAL_QUESTIONS,
                0);
        String result = numCorrectAnswers + "/" + numTotalQuestions;
        scoreResult.setText(result);
        userName.setText(mUserName);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultTriviaActivity.this,
                        MainActivity.class));
            }
        });

        morePicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer[] images = new Integer[]
                        {R.drawable.end1, R.drawable.end4, R.drawable.end7, R.drawable.end6,
                                R.drawable.farm2, R.drawable.end2, R.drawable.end8,
                                R.drawable.end3, R.drawable.end5, R.drawable.tongs};

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(images, 1);
                ViewPager2 viewPager2 = findViewById(R.id.viewPager_morePics);
                viewPager2.setVisibility(View.VISIBLE);
                hideViewsForViewPager();
                viewPager2.setAdapter(viewPagerAdapter);
            }
        });
    }

    private void hideViewsForViewPager(){
        userName.setVisibility(View.GONE);
        finish.setVisibility(View.GONE);
        scoreAnnounce.setVisibility(View.GONE);
        scoreResult.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
        finishButton.setVisibility(View.GONE);
        morePicsButton.setVisibility(View.GONE);
    }
}
