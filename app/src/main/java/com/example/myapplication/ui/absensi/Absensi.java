package com.example.myapplication.ui.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Absensi extends AppCompatActivity {
    private static final String TAG = "Absensi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        setTitle("Absensi");

        Log.d(TAG, "onCreate: Started");
        ListView mListView = (ListView) findViewById(R.id.listview);

        Matakuliah Manajemen_Proyek_Sistem_Informasi =  new Matakuliah("Manajemen Proyek SIstem Informasi","Total","4");
        Matakuliah Pengembangan_Aplikasi_Mobile =  new Matakuliah("Pengembangan Aplikasi Mobile","Total","4");
        Matakuliah Metodologi_Penelitian =  new Matakuliah("Metodologi Penelitian","Total","4");
        Matakuliah Etika_Profesi =  new Matakuliah("Etika Profesi","Total","4");
        Matakuliah Mobile_Game_Developer =  new Matakuliah("Mobile Game Developer","Total","4");
        Matakuliah Evaluasi_dan_Audit_SI =  new Matakuliah("Evaluasi & Audit SI","Total","4");
        Matakuliah Sistem_Cerdas =  new Matakuliah("Sistem Cerdas","Total","4");
        Matakuliah Pemrograman_Web =  new Matakuliah("Pemrograman Web","Total","4");

        ArrayList<Matakuliah> matakuliahList = new ArrayList<>();
        matakuliahList.add(Manajemen_Proyek_Sistem_Informasi);
        matakuliahList.add(Pengembangan_Aplikasi_Mobile);
        matakuliahList.add(Metodologi_Penelitian);
        matakuliahList.add(Etika_Profesi);
        matakuliahList.add(Mobile_Game_Developer);
        matakuliahList.add(Evaluasi_dan_Audit_SI);
        matakuliahList.add(Sistem_Cerdas);
        matakuliahList.add(Pemrograman_Web);

        MatakuliahListAdapter adapter = new MatakuliahListAdapter(this, R.layout.adapter_view_layout, matakuliahList);
        mListView.setAdapter(adapter);
    }
}