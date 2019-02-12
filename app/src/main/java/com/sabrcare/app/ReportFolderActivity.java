package com.sabrcare.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ReportFolderActivity extends AppCompatActivity {

    TextView folderName;
    RecyclerView imgList;
    RecyclerView pdfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_folder);
        folderName = findViewById(R.id.FolderTitle);
        imgList = findViewById(R.id.ImagesRV);
        pdfList = findViewById(R.id.pdfsRV);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this,3);
        imgList.setLayoutManager(gridLayoutManager);
        pdfList.setLayoutManager(gridLayoutManager2);

        ArrayList<String> imagefiles = new ArrayList<>();
        ArrayList<String> pdffiles = new ArrayList<>();

        pdffiles.add("File 1");
        pdffiles.add("File 2");
        pdffiles.add("File 3");
        pdffiles.add("File 4");
        pdffiles.add("File 5");

        imagefiles.add("Image 1");
        imagefiles.add("Image 2");
        imagefiles.add("Image 3");
        imagefiles.add("Image 4");
        imagefiles.add("Image 5");

        ImageRecordsAdapter adapter = new ImageRecordsAdapter(imagefiles,this);
        imgList.setAdapter(adapter);

        FileRecordsAdapter fileRecordsAdapter = new FileRecordsAdapter(pdffiles,this);
        pdfList.setAdapter(fileRecordsAdapter);

        folderName.setText(getIntent().getStringExtra("folderName"));
    }
}
