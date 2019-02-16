package com.sabrcare.app.models;

public class MedicineModel {

    private String medName;
    private String dayPhase;
    private String reminder;
    private String time;

    public MedicineModel(String med_name, String dayPhase, String reminder, String time) {
        this.medName = med_name;
        this.dayPhase = dayPhase;
        this.reminder = reminder;
        this.time = time;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getDayPhase() {
        return dayPhase;
    }

    public void setDayPhase(String dayPhase) {
        this.dayPhase = dayPhase;
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
