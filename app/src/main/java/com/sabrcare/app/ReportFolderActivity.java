package com.sabrcare.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ReturnMode;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class ReportFolderActivity extends AppCompatActivity {

    RecyclerView imgList;
    RecyclerView pdfList;

    boolean showUploadDialog;
    int imgFlag;

    FloatingActionButton photoFab,uploadFileFab;
    FloatingActionMenu fabMenu;

    String filePath;

    private static final int RC_CAMERA_STORAGE = 123;
    private static final int RC_READ_STORAGE = 120;
    private static final int PICK_IMAGE = 100;
    private static final int PICK_PDF = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_folder);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("folderName"));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportFolderActivity.this.finish();
            }
        });

        imgList = findViewById(R.id.ImagesRV);
        pdfList = findViewById(R.id.pdfsRV);
        photoFab = findViewById(R.id.TakePhoto);
        uploadFileFab = findViewById(R.id.UploadFile);
        fabMenu = findViewById(R.id.material_design_android_floating_action_menu2);


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

        final String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        photoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            @AfterPermissionGranted(RC_CAMERA_STORAGE)
            public void onClick(View view) {
                if(EasyPermissions.hasPermissions(ReportFolderActivity.this,perms)){
                    com.esafirm.imagepicker.features.ImagePicker.create(ReportFolderActivity.this)
                            .folderMode(true)
                            .toolbarArrowColor(Color.parseColor("#FFFFFF"))
                            .limit(1)
                            .returnMode(ReturnMode.CAMERA_ONLY)
                            .toolbarFolderTitle("Select Image to upload")
                            .showCamera(true)
                            .single()
                            .imageDirectory("SabrCare Records")
                            .theme(R.style.AppThemeNoActionBar)
                            .start(PICK_IMAGE);


                }else {
                    EasyPermissions.requestPermissions(new PermissionRequest.Builder(ReportFolderActivity.this,RC_CAMERA_STORAGE,perms)
                            .setRationale(R.string.rationale_camera_storage)
                            .build());
                }
            }
        });

        uploadFileFab.setOnClickListener(new View.OnClickListener() {
            @Override
            @AfterPermissionGranted(RC_READ_STORAGE)
            public void onClick(View view) {
                if(EasyPermissions.hasPermissions(ReportFolderActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    fileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    fileIntent.setType("application/pdf");
                    startActivityForResult(Intent.createChooser(fileIntent,"Select PDF"),PICK_PDF);

                } else {
                    EasyPermissions.requestPermissions(new PermissionRequest.Builder(ReportFolderActivity.this,RC_READ_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            .setRationale(R.string.rationale_read_storage)
                            .build());
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        fabMenu.close(false);

        if(requestCode==PICK_IMAGE && resultCode == RESULT_OK && data != null){
            com.esafirm.imagepicker.model.Image image = com.esafirm.imagepicker.features.ImagePicker.getFirstImageOrNull(data);
            filePath = image.getPath();
            showUploadDialog = true;
            imgFlag=1;
        }

        else if(requestCode==PICK_PDF&& resultCode == RESULT_OK) {
            filePath = PathUtil.getPath(this,data.getData());
            showUploadDialog = true;
            imgFlag=0;
        }
        else {
            Toast.makeText(getApplicationContext(),"Error loading file. Please try again",Toast.LENGTH_SHORT).show();
            showUploadDialog=false;
            imgFlag=0;
        }

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

        if(showUploadDialog && imgFlag==0){
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            DialogFragment uploadDialog = UploadDialogFragment.newInstance(filePath);
            ((UploadDialogFragment) uploadDialog).filepath=filePath;
            ft.addToBackStack("Upload Fragment Dialog");

            uploadDialog.show(ft,"");

            showUploadDialog = false;
        }
        else if(showUploadDialog && imgFlag==1){
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();

            DialogFragment uploadDialog = UploadDialogFragment.newInstance(filePath);
            ((UploadDialogFragment) uploadDialog).filepath=filePath;
            ft.addToBackStack("Upload Fragment Dialog");

            uploadDialog.show(ft,"");

            showUploadDialog = false;
        }
    }
}
