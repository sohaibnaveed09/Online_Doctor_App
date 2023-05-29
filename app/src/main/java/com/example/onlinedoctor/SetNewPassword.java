package com.example.onlinedoctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {

    TextInputLayout new_pass,confirm_pass;
    Button btn1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        new_pass=findViewById(R.id.n_password);
        confirm_pass=findViewById(R.id.n_confirmpass);

        btn1=findViewById(R.id.update);
        progressBar=findViewById(R.id.bar);
        progressBar.setVisibility(View.GONE);

    }
    public void setNewPasswordBtn(View view){

        CheckInternet checkInternet=new CheckInternet();
        if(!checkInternet.isConnected(this)){
            showCustomDialog();
            return;
        }

        if (!validatPass()| !validatconfirmpass()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        String _newpass=new_pass.getEditText().getText().toString().trim();
        String _phone=getIntent().getStringExtra("phone");


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("doctor");
        reference.child(_phone).child("password").setValue(_newpass);
        reference.child(_phone).child("confirm_pass").setValue(_newpass);

    }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SetNewPassword.this);
        builder.setMessage("Please connect to the Internet to proceed")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                    }
                });
        builder.show();
    }


    private boolean validatPass() {
        String val = confirm_pass.getEditText().getText().toString();
        if (val.isEmpty()) {
            confirm_pass.setError("Field Cannot be empty");
            return false;
        } else {
            confirm_pass.setError(null);
            confirm_pass.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatconfirmpass(){
        String val1= new_pass.getEditText().getText().toString();
        String val2= confirm_pass.getEditText().getText().toString();


        if(val2.isEmpty()){
            confirm_pass.setError("Field Cannot be empty");
            return false;
        }
        else if(!val2.equals(val1)){
            confirm_pass.setError("Password not Same");
            return false;
        }
        else{
            confirm_pass.setError(null);
            return true;
        }
    }
}