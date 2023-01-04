package com.example.myapplication.ui.News;

public class Berita {

    private String judulnews;
    private String tglterbit;

    public Berita(String judulnews, String tglterbit) {
        this.judulnews = judulnews;
        this.tglterbit = tglterbit;

    }


    public String getJudulnews() {
        return judulnews;
    }
//
    public String getTglterbit() {
        return tglterbit;
    }
}
