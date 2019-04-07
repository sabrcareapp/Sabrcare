package com.sabrcare.app.symptomtracker;

import com.android.volley.toolbox.StringRequest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelSymptom extends RealmObject {

    @PrimaryKey
    public String name;
    public int isCheck;
    public String severity;

    public ModelSymptom(){
    }

    public ModelSymptom(String name,int isCheck, String severity ){
        this.name = name;
        this.isCheck = isCheck;
        this.severity = severity;
    }

    public String getName(){
        return name;
    }

    public int getIsCheck(){
        return isCheck;
    }

    public String getSeverity(){
        return severity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIsCheck(int isCheck){
        this.isCheck = isCheck;
    }

    public void setSeverity(String severity){
        this.severity=severity;
    }
}
