package com.example.myapplication.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.absensi.Absensi;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class    HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        return binding.getRoot();

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);


        TextView tvnim = (TextView) rootview.findViewById(R.id.nim);
        TextView tvnama = (TextView) rootview.findViewById(R.id.nama);
        Button btnnilai = (Button) rootview.findViewById(R.id.nilai);
        Button btnpembayaran = (Button) rootview.findViewById(R.id.pembayaran);
        Button btnkst = (Button) rootview.findViewById(R.id.kst);
        Button btnabsensi = (Button) rootview.findViewById(R.id.absensi);
        CircleImageView circleImageView = (CircleImageView) rootview.findViewById(R.id.profile);

        sharedPreferences = getActivity().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tvnim.setText(sharedPreferences.getString("NIM", "Error loading nim"));
        tvnama.setText(sharedPreferences.getString("Nama_Mahasiwa", "Error loading nama"));
        Picasso.get()
                .load(sharedPreferences.getString("Foto", "eror"))
                .into(circleImageView);



        btnnilai.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nilai2);

//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fr = fragmentManager.beginTransaction();
//            fr.replace(R.id.nav_host_fragment_content_main, new NilaiMahasiswa());
//            fr.addToBackStack(null);
//            fr.commit();
        });

        btnpembayaran.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_pembayaran);
        });

        btnkst.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_kst);
        });

        btnabsensi.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_absensi);
        });





        return rootview;


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}