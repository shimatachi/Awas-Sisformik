package com.example.myapplication.ui.Kst;

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
import com.example.myapplication.ui.absensi.Absensi;
import com.example.myapplication.ui.absensi.Matakuliah;
import com.example.myapplication.ui.absensi.MatakuliahAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class nav_kst extends Fragment {
    private RecyclerView mRecycleview;
    private KstAdapter mKstAdapter;
    private ArrayList<Kst> mKstList;
    private RequestQueue mRequestQueue;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);

        sharedPreferences = getActivity().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mRecycleview = rootview.findViewById(R.id.recyclerviewberita);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mKstList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON(sharedPreferences.getString("NIM", ""));
        return rootview;
    }

    private void parseJSON(String NIM) {
        if (NIM.equals("fail"))
        {
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetKST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject hit = array.getJSONObject(i);

                                String matkul = hit.getString("KST_Matkul");
                                String sks = hit.getString("SKS");

                                mKstList.add(new Kst(matkul, sks));
                            }
                            mKstAdapter = new KstAdapter(getActivity(), mKstList);
                            mRecycleview.setAdapter(mKstAdapter);

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