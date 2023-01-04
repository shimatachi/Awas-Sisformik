package com.example.myapplication.ui.Jadwal_Kuliah;

public class Jadwal {
    private String matkul;
    private String jadwal;
    private String kodematkul;
    private String ruangan;

    public Jadwal(String matkul, String jadwal, String kodematkul, String ruangan) {
        this.matkul = matkul;
        this.jadwal = jadwal;
        this.kodematkul = kodematkul;
        this.ruangan = ruangan;
    }

    public String getMatkul() {
        return matkul;
    }

    public String getJadwal() {
        return jadwal;
    }

    public String getKodematkul() {
        return kodematkul;
    }

    public String getRuangan() {
        return ruangan;
    }
}
