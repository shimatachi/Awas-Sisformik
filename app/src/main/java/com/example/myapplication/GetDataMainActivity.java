package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class GetDataMainActivity {
    private static SharedPreferences sharedPreferences;
    private Context context;
    private String NIM = "NIM", Nama_Mahasiswa = "Nama_Mahasiswa";
    public GetDataMainActivity(Context context) {
        this.sharedPreferences = context.getSharedPreferences("Data_Home",
                Context.MODE_PRIVATE);
        this.context = context;
    }
    public String getNIM() {
        return sharedPreferences.getString(NIM, "");
    }
    public String getNama_Mahasiswa() {
        return sharedPreferences.getString(Nama_Mahasiswa, "");
    }
    public void setNIM(String NIM) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.NIM, NIM);
        edit.commit();
    }
    public void setNama_Mahasiswa(String Nama_Mahasiswa) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.Nama_Mahasiswa, Nama_Mahasiswa);
        edit.commit();
    }
}
