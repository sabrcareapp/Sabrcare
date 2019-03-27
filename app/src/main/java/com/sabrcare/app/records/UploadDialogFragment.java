package com.sabrcare.app.records;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onecode.s3.callback.S3Callback;
import com.onecode.s3.model.S3Credentials;
import com.sabrcare.app.R;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDialogFragment extends DialogFragment {

    public String filepath;
    private RequestQueue requestQueue;
    Map<String,String> recordsMap = new ArrayMap<>();
    static String filename;


    public static UploadDialogFragment newInstance(String filepath){
        UploadDialogFragment uploadDialogFragment = new UploadDialogFragment();
        // NOTE: args in bundle not referred to, filepath is set in ReportFolderActivity. Remove if needed.
        Bundle args = new Bundle();
        args.putString("filePath",filepath);
        uploadDialogFragment.setArguments(args);
        return uploadDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_upload_dialog,null);
        TextView fileName = view.findViewById(R.id.fileName);
        Button cancel = view.findViewById(R.id.cancelbtn);
        Button upload = view.findViewById(R.id.uploadbtn);
        String str2 = filepath.substring(filepath.lastIndexOf('/')+1);
        filename=str2;
        fileName.setText(str2);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                //TODO: Rename files
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return view;
    }

    void uploadFile(){
        String finalUrl="https://i.pinimg.com/236x/c3/d1/18/c3d118cef16ca8c2c5161d70504859e3.jpg";
        String uploadUrl = getResources().getString(R.string.apiUrl) + "records/add/files";

        recordsMap.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
        recordsMap.put("folderName",ReportFolderActivity.folderName);
        if(ReportFolderActivity.isImage){
            recordsMap.put("fileType","Image");
        } else {
            recordsMap.put("fileType","PDF");
        }
        recordsMap.put("fileName",filename);
        recordsMap.put("fileURL",finalUrl);

        requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
          @Override
          public Map<String,String> getHeaders(){
              return recordsMap;
          }
        };
        requestQueue.add(stringRequest);

    }

    //("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
        }
