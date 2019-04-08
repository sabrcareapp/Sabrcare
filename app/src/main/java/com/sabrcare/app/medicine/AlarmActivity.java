package com.sabrcare.app.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sabrcare.app.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AlarmActivity extends AppCompatActivity {
    Button dismiss;
    MediaPlayer mediaPlayer;
    TextView medTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        dismiss = findViewById(R.id.dismissAlarm);
        medTV = findViewById(R.id.med);
        mediaPlayer = MediaPlayer.create(getBaseContext(), getAlarmUri());
        mediaPlayer.start();
        String med = getIntent().getStringExtra("medications");

        ArrayList<String> medList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(med, " ");
        while (st.hasMoreTokens()) {
            medList.add(st.nextToken());
        }
        String displayMed = "";
        for (int j = 0; j < medList.size(); j++) {
            displayMed += medList.get(j) + "\n";
        }
        System.out.println("MED IN ALARM ACTIVITY>>>>>>>>>>>>>>>>>>" + med);
        medTV.setText(new StringBuilder("Take your medicine - \n" + displayMed));
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                AlarmActivity.this.finish();
            }
        });
    }

    private Uri getAlarmUri() {
        //TODO Add vibrate if needed.
        return RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE);
    }
}

