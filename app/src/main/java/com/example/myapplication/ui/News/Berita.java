package com.example.myapplication.ui.News;

public class Berita {

    private final String judulnews;
    private String isi;
    private final String tglterbit;

    public Berita(String judulnews, String tglterbit, String isi) {
        this.judulnews = judulnews;
        this.isi = isi;
        this.tglterbit = tglterbit;

    }

    public String getIsi() {
        return isi;
    }

    public String getJudulnews() {
        return judulnews;
    }
//
    public String getTglterbit() {
        return tglterbit;
    }
}
