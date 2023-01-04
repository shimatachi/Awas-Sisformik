package com.example.myapplication.ui.Pembayaran;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DbContact;
import com.example.myapplication.R;
import com.example.myapplication.ui.Kst.Kst;
import com.example.myapplication.ui.Kst.KstAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Pembayaran extends Fragment {
    private RecyclerView mRecycleview;
    private PembayaranAdapter mPembayaranAdapter;
    private ArrayList<PembayaranList> mPembayaranList;
    private RequestQueue mRequestQueue;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_pembayaran, container, false);

        sharedPreferences = getActivity().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mRecycleview = rootview.findViewById(R.id.recyclerviewpembayaran);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPembayaranList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON(sharedPreferences.getString("NIM", ""));
        return rootview;
    }

    private void parseJSON(String NIM) {
        if (NIM.equals("fail"))
        {
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetPembayaran_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject hit = array.getJSONObject(i);

                                String semester = hit.getString("Pembayaran_Semester");
                                String upload = hit.getString("Bukti_Pembayaran");
                                String belumterverifikasi = hit.getString("Status");
                                String terverifikasi = hit.getString("Verifikasi");

                                mPembayaranList.add(new PembayaranList(semester, upload, belumterverifikasi, terverifikasi));
                            }
                            mPembayaranAdapter = new PembayaranAdapter(getActivity(), mPembayaranList);
                            mRecycleview.setAdapter(mPembayaranAdapter);

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
}