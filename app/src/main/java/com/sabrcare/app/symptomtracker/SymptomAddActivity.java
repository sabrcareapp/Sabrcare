package com.sabrcare.app.symptomtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sabrcare.app.R;
import com.sabrcare.app.HomeActivity;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

public class SymptomAddActivity extends AppCompatActivity {

    public static ArrayList<ModelSymptom> symptoms = new ArrayList<>(0);
    public static int flagIntent=0;
    public int flag=0,flag1=0,flag2=0,flag3=0,flag4=0,flag5=0,flag6=0,flag7=0,flag8=0,flag9=0,flag10=0,flag11=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_add);

        TextView sytaTv = findViewById(R.id.syta_tv),
                sytaTv1 = findViewById(R.id.syta_tv1),
                sytaTv2 = findViewById(R.id.syta_tv2),
                sytaTv3 = findViewById(R.id.syta_tv3),
                sytaTv4 = findViewById(R.id.syta_tv4),
                sytaTv5 = findViewById(R.id.syta_tv5),
                sytaTv6 = findViewById(R.id.syta_tv6),
                sytaTv7 = findViewById(R.id.syta_tv7),
                sytaTv8 = findViewById(R.id.syta_tv8),
                sytaTv9 = findViewById(R.id.syta_tv9),
                sytaTv10 = findViewById(R.id.syta_tv10),
                sytaTv11 = findViewById(R.id.syta_tv11);

        final RecyclerView sytaRv = findViewById(R.id.syta_rv),
                sytaRv1=findViewById(R.id.syta_rv1),
                sytaRv2=findViewById(R.id.syta_rv2),
                sytaRv3=findViewById(R.id.syta_rv3),
                sytaRv4=findViewById(R.id.syta_rv4),
                sytaRv5=findViewById(R.id.syta_rv5),
                sytaRv6=findViewById(R.id.syta_rv6),
                sytaRv7=findViewById(R.id.syta_rv7),
                sytaRv8=findViewById(R.id.syta_rv8),
                sytaRv9=findViewById(R.id.syta_rv9),
                sytaRv10=findViewById(R.id.syta_rv10),
                sytaRv11=findViewById(R.id.syta_rv11);
        Button sytaBtn = findViewById(R.id.syta_btn);

        Realm.init(SymptomAddActivity.this);
        final Realm db = Realm.getDefaultInstance();


        if(symptoms.size()==0 && db.where(ModelSymptom.class).count() == 0) {
            ModelSymptom modelSymptom = new ModelSymptom(
                    "CONSTIPATION", 0, "null");
            symptoms.add(modelSymptom);
            ModelSymptom modelSymptom1 = new ModelSymptom(
                    "DIARRHEA", 0, "null");
            symptoms.add(modelSymptom1);
            ModelSymptom modelSymptom2 = new ModelSymptom(
                    "DIZZINESS", 0, "null");
            symptoms.add(modelSymptom2);
            ModelSymptom modelSymptom3 = new ModelSymptom(
                    "FATIGUE", 0, "null");
            symptoms.add(modelSymptom3);
            ModelSymptom modelSymptom4 = new ModelSymptom(
                    "NAUSEA", 0, "null");
            symptoms.add(modelSymptom4);
            ModelSymptom modelSymptom5 = new ModelSymptom(
                    "ANXIETY", 0, "null");
            symptoms.add(modelSymptom5);
            ModelSymptom modelSymptom6 = new ModelSymptom(
                    "HEADACHE", 0, "null");
            symptoms.add(modelSymptom6);
            ModelSymptom modelSymptom7 = new ModelSymptom(
                    "COUGHING", 0, "null");
            symptoms.add(modelSymptom7);
            ModelSymptom modelSymptom8 = new ModelSymptom(
                    "VOMITING", 0, "null");
            symptoms.add(modelSymptom8);
            ModelSymptom modelSymptom9 = new ModelSymptom(
                    "RACING HEART", 0, "null");
            symptoms.add(modelSymptom9);
            ModelSymptom modelSymptom10 = new ModelSymptom(
                    "SWELLING", 0, "null");
            symptoms.add(modelSymptom10);
            ModelSymptom modelSymptom11 = new ModelSymptom(
                    "BLOATING", 0, "null");
            symptoms.add(modelSymptom11);
            ModelSymptom modelSymptom12 = new ModelSymptom(
                    "GAS", 0, "null");
            symptoms.add(modelSymptom12);
            ModelSymptom modelSymptom13 = new ModelSymptom(
                    "DECREASED APPETITE", 0, "null");
            symptoms.add(modelSymptom13);
            ModelSymptom modelSymptom14 = new ModelSymptom(
                    "HEARTBURN", 0, "null");
            symptoms.add(modelSymptom14);
            ModelSymptom modelSymptom15 = new ModelSymptom(
                    "HICCUPS", 0, "null");
            symptoms.add(modelSymptom15);
            ModelSymptom modelSymptom16 = new ModelSymptom(
                    "NO BOWEL CONTROL", 0, "null");
            symptoms.add(modelSymptom16);
            ModelSymptom modelSymptom17 = new ModelSymptom(
                    "TASTE CHANGES", 0, "null");
            symptoms.add(modelSymptom17);
            ModelSymptom modelSymptom18 = new ModelSymptom(
                    "CONCENTRATION DIFFICULTY", 0, "null");
            symptoms.add(modelSymptom18);
            ModelSymptom modelSymptom19 = new ModelSymptom(
                    "DISCOURAGED MOOD", 0, "null");
            symptoms.add(modelSymptom19);
            ModelSymptom modelSymptom20 = new ModelSymptom(
                    "MEMORY LOSS", 0, "null");
            symptoms.add(modelSymptom20);
            ModelSymptom modelSymptom21 = new ModelSymptom(
                    "SAD MOOD", 0, "null");
            symptoms.add(modelSymptom21);
            ModelSymptom modelSymptom22 = new ModelSymptom(
                    "DRY MOUTH", 0, "null");
            symptoms.add(modelSymptom22);
            ModelSymptom modelSymptom23 = new ModelSymptom(
                    "HOARSE VOICE", 0, "null");
            symptoms.add(modelSymptom23);
            ModelSymptom modelSymptom24 = new ModelSymptom(
                    "MOUTH CORNER CRACKING", 0, "null");
            symptoms.add(modelSymptom24);
            ModelSymptom modelSymptom25 = new ModelSymptom(
                    "VOICE CHANGES", 0, "null");
            symptoms.add(modelSymptom25);
            ModelSymptom modelSymptom26 = new ModelSymptom(
                    "SWALLOWING DIFFICULTY", 0, "null");
            symptoms.add(modelSymptom26);
            ModelSymptom modelSymptom27 = new ModelSymptom(
                    "MOUTH/THROAT SORES", 0, "null");
            symptoms.add(modelSymptom27);
            ModelSymptom modelSymptom28 = new ModelSymptom(
                    "PAIN (GENERAL)", 0, "null");
            symptoms.add(modelSymptom28);
            ModelSymptom modelSymptom29 = new ModelSymptom(
                    "ABDOMINAL/BELLY PAIN", 0, "null");
            symptoms.add(modelSymptom29);
            ModelSymptom modelSymptom30 = new ModelSymptom(
                    "JOINT PAIN", 0, "null");
            symptoms.add(modelSymptom30);
            ModelSymptom modelSymptom31 = new ModelSymptom(
                    "MUSCLE PAIN", 0, "null");
            symptoms.add(modelSymptom31);
            ModelSymptom modelSymptom32 = new ModelSymptom(
                    "SHORTNESS OF BREATH", 0, "null");
            symptoms.add(modelSymptom32);
            ModelSymptom modelSymptom33 = new ModelSymptom(
                    "WHEEZING", 0, "null");
            symptoms.add(modelSymptom33);
            ModelSymptom modelSymptom34 = new ModelSymptom(
                    "FREQUENT URINATION", 0, "null");
            symptoms.add(modelSymptom34);
            ModelSymptom modelSymptom35 = new ModelSymptom(
                    "IRREGULAR PERIOD", 0, "null");
            symptoms.add(modelSymptom35);
            ModelSymptom modelSymptom36 = new ModelSymptom(
                    "LOSS OF URINATION CONTROL", 0, "null");
            symptoms.add(modelSymptom36);
            ModelSymptom modelSymptom37 = new ModelSymptom(
                    "MISSED MENSURATION", 0, "null");
            symptoms.add(modelSymptom37);
            ModelSymptom modelSymptom38 = new ModelSymptom(
                    "PAINFUL URINATION", 0, "null");
            symptoms.add(modelSymptom38);
            ModelSymptom modelSymptom39 = new ModelSymptom(
                    "URGESS TO UINATE", 0, "null");
            symptoms.add(modelSymptom39);
            ModelSymptom modelSymptom40 = new ModelSymptom(
                    "URINE COLOR CHANGES", 0, "null");
            symptoms.add(modelSymptom40);
            ModelSymptom modelSymptom41 = new ModelSymptom(
                    "VAGINAL BLEEDING", 0, "null");
            symptoms.add(modelSymptom41);
            ModelSymptom modelSymptom42 = new ModelSymptom(
                    "VAGINAL DISCHARGE", 0, "null");
            symptoms.add(modelSymptom42);
            ModelSymptom modelSymptom43 = new ModelSymptom(
                    "VAGINAL DRYNESS", 0, "null");
            symptoms.add(modelSymptom43);
            ModelSymptom modelSymptom44 = new ModelSymptom(
                    "ACNE", 0, "null");
            symptoms.add(modelSymptom44);
            ModelSymptom modelSymptom45 = new ModelSymptom(
                    "BED SORES", 0, "null");
            symptoms.add(modelSymptom45);
            ModelSymptom modelSymptom46 = new ModelSymptom(
                    "HAIR LOSS", 0, "null");
            symptoms.add(modelSymptom46);
            ModelSymptom modelSymptom47 = new ModelSymptom(
                    "HAND FOOT SYNDROME", 0, "null");
            symptoms.add(modelSymptom47);
            ModelSymptom modelSymptom48 = new ModelSymptom(
                    "HIVES", 0, "null");
            symptoms.add(modelSymptom48);
            ModelSymptom modelSymptom49 = new ModelSymptom(
                    "NAIL DISCOLORATION", 0, "null");
            symptoms.add(modelSymptom49);
            ModelSymptom modelSymptom50 = new ModelSymptom(
                    "NAIL LOSS", 0, "null");
            symptoms.add(modelSymptom50);
            ModelSymptom modelSymptom51 = new ModelSymptom(
                    "NAIL RIDGING", 0, "null");
            symptoms.add(modelSymptom51);
            ModelSymptom modelSymptom52 = new ModelSymptom(
                    "RASH", 0, "null");
            symptoms.add(modelSymptom52);
            ModelSymptom modelSymptom53 = new ModelSymptom(
                    "SKIN BURNS", 0, "null");
            symptoms.add(modelSymptom53);
            ModelSymptom modelSymptom54 = new ModelSymptom(
                    "STRETCH MARKS", 0, "null");
            symptoms.add(modelSymptom54);
            ModelSymptom modelSymptom55 = new ModelSymptom(
                    "SUNLIGHT SENSITIVITY", 0, "null");
            symptoms.add(modelSymptom55);
            ModelSymptom modelSymptom56 = new ModelSymptom(
                    "SKIN DARKENING", 0, "null");
            symptoms.add(modelSymptom56);
            ModelSymptom modelSymptom57 = new ModelSymptom(
                    "SKIN DRYNESS", 0, "null");
            symptoms.add(modelSymptom57);
            ModelSymptom modelSymptom58 = new ModelSymptom(
                    "DECREASED SEXUAL INTEREST", 0, "null");
            symptoms.add(modelSymptom58);
            ModelSymptom modelSymptom59 = new ModelSymptom(
                    "DELAYED ORGASM", 0, "null");
            symptoms.add(modelSymptom59);
            ModelSymptom modelSymptom60 = new ModelSymptom(
                    "EJACULATION PROBLEMS", 0, "null");
            symptoms.add(modelSymptom60);
            ModelSymptom modelSymptom61 = new ModelSymptom(
                    "ERECTION DIFFICULTY", 0, "null");
            symptoms.add(modelSymptom61);
            ModelSymptom modelSymptom62 = new ModelSymptom(
                    "INTERCOURSE PAIN", 0, "null");
            symptoms.add(modelSymptom62);
            ModelSymptom modelSymptom63 = new ModelSymptom(
                    "UNABLE TO HAVE ORGASM", 0, "null");
            symptoms.add(modelSymptom63);
            ModelSymptom modelSymptom64 = new ModelSymptom(
                    "BLURRED VISION", 0, "null");
            symptoms.add(modelSymptom64);
            ModelSymptom modelSymptom65 = new ModelSymptom(
                    "FLASHING LIGHTS", 0, "null");
            symptoms.add(modelSymptom65);
            ModelSymptom modelSymptom66 = new ModelSymptom(
                    "RINGING IN EARS", 0, "null");
            symptoms.add(modelSymptom66);
            ModelSymptom modelSymptom67 = new ModelSymptom(
                    "VISUAL FLOATERS", 0, "null");
            symptoms.add(modelSymptom67);
            ModelSymptom modelSymptom68 = new ModelSymptom(
                    "WATERY EYES", 0, "null");
            symptoms.add(modelSymptom68);
            ModelSymptom modelSymptom69 = new ModelSymptom(
                    "BODY ODOUR", 0, "null");
            symptoms.add(modelSymptom69);
            ModelSymptom modelSymptom70 = new ModelSymptom(
                    "BREAST SWELLING/TENDERNESS", 0, "null");
            symptoms.add(modelSymptom70);
            ModelSymptom modelSymptom71 = new ModelSymptom(
                    "BRUISING", 0, "null");
            symptoms.add(modelSymptom71);
            ModelSymptom modelSymptom72 = new ModelSymptom(
                    "DECREASED SWEATING", 0, "null");
            symptoms.add(modelSymptom72);
            ModelSymptom modelSymptom73 = new ModelSymptom(
                    "EXCESSIVE SWEATING", 0, "null");
            symptoms.add(modelSymptom73);
        }

        sytaRv.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv1.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv2.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv3.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv4.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv5.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv6.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv7.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv8.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv9.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv10.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        sytaRv11.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        AddSymptomAdapter symptomsAdapter = new AddSymptomAdapter("sytaRv");
        AddSymptomAdapter symptomsAdapter1 = new AddSymptomAdapter("sytaRv1");
        AddSymptomAdapter symptomsAdapter2 = new AddSymptomAdapter("sytaRv2");
        AddSymptomAdapter symptomsAdapter3 = new AddSymptomAdapter("sytaRv3");
        AddSymptomAdapter symptomsAdapter4 = new AddSymptomAdapter("sytaRv4");
        AddSymptomAdapter symptomsAdapter5 = new AddSymptomAdapter("sytaRv5");
        AddSymptomAdapter symptomsAdapter6 = new AddSymptomAdapter("sytaRv6");
        AddSymptomAdapter symptomsAdapter7 = new AddSymptomAdapter("sytaRv7");
        AddSymptomAdapter symptomsAdapter8 = new AddSymptomAdapter("sytaRv8");
        AddSymptomAdapter symptomsAdapter9 = new AddSymptomAdapter("sytaRv9");
        AddSymptomAdapter symptomsAdapter10 = new AddSymptomAdapter("sytaRv10");
        AddSymptomAdapter symptomsAdapter11 = new AddSymptomAdapter("sytaRv11");

        sytaRv.setAdapter(symptomsAdapter);
        sytaRv1.setAdapter(symptomsAdapter1);
        sytaRv2.setAdapter(symptomsAdapter2);
        sytaRv3.setAdapter(symptomsAdapter3);
        sytaRv4.setAdapter(symptomsAdapter4);
        sytaRv5.setAdapter(symptomsAdapter5);
        sytaRv6.setAdapter(symptomsAdapter6);
        sytaRv7.setAdapter(symptomsAdapter7);
        sytaRv8.setAdapter(symptomsAdapter8);
        sytaRv9.setAdapter(symptomsAdapter9);
        sytaRv10.setAdapter(symptomsAdapter10);
        sytaRv11.setAdapter(symptomsAdapter11);

        sytaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==1) {
                    sytaRv.setVisibility(View.GONE);
                    flag=0;
                }
                else{
                    sytaRv.setVisibility(View.VISIBLE);
                    flag=1;
                }
            }
        });

        sytaTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1==1) {
                    sytaRv1.setVisibility(View.GONE);
                    flag1=0;
                }
                else{
                    sytaRv1.setVisibility(View.VISIBLE);
                    flag1=1;
                }
            }
        });

        sytaTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag2==1) {
                    sytaRv2.setVisibility(View.GONE);
                    flag2=0;
                }
                else{
                    sytaRv2.setVisibility(View.VISIBLE);
                    flag2=1;
                }
            }
        });

        sytaTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag3==1) {
                    sytaRv3.setVisibility(View.GONE);
                    flag3=0;
                }
                else{
                    sytaRv3.setVisibility(View.VISIBLE);
                    flag3=1;
                }
            }
        });

        sytaTv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag4==1) {
                    sytaRv4.setVisibility(View.GONE);
                    flag4=0;
                }
                else{
                    sytaRv4.setVisibility(View.VISIBLE);
                    flag4=1;
                }
            }
        });

        sytaTv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag5==1) {
                    sytaRv5.setVisibility(View.GONE);
                    flag5=0;
                }
                else{
                    sytaRv5.setVisibility(View.VISIBLE);
                    flag5=1;
                }
            }
        });

        sytaTv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag6==1) {
                    sytaRv6.setVisibility(View.GONE);
                    flag6=0;
                }
                else{
                    sytaRv6.setVisibility(View.VISIBLE);
                    flag6=1;
                }
            }
        });

        sytaTv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag7==1) {
                    sytaRv7.setVisibility(View.GONE);
                    flag7=0;
                }
                else{
                    sytaRv7.setVisibility(View.VISIBLE);
                    flag7=1;
                }
            }
        });

        sytaTv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag8==1) {
                    sytaRv8.setVisibility(View.GONE);
                    flag8=0;
                }
                else{
                    sytaRv8.setVisibility(View.VISIBLE);
                    flag8=1;
                }
            }
        });

        sytaTv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag9==1) {
                    sytaRv9.setVisibility(View.GONE);
                    flag9=0;
                }
                else{
                    sytaRv9.setVisibility(View.VISIBLE);
                    flag9=1;
                }
            }
        });

        sytaTv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag10==1) {
                    sytaRv10.setVisibility(View.GONE);
                    flag10=0;
                }
                else{
                    sytaRv10.setVisibility(View.VISIBLE);
                    flag10=1;
                }
            }
        });

        sytaTv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag11==1) {
                    sytaRv11.setVisibility(View.GONE);
                    flag11=0;
                }
                else{
                    sytaRv11.setVisibility(View.VISIBLE);
                    flag11=1;
                }
            }
        });



        sytaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagIntent=1;

                for(int i=0;i<74;i++) {
                    final int finalI = i;
                    db.executeTransaction(
                            new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    db.insertOrUpdate(symptoms.get(finalI));
                                }
                            }
                    );
                }
                Intent intent = new Intent(SymptomAddActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
