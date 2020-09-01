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
    private Integer mVideo = -1;
    private Integer mImage;

    public Question(){}

    public Question(Integer id, String question, Integer image, String[] options, Integer answer,
                     Integer video, Integer[] extraResources){
        this.mId = id;
        this.mImage = image;
        this.mExtraAnswerResource = extraResources;
        this.mQuestion = question;
        this.mAnswer = answer;
        this.options = options;
        this.mVideo = video;

    }
    public Integer getId(){return mId;}
    public String getQuestion(){return mQuestion;}
    public Integer getImageResource(){return mImage;}

    public Integer getAnswer(){return mAnswer;}
    public Integer[] getExtraAnswer(){return mExtraAnswerResource;}
    public Integer getVideo(){return mVideo;}
    public String[] getOptions(){return options;}

}
