package com.example.onlinedoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class Doctor_Patient_Login extends AppCompatActivity {

    private boolean selector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_login);
    }

    public void Login_Doctor(View view) {
        selector=false;
        Intent intent=new Intent(Doctor_Patient_Login.this,LoginActivity.class);
        intent.putExtra("myBoolean",selector);
        startActivity(intent);
    }

    public void Login_Patient(View view) {
        selector=true;
        Intent intent=new Intent(Doctor_Patient_Login.this,LoginActivity.class);
        intent.putExtra("myBoolean",selector);
        startActivity(intent);
    }


}