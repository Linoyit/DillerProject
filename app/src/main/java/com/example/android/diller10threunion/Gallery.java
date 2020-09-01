package com.example.android.diller10threunion;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    private List<String> photoUrls = new ArrayList<>();
    private Integer clickedUrl;

    Gallery(){}
    Gallery(Integer cur){
        clickedUrl = cur;
    }

    public void addUrl(String url) {
        photoUrls.add(url);
    }
    public void addClickedUrl(Integer i){
        clickedUrl = i;
    }
    public void removeUrl(String url) { photoUrls.remove(url);}

    public Integer getClickedUrl(){
        return clickedUrl;
    }
    public List<String> getPhotoUrls(){
        return photoUrls;
    }

}
