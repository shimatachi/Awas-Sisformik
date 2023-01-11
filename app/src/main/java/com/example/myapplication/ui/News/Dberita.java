package com.example.myapplication.ui.News;

import static com.example.myapplication.ui.News.GalleryFragment.EXTRA_ISI;
import static com.example.myapplication.ui.News.GalleryFragment.EXTRA_JUDUL;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Dberita extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dberita);

        Intent intent = getIntent();

        String judul = intent.getStringExtra(EXTRA_JUDUL);
        String isi = intent.getStringExtra(EXTRA_ISI);

        TextView tvjudul = findViewById(R.id.judul);
        TextView tvisi = findViewById(R.id.isi);

        tvjudul.setText(judul);
        tvisi.setText(isi);
    }

}