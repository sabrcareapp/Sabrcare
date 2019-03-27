package com.sabrcare.app.medicine;

import io.realm.RealmObject;

public class MedicineModel extends RealmObject {

    private String medID;
    private String medName;
    private String dayPhase;
    private String time;
    private boolean reminderOn;

    public MedicineModel() {
        this.medName = "";
        this.dayPhase = "";
        this.time = "";
        this.reminderOn = false;

        medID=String.valueOf(System.currentTimeMillis());
    }

    public MedicineModel(String medName, String dayPhase, String time, boolean reminderOn) {
        this.medName = medName;
        this.dayPhase = dayPhase;
        this.time = time;
        this.reminderOn = reminderOn;
    }

    public boolean isReminderOn() {
        return reminderOn;
    }

    public void setReminderOn(boolean reminderOn) {
        this.reminderOn = reminderOn;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedID() {
        return medID;
    }

}
