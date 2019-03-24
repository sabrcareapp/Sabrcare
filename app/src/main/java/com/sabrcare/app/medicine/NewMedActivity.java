package com.sabrcare.app.medicine;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sabrcare.app.AlarmReceiver;
import com.sabrcare.app.HomeActivity;
import com.sabrcare.app.R;


import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;


public class NewMedActivity extends AppCompatActivity {
    public Spinner day_phase,remCount;
    public EditText med_name;
    public TextView reminderTime;
    public TimePickerDialog timePickerDialog;
    public Button save, delete;
    Switch alarmSwitch;

    int hr, min;
    AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;

    MedicineModel medicineModel;
    AlarmModel alarmModel;
    Realm realm;

    boolean timeFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        bindViews();

        final ArrayAdapter<CharSequence> adapter_day_phase = ArrayAdapter.createFromResource(NewMedActivity.this, R.array.Day_Phase, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> adapter_med_times = ArrayAdapter.createFromResource(NewMedActivity.this, R.array.Med_Times, R.layout.support_simple_spinner_dropdown_item);
        adapter_day_phase.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter_med_times.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        day_phase.setAdapter(adapter_day_phase);
        remCount.setAdapter(adapter_med_times);

        realm = Realm.getDefaultInstance();

        if (getIntent().getAction().equalsIgnoreCase("Edit")) {
            //TODO Querying with medName only, change later.
            medicineModel = realm.where(MedicineModel.class).
                    equalTo("medName", getIntent().getStringExtra("MedName")).findFirst();
            med_name.setText(medicineModel.getMedName());
            int selectedDayPhase = adapter_day_phase.getPosition(medicineModel.getDayPhase());
            day_phase.setSelection(selectedDayPhase);
            alarmSwitch.setChecked(medicineModel.isReminderOn());
            reminderTime.setText(medicineModel.getTime());

            timeFlag=false;

        } else {
            medicineModel = new MedicineModel();
            timeFlag=true;
            delete.setVisibility(View.INVISIBLE);
        }


        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewMedActivity.this);
                builder.setMessage("Delete this medication? This action is permanent.")
                        .setNegativeButton("Cancel",null).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //TODO Update data in server for medicine
                        alarmModel = realm.where(AlarmModel.class).equalTo("time", medicineModel.getTime()).findFirst();
                        if(alarmModel!=null){
                            System.out.println(">>>>>MED deleted, cancelling alarm");
                            Intent cancelAlarm = new Intent(NewMedActivity.this, AlarmReceiver.class);
                            cancelAlarm.putExtra("medications", alarmModel.getMedicines());
                            pendingIntent = PendingIntent.getBroadcast(NewMedActivity.this, (int) alarmModel.getAlarmID(), cancelAlarm, 0);
                            pendingIntent.cancel();
                            alarmManager.cancel(pendingIntent);
                        }

                        realm.beginTransaction();
                        medicineModel.deleteFromRealm();
                        realm.commitTransaction();

