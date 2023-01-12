package com.example.myapplication.ui.Jadwal_Kuliah;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DbContact;
import com.example.myapplication.R;
import com.example.myapplication.ui.News.Berita;
import com.example.myapplication.ui.News.BeritaAdapater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JadwalKuliah extends Fragment {
    private RecyclerView mRecycleview;
    private JadwalAdapter mJadwalAdapter;
    private ArrayList<Jadwal> mJadwalList;
    private RequestQueue mRequestQueue;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_jadwal_kuliah, container, false);

        sharedPreferences = getActivity().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mRecycleview = rootview.findViewById(R.id.recyclerviewjadwal);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mJadwalList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
//        parseJSON(sharedPreferences.getString("", ""));
        parseJSON2(sharedPreferences.getString("NIM", ""));
        return rootview;
    }

    private void parseJSON2(String NIM) {

        if (NIM.equals("fail"))
        {
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetJadwal_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject hit = array.getJSONObject(i);

                                String matkul = hit.getString("Nama_Matakuliah");
                                String jadwal = hit.getString("Hari");
                                String kodematkul = hit.getString("Kode_Matakuliah");
                                String ruangan = hit.getString("Nama_Ruangan");
                                String jam = hit.getString("Jam");


                                mJadwalList.add(new Jadwal(matkul, jadwal, kodematkul, ruangan, jam));
                            }
                            mJadwalAdapter = new JadwalAdapter(getActivity(), mJadwalList);
                            mRecycleview.setAdapter(mJadwalAdapter);

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

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

//    private void parseJSON(String NIM) {
//        if (NIM.equals("fail"))
//        {
//            return;
//        }
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetJadwal_URL ,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray array = new JSONArray(response);
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject hit = array.getJSONObject(i);
//
//                                String matkul = hit.getString("Nama_Matakuliah");
//                                String jadwal = hit.getString("Hari");
//                                String kodematkul = hit.getString("SKS");
//                                String ruangan = hit.getString("Nama_Ruangan");
//
//                                mJadwalList.add(new Jadwal(matkul, jadwal, kodematkul, ruangan));
//                            }
//                            mJadwalAdapter = new JadwalAdapter(getActivity(), mJadwalList);
//                            mRecycleview.setAdapter(mJadwalAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("NIM", NIM);
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(getActivity()).add(stringRequest);
//    }
}