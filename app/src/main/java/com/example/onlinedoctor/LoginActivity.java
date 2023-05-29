package com.example.onlinedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout U_phone, U_pass;
    ProgressBar progressBar;
    Button btn1, btn2;
    TextView forgetpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean selec=getIntent().getExtras().getBoolean("myBoolean");

        if(selec==false){
            Toast.makeText(LoginActivity.this,"Doctor", Toast.LENGTH_LONG).show();
        }
        else if(selec==true){
            Toast.makeText(LoginActivity.this,"Patient", Toast.LENGTH_LONG).show();
        }


        progressBar = findViewById(R.id.bar);
        progressBar.setVisibility(View.GONE);

        U_phone = findViewById(R.id.U_username);
        U_pass = findViewById(R.id.U_password);

        btn1 = findViewById(R.id.U_button);
        btn2 = findViewById(R.id.click_register);

        forgetpass = findViewById(R.id.forget_pass);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), forgetpassword.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckInternet checkInternet=new CheckInternet();
                if(!checkInternet.isConnected(LoginActivity.this)){
                    showCustomDialog();
                    return;
                }

                if (!validatName() | !validatPass()) {
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                String n = U_phone.getEditText().getText().toString().trim();
                String p = U_pass.getEditText().getText().toString().trim();

                if(selec==false){
                    Query checkuser_doctor = FirebaseDatabase.getInstance().getReference("doctor").orderByChild("contact_no").equalTo(n);

                    checkuser_doctor.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                U_phone.setError(null);
                                U_phone.setErrorEnabled(false);

                                String firebase_pass = snapshot.child(n).child("password").getValue(String.class);
                                if (firebase_pass.equals(p)) {
                                    U_pass.setError(null);
                                    U_pass.setErrorEnabled(false);

                                    String _name = snapshot.child(n).child("name").getValue(String.class);
                                    String _email = snapshot.child(n).child("email").getValue(String.class);
                                    String _number = snapshot.child(n).child("contact_no").getValue(String.class);
                                    String _exp = snapshot.child(n).child("experince").getValue(String.class);
                                    String _qua = snapshot.child(n).child("qualification").getValue(String.class);

                                    Intent intent=new Intent(LoginActivity.this,Doctor_DashBoard.class);
                                    intent.putExtra("name1",_name);
                                    intent.putExtra("email1",_email);
                                    intent.putExtra("phoneNo1",_number);
                                    intent.putExtra("exp1",_exp);
                                    intent.putExtra("qua1",_qua);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Password Wrong ", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "No such user exists ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else if(selec==true){
                    Query checkuser_pat = FirebaseDatabase.getInstance().getReference("Patient").orderByChild("contact_no").equalTo(n);

                    checkuser_pat.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                U_phone.setError(null);
                                U_phone.setErrorEnabled(false);

                                String firebase_pass = snapshot.child(n).child("password").getValue(String.class);
                                if (firebase_pass.equals(p)) {
                                    U_pass.setError(null);
                                    U_pass.setErrorEnabled(false);

                                   String _name = snapshot.child(n).child("name").getValue(String.class);
                                    String _email = snapshot.child(n).child("email").getValue(String.class);
                                    String _number = snapshot.child(n).child("contact_no").getValue(String.class);

                                    Intent intent=new Intent(LoginActivity.this,HomePage.class);
                                    intent.putExtra("name1",_name);
                                    intent.putExtra("email1",_email);
                                    intent.putExtra("phoneNo1",_number);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Password Wrong ", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "No such user exists ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selec==false){
                    Intent intent = new Intent(getApplicationContext(), SignupActivity2.class);
                    startActivity(intent);
                }
                else if(selec==true){
                    Intent intent = new Intent(getApplicationContext(), SignupActivity1.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
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

    private boolean validatName() {
        String val = U_phone.getEditText().getText().toString();
        if (val.isEmpty()) {
            U_phone.setError("Field Cannot be empty");
            return false;
        } else {
            U_phone.setError(null);
            U_phone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatPass() {
        String val = U_pass.getEditText().getText().toString();
        if (val.isEmpty()) {
            U_pass.setError("Field Cannot be empty");
            return false;
        } else {
            U_pass.setError(null);
            U_pass.setErrorEnabled(false);
            return true;
        }
    }
}