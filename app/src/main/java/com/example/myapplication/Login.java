package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button btnlogin;
    EditText qUsername, qPassword;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnLogin);
        qUsername = findViewById(R.id.etUsername);
        qPassword = findViewById(R.id.etPassword);
        progressDialog = new ProgressDialog(Login.this);

        sharedPreferences = getSharedPreferences("LoginFile" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        if (sharedPreferences.getString("isLoggedIn" , "").equals("true")){
//            startActivity(new Intent(Login.this , MainActivity.class));
//            finishAffinity();
//        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sUsername = qUsername.getText().toString();
                String sPassword = qPassword.getText().toString();

                Loginactivity(sUsername,sPassword);
/*
                if (binding.etUsername.getText().toString().isEmpty()) {
                    binding.etUsername.setError("Please Enter Username");
                } else if (binding.etPassword.getText().toString().isEmpty()) {
                    binding.etPassword.setError("Please Enter Password");
                } else {
                    Loginactivity(binding);
                }
                */
            }
        });

    }

    private void Loginactivity(final String username, final String password) {
        if (checkNetworkConnection()) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_LOGIN_URL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String resp = jsonObject.getString("server_response");
                        if (resp.equals("[{\"status\":\"OK\"}]")) {
                            Toast.makeText(getApplicationContext(),"Login Berhasil", Toast.LENGTH_SHORT).show();
                            getUserDetail(username);
//                            editor.putString("isLoggedIn", "true");
//                            editor.commit();
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(intent);
//                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), resp,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }, error -> {

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("Password", password);
                return params;
            }
        };

        VolleyConnection.getInstance(Login.this).addToRequestQue(stringRequest);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        }, 200);
    } else
    {
        Toast.makeText(getApplicationContext(),"Koneksi Gagal", Toast.LENGTH_SHORT).show();
    }


}

    private void getUserDetail(String username) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_GetData_URL,
                    response -> {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            editor.putBoolean("LoggedIn",true);
                            editor.putString("NIM",jsonObject.getString("NIM"));
                            editor.putString("Nama_Mahasiwa",jsonObject.getString("Nama_Mahasiwa"));
                            editor.putString("JK",jsonObject.getString("JK"));
                            editor.putString("No_Telp",jsonObject.getString("No_Telp"));
                            editor.putString("Email",jsonObject.getString("Email"));
                            editor.putString("Tanggal_Lahir",jsonObject.getString("Tanggal_Lahir"));
                            editor.putString("Alamat",jsonObject.getString("Alamat"));
                            editor.putString("Jurusan",jsonObject.getString("Jurusan"));


                            while (!editor.commit())
                            {
                                editor.commit();

                            }
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
//                                loginUsername.setText(jsonObject.getString("nama"));
//                                Toast.makeText(Login.this, jsonObject.getString("nama"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Username", username);
                    return params;
                }
            };

            VolleyConnection.getInstance(Login.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 200);
        } else
        {
            Toast.makeText(getApplicationContext(),"Koneksi Gagal", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
        return ((networkinfo != null) && (networkinfo.isConnected()));
    }
}

//                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.login,new HomeFragment()).commit();


//            startActivity(new Intent(Login.this, MainActivity.class));
