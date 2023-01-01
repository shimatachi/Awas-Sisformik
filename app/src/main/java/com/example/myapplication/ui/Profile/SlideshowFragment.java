package com.example.myapplication.ui.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.Login;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        SlideshowViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView tvnama = (TextView) root.findViewById(R.id.nama);
        TextView tvjk = (TextView) root.findViewById(R.id.jk);
        TextView tvnim = (TextView) root.findViewById(R.id.nim);
        TextView tvjurusan = (TextView) root.findViewById(R.id.jurusan);
        TextView tvhp = (TextView) root.findViewById(R.id.hp);
        TextView tvemail = (TextView) root.findViewById(R.id.email);
        TextView tvttl = (TextView) root.findViewById(R.id.tl);
        TextView tvalamat = (TextView) root.findViewById(R.id.alamat);
        CircleImageView circleImageView = (CircleImageView) root.findViewById(R.id.profile);


        sharedPreferences = getActivity().getSharedPreferences("LoginFile", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tvnim.setText(sharedPreferences.getString("NIM", "Error loading NIM"));
        tvjk.setText(sharedPreferences.getString("JK", "Error loading Jenis_Kelamin"));
        tvnama.setText(sharedPreferences.getString("Nama_Mahasiwa", "Error loading Nama_Mhs"));
        tvjurusan.setText(sharedPreferences.getString("Jurusan", "Error loading Jurusan"));
        tvhp.setText(sharedPreferences.getString("No_Telp", "Error loading No_Telp"));
        tvemail.setText(sharedPreferences.getString("Email", "Error loading Email"));
        tvttl.setText(sharedPreferences.getString("Tanggal_Lahir", "Error loading Tanggal_Lahir"));
        tvalamat.setText(sharedPreferences.getString("Alamat", "Error loading Alamat"));
        Picasso.get()
                .load(sharedPreferences.getString("Foto", "eror"))
                .into(circleImageView);


        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("LoggedIn");
//                editor.putString("isLoggedIn", "false");
                editor.commit();
                Intent intent = new Intent(root.getContext(), Login.class);
                root.getContext().startActivity(intent);
                getActivity().finish();
//                Navigation.findNavController(view).navigate(R.id.action_nav_slideshow_to_nav_login2);
            }
        });

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}