package com.sabrcare.app.symptomtracker;

public class ModelSymptom {

    public String name;
    public int isCheck;
    public String severity;

    public ModelSymptom(String name,int isCheck, String severity ){
        this.name = name;
        this.isCheck = isCheck;
        this.severity = severity;
    }

}
