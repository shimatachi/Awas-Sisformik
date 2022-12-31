package com.example.myapplication.ui.absensi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MatakuliahListAdapter extends ArrayAdapter<Matakuliah> {
    private static final String TAG = "MatakuliahListAdapter";

    private Context mMontext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */

    public MatakuliahListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Matakuliah> objects) {
        super(context, resource, objects);
        this.mMontext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String namamatkul = getItem(position).getNamamatkul();
        String labeltotal = getItem(position).getLabeltotal();
        String totalabsensi = getItem(position).getTotalabsensi();

        LayoutInflater inflater = LayoutInflater.from(mMontext);
        convertView = inflater.inflate(mResource,parent, false);

        TextView tvNama = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvLabeltotal = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvTotalabsensi = (TextView) convertView.findViewById(R.id.textView3);
        tvNama.setText(namamatkul);
        tvLabeltotal.setText(labeltotal);
        tvTotalabsensi.setText(totalabsensi);

        return convertView;
    }
}
