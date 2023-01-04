package com.example.myapplication.ui.Kst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class KstAdapter extends RecyclerView.Adapter<KstAdapter.KstViewHolder> {

    private Context mContext;
    private ArrayList<Kst> mKstList;

    public KstAdapter(Context context, ArrayList<Kst> kstList) {
        mContext = context;
        mKstList = kstList;
    }

    @Override
    public KstAdapter.KstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_kst, parent, false);
        return new KstAdapter.KstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KstAdapter.KstViewHolder holder, int position) {
        Kst currentItem = mKstList.get(position);

        String matkul = currentItem.getMatkul();
        String sks = currentItem.getSks();

        holder.mTvMatkul.setText(matkul);
        holder.mTvSks.setText(sks);


    }

    @Override
    public int getItemCount() {
        return mKstList.size();
    }

    public class KstViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvMatkul;
        public TextView mTvSks;

        public KstViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvMatkul = itemView.findViewById(R.id.matkul);
            mTvSks = itemView.findViewById(R.id.sks);


        }
    }
}
