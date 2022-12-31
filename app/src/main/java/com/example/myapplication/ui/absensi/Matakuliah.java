package com.example.myapplication.ui.absensi;

public class Matakuliah {
    private String namamatkul;
    private String labeltotal;
    private String totalabsensi;

    public Matakuliah(String namamatkul, String labeltotal, String totalabsensi) {
        this.namamatkul = namamatkul;
        this.labeltotal = labeltotal;
        this.totalabsensi = totalabsensi;
    }

    public String getNamamatkul() {
        return namamatkul;
    }

    public void setNamamatkul(String namamatkul) {
        this.namamatkul = namamatkul;
    }

    public String getLabeltotal() {
        return labeltotal;
    }

    public void setLabeltotal(String labeltotal) {
        this.labeltotal = labeltotal;
    }

    public String getTotalabsensi() {
        return totalabsensi;
    }

    public void setTotalabsensi(String totalabsensi) {
        this.totalabsensi = totalabsensi;
    }
}

