package com.sabrcare.app.models;

public class MedicineModel {

    private String med_name;
    private String day_phase;
    private String reminder;
    private String time;

    public MedicineModel(String med_name, String day_phase, String reminder, String time) {
        this.med_name = med_name;
        this.day_phase = day_phase;
        this.reminder = reminder;
        this.time = time;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getDay_phase() {
        return day_phase;
    }

    public void setDay_phase(String day_phase) {
        this.day_phase = day_phase;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
