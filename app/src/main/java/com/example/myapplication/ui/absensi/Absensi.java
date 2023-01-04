package com.example.myapplication.ui.absensi;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DbContact;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Absensi extends AppCompatActivity {
    private static final String TAG = "Absensi";
    private RecyclerView mRecycleview;
    private MatakuliahAdapter mMatakuliahAdapter;
    private ArrayList<Matakuliah> mMatakuliahList;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        setTitle("Absensi");

        sharedPreferences = getApplicationContext().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mRecycleview = findViewById(R.id.recyclerview);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));

        mMatakuliahList = new ArrayList<>();

        parseJSON(sharedPreferences.getString("NIM", ""));
    }

    private void parseJSON(final String NIM) {

        if (NIM.equals("fail"))
        {
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetAbsensi_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject hit = array.getJSONObject(i);

                                String namamatkul = hit.getString("Absensi_Matkul");
                                String totalabsensi = hit.getString("Kehadiran");

                                mMatakuliahList.add(new Matakuliah(namamatkul, totalabsensi));
                            }
                            mMatakuliahAdapter = new MatakuliahAdapter(Absensi.this, mMatakuliahList);
                            mRecycleview.setAdapter(mMatakuliahAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NIM", NIM);
                return params;
            }
        };

        Volley.newRequestQueue(Absensi.this).add(stringRequest);
    }
}