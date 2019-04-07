package com.sabrcare.app;


import android.net.Uri;

public class ModelTimeline  {

    private String title;
    private String subtitle;
    private String imageUri;
    private String timelineType;

    private String date;
    private String time;


    public ModelTimeline(String title, String subtitle, String imageUri, String timelineType,String date,String time) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUri = imageUri;
        this.timelineType=timelineType;
        this.date=date;
        this.time=time;
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



    public String getTimelineType() {
        return timelineType;
    }

    public void setTimelineType(String timelineType) {
        this.timelineType = timelineType;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
