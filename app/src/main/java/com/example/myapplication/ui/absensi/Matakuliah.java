package com.example.myapplication.ui.absensi;

public class Matakuliah {
    private String namamatkul;
    private String totalabsensi;

    public Matakuliah(String namamatkul, String totalabsensi) {
        this.namamatkul = namamatkul;
        this.totalabsensi = totalabsensi;
    }

    public String getNamamatkul() {
        return namamatkul;
    }


    public String getTotalabsensi() {
        return totalabsensi;
    }
}

