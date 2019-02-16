package com.sabrcare.app.activities;

import android.app.TimePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.sabrcare.app.R;


import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;



public class NewMedActivity extends AppCompatActivity {
        public Spinner day_phase;
        public EditText med_name;
        public EditText reminder;
        public EditText time;
        public TimePickerDialog timePickerDialog;
        public Button done;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_medication);


            day_phase=findViewById(R.id.spinner_day_phase);
            done=findViewById(R.id.done);
            time=findViewById(R.id.time);
            ArrayAdapter<CharSequence> adapter_day_phase = ArrayAdapter.createFromResource(NewMedActivity.this, R.array.Day_Phase,R.layout.support_simple_spinner_dropdown_item);
            adapter_day_phase.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            day_phase.setAdapter(adapter_day_phase);

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Use the current time as the default values for the picker
                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    // Create a new instance of TimePickerDialog
                    timePickerDialog = new TimePickerDialog(NewMedActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            time.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    timePickerDialog.setTitle("Select Time");
                    timePickerDialog.show();
                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Fragment fragment= new MedicineFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
            });

        }
}
