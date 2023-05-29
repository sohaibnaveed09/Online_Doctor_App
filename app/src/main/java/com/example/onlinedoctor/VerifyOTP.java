package com.example.onlinedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pinView;
    String _phone;
    String codeBysystem;
    Button Verify;
    ProgressBar p;
    TextView Resendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        pinView = findViewById(R.id.pin_view);
        Verify = findViewById(R.id.verify);
        p=findViewById(R.id.progress);
        _phone = getIntent().getStringExtra("phone");
        Resendotp=findViewById(R.id.resendotp);
        codeBysystem= getIntent().getStringExtra("s");

        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!pinView.getText().toString().trim().isEmpty()){
                    String entercodeotp = pinView.getText().toString();
                    if(codeBysystem!=null){
                        p.setVisibility(View.VISIBLE);
                        Verify.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                codeBysystem,entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                p.setVisibility(View.GONE);
                                Verify.setVisibility(View.VISIBLE);

                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(),SetNewPassword.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(VerifyOTP.this, "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(VerifyOTP.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(VerifyOTP.this, "otp verify", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(VerifyOTP.this, "enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberotpmove();
        Resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                codeBysystem=newbackendotp;
                                Toast.makeText(VerifyOTP.this, "OTP Resend succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });

    }

    private void numberotpmove() {
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    pinView.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}