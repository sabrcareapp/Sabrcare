package com.sabrcare.app;


import android.net.Uri;

public class ModelTimeline  {

    private String title;
    private String subtitle;
    private String imageUri;
    private String timelineType;


    public ModelTimeline(String title, String subtitle, String imageUri, String timelineType) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUri = imageUri;
        this.timelineType=timelineType;
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
}
