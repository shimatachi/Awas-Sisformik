package com.example.myapplication.ui.absensi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MatakuliahAdapter extends RecyclerView.Adapter<MatakuliahAdapter.MatakuliahViewHolder> {
    private Context mContext;
    private ArrayList<Matakuliah> mMatakuliahListl;

    public MatakuliahAdapter(Context context, ArrayList<Matakuliah> matakuliahList) {
        mContext = context;
        mMatakuliahListl = matakuliahList;
    }

    @Override
    public MatakuliahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_view_layout, parent, false);
        return new MatakuliahViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatakuliahViewHolder holder, int position) {
        Matakuliah currentItem = mMatakuliahListl.get(position);

        String namamatkul = currentItem.getNamamatkul();
        String totalabsensi = currentItem.getTotalabsensi();

        holder.mTvMatkul.setText(namamatkul);
        holder.mTvJmlhabsensi.setText(totalabsensi);


    }

    @Override
    public int getItemCount() {
        return mMatakuliahListl.size();
    }

    public class MatakuliahViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvMatkul;
        public TextView mTvJmlhabsensi;

        public MatakuliahViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvMatkul = itemView.findViewById(R.id.mtakuliah);
            mTvJmlhabsensi = itemView.findViewById(R.id.jumlahabsensi);


        }
    }
}
