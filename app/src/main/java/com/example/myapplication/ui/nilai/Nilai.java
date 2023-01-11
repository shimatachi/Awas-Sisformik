package com.example.myapplication.ui.nilai;

import static com.example.myapplication.ui.News.GalleryFragment.EXTRA_JUDUL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Nilai extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listdataHeader;
    HashMap<String,List<String>> listdataChild;
    CustomExpandableListView customExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        addControl();
        setTitle("Nilai Mahasiswa");
        customExpandableListView = new CustomExpandableListView(Nilai.this,listdataHeader, listdataChild );
        expandableListView.setAdapter(customExpandableListView);
    }

    @SuppressLint("WrongViewCast")
    private void addControl() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String,List<String>> ();

        listdataHeader.add("Semester 5");
        listdataHeader.add("Semester 4");
        listdataHeader.add("Semester 3");
        listdataHeader.add("Semester 2");
        listdataHeader.add("Semester 1");

        List<String> semester5 = new ArrayList<>();
        semester5.add("Manajemen Proyek Sistem Informasi :A");
        semester5.add("Pengembangan Aplikasi Mobile : A");
        semester5.add("Metodologi Penelitian : A");
        semester5.add("Etika Profesi : A");
        semester5.add("Mobile Game Developer : A");
        semester5.add("Evaluasi & Audit SI : A");
        semester5.add("Sistem Cerdas : A");
        semester5.add("Pemrograman Web : A");

        List<String> semester4 = new ArrayList<>();
        semester4.add("Manajemen Proyek Sistem Informasi : A");
        semester4.add("Pengembangan Aplikasi Mobile : A");
        semester4.add("Metodologi Penelitian : A");

        List<String> semester3 = new ArrayList<>();
        semester3.add("Manajemen Proyek Sistem Informasi : A");
        semester3.add("Pengembangan Aplikasi Mobile : A");
        semester3.add("Metodologi Penelitian : A");

        List<String> semester2 = new ArrayList<>();
        semester2.add("Manajemen Proyek Sistem Informasi : A");
        semester2.add("Pengembangan Aplikasi Mobile : A");
        semester2.add("Metodologi Penelitian : A");

        List<String> semester1 = new ArrayList<>();
        semester1.add("Manajemen Proyek Sistem Informasi : A");
        semester1.add("Pengembangan Aplikasi Mobile : A");
        semester1.add("Metodologi Penelitian : A");

        listdataChild.put(listdataHeader.get(0),semester5);
        listdataChild.put(listdataHeader.get(1),semester4);
        listdataChild.put(listdataHeader.get(2),semester3);
        listdataChild.put(listdataHeader.get(3),semester2);
        listdataChild.put(listdataHeader.get(4),semester1);

    }
}
