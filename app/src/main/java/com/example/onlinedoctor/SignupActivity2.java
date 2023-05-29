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

public class SignupActivity2 extends AppCompatActivity {
TextInputLayout edtname,edtemail,edtcontact,edtquali,edtexpe,edtpass,edtchangepas;
Button btn2;

DatabaseReference reff;
doctor d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        edtname=findViewById(R.id.D_username);
        edtemail=findViewById(R.id.D_email);
        edtcontact=findViewById(R.id.D_contact);
        edtquali=findViewById(R.id.qualification);
        edtexpe=findViewById(R.id.experience);
        edtpass=findViewById(R.id.D_password);
        edtchangepas=findViewById(R.id.D_confirmpass);


        d=new doctor();

        btn2=findViewById(R.id.D_button);

        reff= FirebaseDatabase.getInstance().getReference().child("doctor");


    }

   public void D_registration(View view){

       CheckInternet checkInternet=new CheckInternet();
       if(!checkInternet.isConnected(this)){
           showCustomDialog();
           return;
       }

       if(!validatName()|!validatEmail()|!validatPhoneNo()|!validatPassword()|!validatconfirmpass()|!validatqualy()|!validatexp()){
           return;
       }

       d.setName(edtname.getEditText().getText().toString().trim());
       d.setEmail(edtemail.getEditText().getText().toString().trim());
       d.setContact_no(edtcontact.getEditText().getText().toString().trim());
       d.setExperince(edtexpe.getEditText().getText().toString().trim());
       d.setQualification(edtquali.getEditText().getText().toString().trim());
       d.setPassword(edtpass.getEditText().getText().toString().trim());
       d.setConfirm_pass(edtchangepas.getEditText().getText().toString().trim());
       reff.child(d.getContact_no()).setValue(d);
       Toast.makeText(SignupActivity2.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

       Intent intent=new Intent(SignupActivity2.this,LoginActivity.class);
       startActivity(intent);
   }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity2.this);
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

    private boolean validatName(){
        String val= edtname.getEditText().getText().toString();
        if(val.isEmpty()){
            edtname.setError("Field Cannot be empty");
            return false;
        }
        else{
            edtname.setError(null);
            edtname.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validatEmail(){
        String val= edtemail.getEditText().getText().toString();
        if(val.isEmpty()){
            edtemail.setError("Field Cannot be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            edtemail.setError("Invaild email address");
            return false;
        }
        else{
            edtemail.setError(null);
            return true;
        }
    }

    private boolean validatPhoneNo(){
        String val= edtcontact.getEditText().getText().toString();
        if(val.isEmpty()){
            edtcontact.setError("Field Cannot be empty");
            return false;
        }
        else{
            edtcontact.setError(null);
            return true;
        }
    }

    private boolean validatPassword(){
        String val= edtpass.getEditText().getText().toString();
        String passwordVal="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        CharSequence input=val;
        Pattern pattern = Pattern.compile(passwordVal, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if(val.isEmpty()){
            edtpass.setError("Field Cannot be empty");
            return false;
        }
        else if(!matcher.matches()){
            edtpass.setError("Password is too weak");
            return false;
        }
        else{
            edtpass.setError(null);
            return true;
        }
    }

    private boolean validatconfirmpass(){
        String val1= edtpass.getEditText().getText().toString();
        String val2= edtchangepas.getEditText().getText().toString();


        if(val2.isEmpty()){
            edtchangepas.setError("Field Cannot be empty");
            return false;
        }
        else if(!val2.equals(val1)){
            edtchangepas.setError("Password not Same");
            return false;
        }
        else{
            edtchangepas.setError(null);
            return true;
        }
    }

    private boolean validatqualy(){
        String val= edtquali.getEditText().getText().toString();
        if(val.isEmpty()){
            edtquali.setError("Field Cannot be empty");
            return false;
        }
        else{
            edtquali.setError(null);
            return true;
        }
    }

    private boolean validatexp(){
        String val= edtexpe.getEditText().getText().toString();
        if(val.isEmpty()){
            edtexpe.setError("Field Cannot be empty");
            return false;
        }
        else{
            edtexpe.setError(null);
            return true;
        }
    }

}