package com.sabrcare.app;


import android.net.Uri;

public class ModelTimeline  {

    private String title;
    private String subtitle;
    private String imageUri;

    public ModelTimeline(String title, String subtitle, String imageUri) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUri = imageUri;
    }

    public ModelTimeline() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
