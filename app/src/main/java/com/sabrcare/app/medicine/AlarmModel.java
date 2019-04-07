package com.sabrcare.app.medicine;

import java.util.ArrayList;
import java.util.StringTokenizer;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AlarmModel extends RealmObject {
    private String Medicines;
    @PrimaryKey
    private String time;
    private boolean isOn;

    private long alarmID;


    public AlarmModel() {
        this.Medicines = "";
        this.isOn = false;
        time = "";
    }

    public void addMedicineToAlarm(String medicine) {
        this.Medicines += medicine + " ";
    }

    public void removeMedicineFromAlarm(String medicine) {
        StringTokenizer st = new StringTokenizer(this.Medicines, " ");
        ArrayList<String> meds = new ArrayList<>();
        while (st.hasMoreTokens()) {
            meds.add(st.nextToken());
        }
        this.Medicines = "";
        meds.remove(medicine);
        for (int i = 0; i < meds.size(); i++) {
            this.Medicines += meds.get(i) + ' ';
        }
    }

    public long getAlarmID() {
        return alarmID;
    }

    public void setAlarmID() {
        alarmID = System.currentTimeMillis();
    }

    public String getMedicines() {
        return Medicines;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
