package com.example.onlinedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity1 extends AppCompatActivity {
TextInputLayout edit1_username,edit2_email,edit3_contact,edit4_pass,edit5_pass;
Button btn1;
DatabaseReference reff;
Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        edit1_username=findViewById(R.id.P_username);
        edit2_email=findViewById(R.id.P_email);
        edit3_contact=findViewById(R.id.P_contact);
        edit4_pass=findViewById(R.id.P_password);
        edit5_pass=findViewById(R.id.P_confirmpass);
        btn1=findViewById(R.id.P_button);
        patient = new Patient();
        reff= FirebaseDatabase.getInstance().getReference().child("Patient");



    }

    private boolean validatName(){
        String val= edit1_username.getEditText().getText().toString();
        if(val.isEmpty()){
            edit1_username.setError("Field Cannot be empty");
            return false;
        }
        else{
            edit1_username.setError(null);
            edit1_username.setErrorEnabled(false);
            return true;
        }
    }

    public void P_registration(View view){

        CheckInternet checkInternet=new CheckInternet();
        if(!checkInternet.isConnected(this)){
            showCustomDialog();
            return;
        }

        if(!validatName()|!validatEmail()|!validatPhoneNo()|!validatPassword()|!validatconfirmpass()){
            return;
        }

        patient.setName(edit1_username.getEditText().getText().toString().trim());
        patient.setEmail(edit2_email.getEditText().getText().toString().trim());
        patient.setContact_no(edit3_contact.getEditText().getText().toString().trim());
        patient.setPassword(edit4_pass.getEditText().getText().toString().trim());
        patient.setConfirm_pass(edit5_pass.getEditText().getText().toString().trim());
        reff.child(patient.getContact_no()).setValue(patient);
        Toast.makeText(SignupActivity1.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(SignupActivity1.this,LoginActivity.class);
        startActivity(intent);


    }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity1.this);
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
                        startActivity(new Intent(getApplicationContext(),Doctor_Patient_Login.class));
                        finish();
                    }
                });
        builder.show();
    }


    private boolean validatEmail(){
        String val= edit2_email.getEditText().getText().toString();
        if(val.isEmpty()){
            edit2_email.setError("Field Cannot be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            edit2_email.setError("Invaild email address");
            return false;
        }
        else{
            edit2_email.setError(null);
            return true;
        }
    }

    private boolean validatPhoneNo(){
        String val= edit3_contact.getEditText().getText().toString();
        if(val.isEmpty()){
            edit3_contact.setError("Field Cannot be empty");
            return false;
        }
        else{
            edit3_contact.setError(null);
            return true;
        }
    }

    private boolean validatPassword(){
        String val= edit4_pass.getEditText().getText().toString();
        String passwordVal="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        CharSequence input=val;
        Pattern pattern = Pattern.compile(passwordVal, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if(val.isEmpty()){
            edit4_pass.setError("Field Cannot be empty");
            return false;
        }
        else if(!matcher.matches()){
            edit4_pass.setError("Password is too weak");
            return false;
        }
        else{
            edit4_pass.setError(null);
            return true;
        }
    }

    private boolean validatconfirmpass(){
        String val1= edit4_pass.getEditText().getText().toString();
        String val2= edit5_pass.getEditText().getText().toString();


        if(val2.isEmpty()){
            edit5_pass.setError("Field Cannot be empty");
            return false;
        }
        else if(!val2.equals(val1)){
            edit5_pass.setError("Password not Same");
            return false;
        }
        else{
            edit5_pass.setError(null);
            return true;
        }
    }

}