package com.sabrcare.app.records;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionMenu;
import com.sabrcare.app.HomeActivity;
import com.sabrcare.app.R;
import com.sabrcare.app.activities.ProfileActivity;
import com.sabrcare.app.auth.SignInActivity;
import com.sabrcare.app.auth.SignUpActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.sabrcare.app.HomeActivity.FILE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {

    private ArrayList<String> records;
    private RecordsAdapter recordsAdapter;

    //boolean flag to know if main FAB is in open or closed state.
    private boolean fabExpanded = false;
    com.github.clans.fab.FloatingActionButton fabNewFolder;
    RecyclerView recyclerView;
    FloatingActionMenu materialDesignFAM;

    private RequestQueue listFolders;

    Map<String, String> requestHeaders = new ArrayMap<>();
    Map<String, String> addFolders = new ArrayMap<>();

    SharedPreferences setting;

    String token=null;

    Button profile;


    public RecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_records, container, false);
        recyclerView = view.findViewById(R.id.RecordsRV);
        materialDesignFAM = view.findViewById(R.id.material_design_android_floating_action_menu);
        fabNewFolder = view.findViewById(R.id.NewFolderFAB);
        profile = view.findViewById(R.id.profile);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loadprofile = new Intent(getContext(),ProfileActivity.class);
                getContext().startActivity(loadprofile);

            }
        });



        setting= getActivity().getSharedPreferences(FILE,MODE_PRIVATE);
        token = setting.getString("Token","null");

        if(token.equals("null"))
        {
            Intent launchHome = new Intent(getActivity(),SignInActivity.class);
            startActivity(launchHome);
            getActivity().finishAffinity();
            return view;
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        records = new ArrayList<>();
        loadFolders();

        Toolbar records_toolbar = view.findViewById(R.id.records_toolbar);
        records_toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(records_toolbar);
        fabNewFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog newFolderDialog = new Dialog(getContext());
                newFolderDialog.setContentView(R.layout.fragment_new_folder);
                newFolderDialog.setCancelable(true);
                final EditText nameField = newFolderDialog.findViewById(R.id.foldername);
                Button add = newFolderDialog.findViewById(R.id.add);
                Button cancel = newFolderDialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newFolderDialog.dismiss();
                        materialDesignFAM.close(false);
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String foldername = nameField.getText().toString();
                        if(foldername.isEmpty()||foldername.matches(""))
                        {
                            Toast.makeText(getContext(),"Enter valid folder name",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        System.out.println(">>>>>>>>>>.foldername>>>" + foldername);
                        addFolder(foldername);
                        newFolderDialog.dismiss();
                        materialDesignFAM.close(false);
                    }
                });
                newFolderDialog.show();
            }
        });

        return view;
    }


    private void addFolder(String folderName) {
        String url = getResources().getString(R.string.apiUrl);
        url += "records/add/folders";
        listFolders = Volley.newRequestQueue(getContext());
        addFolders.put("token", token);
        addFolders.put("folderName", folderName);
        StringRequest addFolder = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadFolders();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("eyeopener", error.toString());
                Toast.makeText(getContext(), "AddingFolderFailed", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return addFolders;
            }
        };
        listFolders.add(addFolder);
    }

    private void loadFolders() {
        String url = getResources().getString(R.string.apiUrl);
        url += "records/show/folders";
        //TODO Handle Token

        requestHeaders.put("token", token);
        //requestHeaders.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiaGFyaS4yNTk5QGdtYWlsLmNvLmluIiwiZXhwIjoxNTU0Mjk4OTUyfQ.qy7W-tdcSVGrEoZrNialM4VFURvX3UJ9o6Ifde5HN6s");
        listFolders = Volley.newRequestQueue(getContext());
        StringRequest getFolders = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String resp = new JSONObject(response).getString("data");
                    System.out.println(">>>>>>JSON>>>>>>>>>>>>>>>>>>" + resp);
                    JSONArray jsonArray = new JSONArray(resp);
                    System.out.println(">>>>>>>>>>>LENGTH>>>" + jsonArray.length());
                    records = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        records.add(jsonArray.getJSONObject(i).getJSONObject("folders").getString("folderName"));
                        System.out.println(">>>>>>>>>>>folderName>>>" + records.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recordsAdapter = new RecordsAdapter(records, getContext());
                recyclerView.setAdapter(recordsAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LoadError", error.toString());
                Toast.makeText(getContext(), "Load Folders error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return requestHeaders;
            }
        };
        listFolders.add(getFolders);
    }

}
