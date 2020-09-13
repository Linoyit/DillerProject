package com.example.android.diller10threunion;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.VideoView;
import java.util.List;

public class Question {

    private Integer mId;
    private String mQuestion;
    private Integer mAnswer;
    private Integer[] mExtraAnswerResource;
    private String[] options = new String[4];
    private String mVideo;
    private String mImageUrl;


    public Question(){}

    public Question(Integer id, String question, String imageToken, String[] options, Integer answer,
                     String video, Integer[] extraResources){
        this.mId = id;
        this.mImageUrl = imageToken;
        this.mExtraAnswerResource = extraResources;
        this.mQuestion = question;
        this.mAnswer = answer;
        this.options = options;
        this.mVideo = video;

    }
    public Integer getId(){return mId;}
    public String getQuestion(){return mQuestion;}
    public String getImageResource(){return mImageUrl;}

    public Integer getAnswer(){return mAnswer;}
    public Integer[] getExtraAnswer(){return mExtraAnswerResource;}
    public String getVideo(){return mVideo;}
    public String[] getOptions(){return options;}

}
