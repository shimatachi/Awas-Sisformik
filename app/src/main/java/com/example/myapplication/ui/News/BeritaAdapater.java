package com.example.myapplication.ui.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class BeritaAdapater extends RecyclerView.Adapter<BeritaAdapater.BeritaViewHolder>{
    private final Context mContext;
    private final ArrayList<Berita> mBeritaList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public BeritaAdapater(Context context, ArrayList<Berita> beritaList) {
        mContext = context;
        mBeritaList = beritaList;
    }

    @Override
    public BeritaAdapater.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_berita, parent, false);
        return new BeritaAdapater.BeritaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapater.BeritaViewHolder holder, int position) {
        Berita currentItem = mBeritaList.get(position);

        String judulnews = currentItem.getJudulnews();
        String tglterbit = currentItem.getTglterbit();

        holder.mTvJudul.setText(judulnews);
        holder.mTvTglTerbit.setText(tglterbit);


    }

    @Override
    public int getItemCount() {
        return mBeritaList.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvJudul;
        public TextView mTvTglTerbit;
//        public Button readmore;



        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvJudul = itemView.findViewById(R.id.judul_news1);
            mTvTglTerbit = itemView.findViewById(R.id.tgl_news1);
//            readmore = itemView.findViewById(R.id.btn_selengkapnya_news1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
