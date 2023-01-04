package com.example.myapplication.ui.Pembayaran;

public class PembayaranList {
    private String semester;
    private String upload;
    private String belumterverifikasi;
    private String terverikasi;

    public PembayaranList(String semester, String upload, String belumterverifikasi, String terverikasi) {
        this.semester = semester;
        this.upload = upload;
        this.belumterverifikasi = belumterverifikasi;
        this.terverikasi = terverikasi;
    }

    public String getSemester() {
        return semester;
    }

    public String getUpload() {
        return upload;
    }

    public String getBelumterverifikasi() {
        return belumterverifikasi;
    }

    public String getTerverikasi() {
        return terverikasi;
    }
}
