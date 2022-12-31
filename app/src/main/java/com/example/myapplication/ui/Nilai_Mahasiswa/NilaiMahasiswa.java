package com.example.myapplication.ui.Nilai_Mahasiswa;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NilaiMahasiswa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NilaiMahasiswa extends Fragment {

    BarChart bar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NilaiMahasiswa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NilaiMahasiswa.
     */
    // TODO: Rename and change types and number of parameters
    public static NilaiMahasiswa newInstance(String param1, String param2) {
        NilaiMahasiswa fragment = new NilaiMahasiswa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_nilai_mahasiswa,container,false);

//        // Set Actionbar Title
//        getActivity().setTitle("Nilai Mahasiswa");

        Button btnnilai = (Button) view.findViewById(R.id.button_lihatnilai);

        btnnilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_nav_nilai_to_nav_nilai2);

            }
        });


        bar = (BarChart)view.findViewById(R.id.barchart_ips);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1f));
        entries.add(new BarEntry(1f, 2f));
        entries.add(new BarEntry(2f, 3f));
        entries.add(new BarEntry(3f, 4f));
        entries.add(new BarEntry(4f, 5f));

        BarDataSet bSet = new BarDataSet(entries, "Semester");
        //color bar data set
        bSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> barFactors = new ArrayList<>();
        barFactors.add("1");
        barFactors.add("2");
        barFactors.add("3");
        barFactors.add("4");
        barFactors.add("5");

        XAxis xAxis = bar.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        BarData data = new BarData(bSet);
        data.setBarWidth(0.9f); // set custom bar width
        data.setValueTextSize(10f);
        Description description = new Description();
        description.setTextColor(R.color.black);
        description.setText("Your IPS");
        bar.setDescription(description);
        bar.setData(data);
        bar.setFitBars(true); // make the x-axis fit exactly all bars
        bar.invalidate(); // refresh
        bar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        Legend l = bar.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        List<LegendEntry> lentries = new ArrayList<>();
        for (int i = 0; i < barFactors.size(); i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = ColorTemplate.COLORFUL_COLORS[i];
            entry.label = barFactors.get(i);
            lentries.add(entry);
        }
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        l.setCustom(lentries);

        //return inflater.inflate(R.layout.fragment_nilai_mahasiswa, container, false);
        l.setCustom(lentries);
        return view;
    }
}