                        finish();
                        Intent openMedFrag = new Intent(NewMedActivity.this, HomeActivity.class);
                        openMedFrag.setAction("updateMeds");
                        startActivity(openMedFrag);
                        finishAffinity();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, hr);
                calendar.set(Calendar.MINUTE, min);

                if (calendar.before(Calendar.getInstance())) {
                    calendar.add(Calendar.DATE, 1);
                }

                if(med_name.getText().toString().matches("")){
                    Toast.makeText(NewMedActivity.this,"Name empty!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(timeFlag){
                    Toast.makeText(NewMedActivity.this,"Time not set!",Toast.LENGTH_SHORT).show();
                    return;
                }

                realm.beginTransaction();
                medicineModel.setMedName(med_name.getText().toString());
                medicineModel.setReminderOn(alarmSwitch.isChecked());
                medicineModel.setTime(reminderTime.getText().toString());
                medicineModel.setDayPhase(day_phase.getSelectedItem().toString());




                alarmModel = realm.where(AlarmModel.class).equalTo("time", medicineModel.getTime()).findFirst();
                System.out.println("TIME QUERY>>>>>>>>>>>>>" + medicineModel.getTime());
                if (alarmModel == null) // No alarm already set for this particular time
                {
                    alarmModel = new AlarmModel();
                    alarmModel.setTime(medicineModel.getTime());
                    alarmModel.setAlarmID();
                    System.out.println(">>>>ALARM ID" + alarmModel.getAlarmID());
                    alarmModel.setOn(medicineModel.isReminderOn());
                } else {
                    System.out.println("ALARM FOUND>>>>>>" + alarmModel.getTime());

                    Intent cancelAlarm = new Intent(NewMedActivity.this, AlarmReceiver.class);
                    cancelAlarm.putExtra("medications", alarmModel.getMedicines());
                    pendingIntent = PendingIntent.getBroadcast(NewMedActivity.this, (int) alarmModel.getAlarmID(), cancelAlarm, 0);
                    pendingIntent.cancel();
                    alarmManager.cancel(pendingIntent);
                    System.out.println(">>>>>>>>>>CANCELLED FOR" + alarmModel.getMedicines());
                }

                alarmModel.setOn(medicineModel.isReminderOn());
                if(alarmModel.isOn()){
                    alarmModel.addMedicineToAlarm(medicineModel.getMedName());
                }else{
                    alarmModel.removeMedicineFromAlarm(medicineModel.getMedName());
                }
                System.out.println(">>>>>>>>Meds in ALARM after adding/removing " + alarmModel.getMedicines());
                realm.insertOrUpdate(medicineModel);
                realm.insertOrUpdate(alarmModel);
                realm.commitTransaction();
                //TODO Update data in server for medicine

                if (alarmModel.isOn() || (!alarmModel.getMedicines().equalsIgnoreCase(""))) {
                    Intent openAlarm = new Intent(NewMedActivity.this, AlarmReceiver.class);
                    openAlarm.putExtra("medications", alarmModel.getMedicines());

                    pendingIntent = PendingIntent.getBroadcast(NewMedActivity.this, (int) alarmModel.getAlarmID(), openAlarm, 0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                    System.out.println(">>>>>>>>>>>SENT for " + alarmModel.getMedicines() + ">>>ID>>>" + alarmModel.getAlarmID());
                } else {
                    Intent cancelAlarm = new Intent(NewMedActivity.this, AlarmReceiver.class);
                    cancelAlarm.putExtra("medications", alarmModel.getMedicines());
                    pendingIntent = PendingIntent.getBroadcast(NewMedActivity.this, (int) alarmModel.getAlarmID(), cancelAlarm, 0);
                    pendingIntent.cancel();
                    alarmManager.cancel(pendingIntent);
                    System.out.println(">>>>>>>>>>CANCELLED FOR" + alarmModel.getMedicines());

                }

                Toast.makeText(NewMedActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                NewMedActivity.this.finish();

                //Finishing home activity from here, launch new intent with updateMeds action.
                Intent openMedFrag = new Intent(NewMedActivity.this, HomeActivity.class);
                openMedFrag.setAction("updateMeds");
                startActivity(openMedFrag);
                finish();
                setResult(RESULT_CANCELED);
                finishAffinity();
            }
        });
    }

    void bindViews() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMedActivity.this.finish();
            }
        });

        if (getIntent().getAction().equalsIgnoreCase("Edit")) {
            toolbar.setTitle("Edit Medication");
        } else {
            toolbar.setTitle("Add New Medication");
        }

        reminderTime = findViewById(R.id.time);
        med_name = findViewById(R.id.medName);
        day_phase = findViewById(R.id.spinner_day_phase);
        remCount=findViewById(R.id.spinner_med_count);
        alarmSwitch = findViewById(R.id.reminderSwitch);
        save = findViewById(R.id.done);
        delete=findViewById(R.id.delete);
        alarmSwitch.setTextOff("Reminder Off");
        alarmSwitch.setTextOn("Reminder On");
        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (alarmSwitch.isChecked()) {
                    alarmSwitch.setText("Reminder on");
                } else {
                    alarmSwitch.setText("Reminder off");
                }
            }
        });

        reminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                final int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(NewMedActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hr = selectedHour;
                        min = selectedMinute;

                        String sHour;
                        String sMin;
                        if (hr < 10) {
                            sHour = "0" + hr;
                        } else {
                            sHour = "" + hr;
                        }
                        if (min < 10) {
                            sMin = "0" + min;
                        } else {
                            sMin = "" + min;
                        }
                        reminderTime.setText(new StringBuilder(sHour + ":" + sMin));

                        timeFlag=false;
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

    }

}
