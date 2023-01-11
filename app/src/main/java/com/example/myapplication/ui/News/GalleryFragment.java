package com.example.myapplication.ui.News;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.ui.Kalender.Kalender;
import com.example.myapplication.ui.absensi.Absensi;
import com.example.myapplication.ui.nilai.Nilai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements BeritaAdapater.OnItemClickListener {
    public static final String EXTRA_JUDUL = "judulnews";
    public static final String EXTRA_ISI = "isi";

    private RecyclerView mRecycleview;
    private BeritaAdapater mBeritaAdapter;
    private ArrayList<Berita> mBeritaList;
    private RequestQueue mRequestQueue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);


        mRecycleview = rootview.findViewById(R.id.recyclerviewberita);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBeritaList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJSON();
        return rootview;
    }

    private void parseJSON() {
        String url = "http://coba.galariks.my.id/bukanAPI/Data_Berita.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String judulnews = hit.getString("Judul");
                                String tglterbit = hit.getString("Tanggal_Terbit");
                                String isi = hit.getString("Isi_Berita");


                                mBeritaList.add(new Berita(judulnews, tglterbit, isi));
                            }
                            mBeritaAdapter = new BeritaAdapater(getActivity(), mBeritaList);
                            mRecycleview.setAdapter(mBeritaAdapter);
                            mBeritaAdapter.setOnItemClickListener(GalleryFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {

//        showDialog();
        Intent intent = new Intent(getContext(), Dberita.class);

        Berita clickedItem = mBeritaList.get(position);

        intent.putExtra(EXTRA_JUDUL, clickedItem.getJudulnews());
        intent.putExtra(EXTRA_ISI, clickedItem.getIsi());
        Toast.makeText(getContext(), "Test Upload", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
}

//    private void showDialog() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                getActivity());
//
//        // set title dialog
//        alertDialogBuilder.setTitle("");
//
//        // set pesan dari dialog
//        alertDialogBuilder
//                .setMessage("Klik Ya untuk keluar!")
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // jika tombol diklik, maka akan menutup activity ini
//                        getActivity().finish();
//                    }
//                })
//                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // jika tombol ini diklik, akan menutup dialog
//                        // dan tidak terjadi apa2
//                        dialog.cancel();
//                    }
//                });
//
//        // membuat alert dialog dari builder
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // menampilkan alert dialog
//        alertDialog.show();
//    }
//}







