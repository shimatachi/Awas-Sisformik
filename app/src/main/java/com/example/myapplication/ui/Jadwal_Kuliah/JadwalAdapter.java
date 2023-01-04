package com.example.myapplication.ui.Jadwal_Kuliah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {

    private Context mContext;
    private ArrayList<Jadwal> mJadwalList;

    public JadwalAdapter(Context context, ArrayList<Jadwal> jadwalList) {
        mContext = context;
        mJadwalList = jadwalList;
    }

    @Override
    public JadwalAdapter.JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_jadwal, parent, false);
        return new JadwalAdapter.JadwalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.JadwalViewHolder holder, int position) {
        Jadwal currentItem = mJadwalList.get(position);

        String matkul = currentItem.getMatkul();
        String jadwal = currentItem.getJadwal();
        String kodematkul = currentItem.getKodematkul();
        String ruangan = currentItem.getRuangan();

        holder.mTvMatkul.setText(matkul);
        holder.mTvJadwal.setText(jadwal);
        holder.mTvKodeMatkul.setText(kodematkul);
        holder.mTvRuangan.setText(ruangan);



    }

    @Override
    public int getItemCount() {
        return mJadwalList.size();
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvMatkul;
        public TextView mTvJadwal;
        public TextView mTvKodeMatkul;
        public TextView mTvRuangan;

        public JadwalViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvMatkul = itemView.findViewById(R.id.jadwalmatkul);
            mTvJadwal = itemView.findViewById(R.id.jammatkul);
            mTvKodeMatkul = itemView.findViewById(R.id.kodematkul);
            mTvRuangan = itemView.findViewById(R.id.ruangan);


        }
    }
}
