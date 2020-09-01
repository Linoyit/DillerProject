package com.example.android.diller10threunion;

import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class Message {

//    private String text;
//    private String name;
    private String photoUrl;
    private String mKey;
//    private Uri mImageUri;

    public Message() {}

    public Message(String photoUrl) {
//        this.text = text;
//        this.name = name;
        this.photoUrl = photoUrl;
    }

//    public String getText(){ return text; }
//    public String getName(){ return name; }
    public String getPhotoUrl(){ return photoUrl; }
    public String getKey(){ return mKey; }
//    public Uri getImageUri() {return mImageUri;}
//
//    public void setText(String text){ this.text = text; }
//    public void setName(String name){ this.name = name; }
    public void setPhotoUrl(String photoUrl){ this.photoUrl = photoUrl; }
    public void setKey(String key){ mKey = key;}
//    public void setImageUri(Uri uri) {mImageUri = uri;}
}
