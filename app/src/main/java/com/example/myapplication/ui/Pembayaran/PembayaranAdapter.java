package com.example.myapplication.ui.Pembayaran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.PembayaranListViewHolder> {

    private Context mContext;
    private ArrayList<PembayaranList> mPembayaranList;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public PembayaranAdapter(Context context, ArrayList<PembayaranList> pembayaranList) {
        mContext = context;
        mPembayaranList = pembayaranList;
    }

    @Override
    public PembayaranAdapter.PembayaranListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_pembayaran, parent, false);
        return new PembayaranAdapter.PembayaranListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PembayaranAdapter.PembayaranListViewHolder holder, int position) {
        PembayaranList currentItem = mPembayaranList.get(position);

        String semester = currentItem.getSemester();
        String upload = currentItem.getUpload();
        String belumterverifikasi = currentItem.getBelumterverifikasi();
        String terverifikasi = currentItem.getTerverikasi();

        holder.mTvSemester.setText(semester);
        holder.mTvUpload.setText(upload);
        holder.mTvBelumTerverifikasi.setText(belumterverifikasi);
        holder.mTvTerverifikasi.setText(terverifikasi);



    }

    @Override
    public int getItemCount() {
        return mPembayaranList.size();
    }

    public class PembayaranListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvSemester;
        public TextView mTvUpload;
        public TextView mTvBelumTerverifikasi;
        public TextView mTvTerverifikasi;
        public Button btnUpload, btnSelect;

        public PembayaranListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvSemester = itemView.findViewById(R.id.label_semester);
            mTvUpload = itemView.findViewById(R.id.upload);
            mTvBelumTerverifikasi = itemView.findViewById(R.id.belumterverifikasi);
            mTvTerverifikasi = itemView.findViewById(R.id.terverifikasi);
            btnUpload = itemView.findViewById(R.id.button_upload);

            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Test Upload", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}