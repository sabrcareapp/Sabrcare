package com.sabrcare.app.timeline;

import android.net.Uri;

public class TimelineModel {

    private String title;
    private String subtitle;
    private Uri uri;

    public TimelineModel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.uri = uri;                                             //to be used later
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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
